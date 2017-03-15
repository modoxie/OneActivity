package com.base.oneactivity.ui;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.base.oneactivity.function.RequestPermissionsResultAction;
import com.base.oneactivity.tool.ScreenUtil;
import com.base.oneactivity.tool.UIUtil;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;

/**
 * Created by Administrator on 2016/6/21
 */
public class AContext extends AppCompatActivity implements UIControl {
    private AssetManager mAssetManager;
    private Resources mResources;
    private Resources.Theme mTheme;
    private boolean isAnima;
    private LinkedList<UI> backUIViews = new LinkedList<>();
    private UI nowUI;
    private FrameLayout root;
    private Map<String, RequestPermissionsResultAction> mapPermission;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UIUtil.init(this);
        root = new FrameLayout(this);
        root.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        setContentView(root);
        if (savedInstanceState != null) {
            recoverySave(savedInstanceState);
        }
    }

    @Override
    public Activity getActivity() {
        return this;
    }

    @Override
    public UIControl show(UI to) {
        if (isAnima) return this;
        if (to == null || to == nowUI) return this;
        to.setUIControl(this);
        if (to.getView() == null) to.createView(this);
        UI old = nowUI;
        root.addView(to.getView());
        if (old != null) {
            root.removeView(old.getView());
            old.onHint();
            backUIViews.add(old);
        }
        to.onShow();
        nowUI = to;
        return this;
    }

    @Override
    public UIControl show(final UI to, final ChangeAnimator changeAnimator) {
        if (isAnima) return this;
        if (changeAnimator == null) return show(to);
        if (to == null || to == nowUI) return this;
        to.setUIControl(this);
        if (to.getView() == null) to.createView(this);
        final UI old = nowUI;
        final float sceneWidth = ScreenUtil.getSceneWidth();
        ValueAnimator animator = ValueAnimator.ofFloat(0, sceneWidth);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (float) animation.getAnimatedValue();
                changeAnimator.getOnChange().action(old, to, value / sceneWidth);
            }
        });
        animator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                isAnima = true;
                root.addView(to.getView());
                changeAnimator.getOnStart().action(old, to);
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                isAnima = false;
                nowUI = to;
                if (old != null) {
                    root.removeView(old.getView());
                    old.onHint();
                    backUIViews.add(old);
                    changeAnimator.getOnFinish().action(old, to);
                }
                to.onShow();
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                isAnima = false;
                if (old != null) {
                    root.removeView(old.getView());
                    changeAnimator.getOnFinish().action(old, to);
                    old.onHint();
                    backUIViews.add(old);
                }
                to.onShow();
                nowUI = to;
            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        animator.setDuration(changeAnimator.getTime());
        animator.start();
        return this;
    }

    @Override
    public UIControl back() {
        if (isAnima) return this;
        UI old = nowUI, to;
        if (backUIViews.size() > 0) {
            to = backUIViews.getLast();
        } else {
            to = null;
        }
        destroy(old);
        if (to != null) {
            to.setUIControl(this);
            if (to.getView() == null) to.createView(this);
            root.addView(to.getView());
            to.onShow();
            nowUI = to;
            backUIViews.removeLast();
        } else {
            finish();
        }
        return this;
    }

    @Override
    public UIControl back(final ChangeAnimator changeAnimator) {
        if (isAnima) return this;
        if (changeAnimator == null) return back();
        final UI old = nowUI, to;
        if (backUIViews.size() > 0) {
            to = backUIViews.getLast();
        } else {
            to = null;
        }
        final float sceneWidth = ScreenUtil.getSceneWidth();
        ValueAnimator animator = ValueAnimator.ofFloat(0, sceneWidth);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (float) animation.getAnimatedValue();
                changeAnimator.getOnChange().action(old, to, value / sceneWidth);
            }
        });
        animator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                isAnima = true;
                if (to != null) {
                    to.setUIControl(AContext.this);
                    if (to.getView() == null) to.createView(AContext.this);
                    root.addView(to.getView());
                }
                changeAnimator.getOnStart().action(old, to);
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                isAnima = false;
                changeAnimator.getOnFinish().action(old, to);
                destroy(old);
                if (to == null) {
                    finish();
                } else {
                    to.onShow();
                    backUIViews.removeLast();
                    nowUI = to;
                }
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                isAnima = false;
                changeAnimator.getOnFinish().action(old, to);
                destroy(old);
                if (to == null) {
                    finish();
                } else {
                    to.onShow();
                    backUIViews.removeLast();
                    nowUI = to;
                }
            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        animator.setDuration(changeAnimator.getTime());
        animator.start();
        return this;
    }

    @Override
    public UIControl backTo(int uiID) {
        if (nowUI.getID() == uiID) return this;
        for (int i = backUIViews.size() - 1; i >= 0; i--) {
            UI ui = backUIViews.get(i);
            if (ui.getID() == uiID) {
                destroy(nowUI);
                show(ui);
                return this;
            } else {
                destroy(ui);
            }
        }
        destroy(nowUI);
        return this;
    }

    @Override
    public UIControl backTo(String name) {
        if (nowUI.getName().equals(name)) return this;
        for (int i = backUIViews.size() - 1; i >= 0; i--) {
            UI ui = backUIViews.get(i);
            if (ui.getName().equals(name)) {
                destroy(nowUI);
                show(ui);
                return this;
            } else {
                destroy(ui);
            }
        }
        destroy(nowUI);
        return this;
    }

    @Override
    public UIControl destroy(UI ui) {
        if (ui == null) return this;
        if (nowUI == ui) {
            root.removeView(ui.getView());
            ui.onHint();
            ui.onDestroy();
            nowUI = null;
            return this;
        }
        if (backUIViews.contains(ui)) {
            backUIViews.remove(ui);
            ui.onDestroy();
            return this;
        }
        ui.onHint();
        ui.onDestroy();
        return this;
    }

    @Override
    public UIControl destroy(int uiID) {
        if (nowUI.getID() == uiID) {
            root.removeView(nowUI.getView());
            nowUI.onHint();
            nowUI.onDestroy();
            nowUI = null;
            return this;
        }
        for (int i = backUIViews.size() - 1; i >= 0; i--) {
            UI ui = backUIViews.get(i);
            if (ui.getID() == uiID) {
                ui.onHint();
                ui.onDestroy();
                backUIViews.remove(ui);
                return this;
            }
        }
        return this;
    }

    @Override
    public ViewGroup getRoot() {
        return root;
    }

    @Override
    public boolean hasUI(String name) {
        for (UI ui : backUIViews) {
            if (ui.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public UI findUI(String name) {
        for (UI ui : backUIViews) {
            if (ui.getName().equals(name)) {
                return ui;
            }
        }
        return null;
    }

    @Override
    public UI findUI(int id) {
        for (UI ui : backUIViews) {
            if (ui.getID() == id) {
                return ui;
            }
        }
        return null;
    }

    @Override
    public UIControl removeAll() {
        Iterator<UI> iterator=backUIViews.iterator();
        while(iterator.hasNext()){
            UI ui=iterator.next();
            iterator.remove();
            ui.onHint();
            ui.onDestroy();
        }
        if (nowUI != null) {
            root.removeView(nowUI.getView());
            nowUI.onHint();
            nowUI.onDestroy();
            nowUI = null;
        }
        return this;
    }

    @Override
    public UI getTop() {
        return nowUI;
    }

    @Override
    public void onBackPressed() {
        if (isAnima) return;
        if (nowUI == null || !nowUI.onBack()) {
            back();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (nowUI != null) {
            nowUI.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        if (nowUI != null) {
            nowUI.onRestart();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (nowUI != null) {
            nowUI.onStart();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (nowUI != null) {
            nowUI.onResume();
        }
    }

    @Override
    protected void onPause() {
        if (nowUI != null) {
            nowUI.onPause();
        }
        super.onPause();
    }

    @Override
    protected void onStop() {
        if (nowUI != null) {
            nowUI.onStop();
        }
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Iterator<UI> iterator=backUIViews.iterator();
        while(iterator.hasNext()){
            UI ui=iterator.next();
            if (ui != null) ui.setUIControl(null);
        }
        if (nowUI != null) {
            destroy(nowUI);
        }
        UIUtil.out(this);
        super.onDestroy();
    }

    @Override
    public void loadResources(String dexPath) {
        try {
            AssetManager assetManager = AssetManager.class.newInstance();
            Method addAssetPath = assetManager.getClass().getMethod("addAssetPath", String.class);
            addAssetPath.invoke(assetManager, dexPath);
            mAssetManager = assetManager;
        } catch (Exception e) {
            e.printStackTrace();
        }
        Resources superRes = super.getResources();
        mResources = new Resources(mAssetManager, superRes.getDisplayMetrics(), superRes.getConfiguration());
        mTheme = mResources.newTheme();
        mTheme.setTo(super.getTheme());
    }

    @Override
    public AssetManager getAssets() {
        return mAssetManager == null ? super.getAssets() : mAssetManager;
    }

    @Override
    public Resources getResources() {
        return mResources == null ? super.getResources() : mResources;
    }

    @Override
    public Resources.Theme getTheme() {
        return mTheme == null ? super.getTheme() : mTheme;
    }

    public void useDexClassLoader(String path) {

    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return isAnima || super.dispatchTouchEvent(ev);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        ControlUtil c = ControlUtil.getInstance();
        c.setMapPermission(mapPermission);
        if (nowUI != null) {
            c.setNowUI(nowUI);
            c.setView(nowUI.getView());
            c.setBackUIViews(backUIViews);
            c.setData(nowUI.getData());
        }
    }

    protected void recoverySave(Bundle savedInstanceState) {
        ControlUtil c = ControlUtil.getInstance();
        mapPermission = c.getMapPermission();
        nowUI = c.getNowUI();
        if (nowUI != null) {
            nowUI.setUIControl(this);
            nowUI.setData(c.getData()).setView(c.getView());
            root.addView(nowUI.getView());
            nowUI.onShow();
            backUIViews = c.getBackUIViews();
        }
    }

    @Override
    public UIControl addRequest(@NonNull String[] permission, RequestPermissionsResultAction action) {
        if (mapPermission == null) mapPermission = new HashMap<>();
        for (String p : permission) {
            if (ActivityCompat.checkSelfPermission(getActivity(), p) == PackageManager.PERMISSION_GRANTED) {
                action.permissionGranted.action();
                return this;
            }
        }
        if (action.toast != null) action.toast.action();
        if (permission.length > 0) {
            mapPermission.put(permission[0], action);
            ActivityCompat.requestPermissions(getActivity(), new String[]{permission[0]}, mapPermission.size());
        }
        return this;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (mapPermission == null || permissions.length == 0) return;
        RequestPermissionsResultAction action = mapPermission.get(permissions[0]);
        if (action == null) {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        } else {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                action.permissionGranted.action();
            } else {
                action.permissionDenied.action();
            }
        }
    }

    @Override
    public void finish() {
        for(UI ui:backUIViews){
            destroy(ui);
        }
        destroy(nowUI);
        super.finish();
    }
}
