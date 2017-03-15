package com.base.oneactivity.ui;

import android.app.Activity;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import org.json.JSONObject;

import java.util.concurrent.ExecutorService;

import static java.util.concurrent.Executors.newSingleThreadExecutor;

/**
 * Created by Administrator on 2016/6/21
 */
public abstract class UIView implements UI {
    private JSONObject mData;
    protected UIModel uiModel;
    protected View mView;
    private String name;
    public static ExecutorService executorService;

    public UIView() {

    }

    public UIView(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        if (null == name || TextUtils.isEmpty(name)) {
            return getClass().getSimpleName();
        }
        return name;
    }

    @Override
    public View getView() {
        return mView;
    }

    @Override
    public int getID() {
        return hashCode();
    }

    @Override
    public JSONObject getData() {
        if (mData == null) mData = new JSONObject();
        return mData;
    }

    @Override
    public UI setData(JSONObject data) {
        mData = data == null ? new JSONObject() : data;
        return this;
    }


    @Override
    public void createView(UIControl uiControl) {
        mView = onCreateView(uiControl);
        onViewCreate();
    }

    @Override
    public abstract View onCreateView(UIControl uiControl);

    @Override
    public void onViewCreate() {
        Log.d("UIView","onViewCreate");
    }

    @Override
    public void onShow() {
        Log.d("UIView","onShow");
    }

    @Override
    public void onHint() {
        Log.d("UIView","onHint");
        if (getView() != null) {
            InputMethodManager imm = (InputMethodManager) getView().getContext().getSystemService(Activity.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(getView().getWindowToken(), 0);
        }
    }

    @Override
    public boolean onBack() {
        Log.d("UIView","onBack");
        return false;
    }

    @Override
    public void onDestroy() {
        Log.d("UIView","onDestroy");
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d("UIView","onActivityResult");
    }

    @Override
    public void refresh() {
        Log.d("UIView","refresh");
    }

    @Override
    public void onRestart() {
        Log.d("UIView","onRestart");
    }

    @Override
    public void onStart() {
        Log.d("UIView","onStart");
    }

    @Override
    public void onResume() {
        Log.d("UIView","onResume");
    }

    @Override
    public void onPause() {
        Log.d("UIView","onPause");
    }

    @Override
    public void onStop() {
        Log.d("UIView","onStop");
    }

    @Override
    public <T extends View> T find(int id) {
        return (T) mView.findViewById(id);
    }

    @Override
    public void setView(View v) {
        mView = v;
    }

}
