package com.base.oneactivity.ui;

import android.content.res.AssetManager;
import android.content.res.Resources;
import android.view.View;
import android.widget.FrameLayout;


import com.base.oneactivity.function.RequestPermissionsResultAction;

import org.json.JSONObject;

import java.util.LinkedList;
import java.util.Map;

/**
 * Created by Administrator on 2016/7/26
 */
public class ControlUtil {
    private static ControlUtil controlUtil;
    private AssetManager mAssetManager;
    private Resources mResources;
    private Resources.Theme mTheme;
    private boolean isAnima;
    private LinkedList<UI> backUIViews = new LinkedList<>();
    private UI nowUI;
    private FrameLayout root;
    private JSONObject data;
    private View view;
    private Map<String, RequestPermissionsResultAction> mapPermission;

    public static ControlUtil getInstance() {
        if (controlUtil == null) {
            controlUtil = new ControlUtil();
        }
        return controlUtil;
    }

    public AssetManager getmAssetManager() {
        return mAssetManager;
    }

    public void setmAssetManager(AssetManager mAssetManager) {
        this.mAssetManager = mAssetManager;
    }

    public Resources getmResources() {
        return mResources;
    }

    public void setmResources(Resources mResources) {
        this.mResources = mResources;
    }

    public Resources.Theme getmTheme() {
        return mTheme;
    }

    public void setmTheme(Resources.Theme mTheme) {
        this.mTheme = mTheme;
    }

    public boolean isAnima() {
        return isAnima;
    }

    public void setAnima(boolean anima) {
        isAnima = anima;
    }

    public LinkedList<UI> getBackUIViews() {
        return backUIViews;
    }

    public void setBackUIViews(LinkedList<UI> backUIViews) {
        this.backUIViews = backUIViews;
    }

    public UI getNowUI() {
        return nowUI;
    }

    public void setNowUI(UI nowUI) {
        this.nowUI = nowUI;
    }

    public FrameLayout getRoot() {
        return root;
    }

    public void setRoot(FrameLayout root) {
        this.root = root;
    }

    public JSONObject getData() {
        return data;
    }

    public void setData(JSONObject data) {
        this.data = data;
    }

    public View getView() {
        return view;
    }

    public void setView(View vie) {
        this.view = vie;
    }

    public Map<String, RequestPermissionsResultAction> getMapPermission() {
        return mapPermission;
    }

    public void setMapPermission(Map<String, RequestPermissionsResultAction> mapPermission) {
        this.mapPermission = mapPermission;
    }
}
