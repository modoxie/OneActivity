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
        name=this.getClass().getSimpleName();
    }

    public UIView(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
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
        Log.d(name,"onViewCreate");
    }

    @Override
    public void onShow() {
        Log.d(name,"onShow");
    }

    @Override
    public void onHint() {
        Log.d(name,"onHint");
        if (getView() != null) {
            InputMethodManager imm = (InputMethodManager) getView().getContext().getSystemService(Activity.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(getView().getWindowToken(), 0);
        }
    }

    @Override
    public boolean onBack() {
        Log.d(name,"onBack");
        return false;
    }

    @Override
    public void onDestroy() {
        Log.d(name,"onDestroy");
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d(name,"onActivityResult");
    }

    @Override
    public void refresh() {
        Log.d(name,"refresh");
    }

    @Override
    public void onRestart() {
        Log.d(name,"onRestart");
    }

    @Override
    public void onStart() {
        Log.d(name,"onStart");
    }

    @Override
    public void onResume() {
        Log.d(name,"onResume");
    }

    @Override
    public void onPause() {
        Log.d(name,"onPause");
    }

    @Override
    public void onStop() {
        Log.d(name,"onStop");
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
