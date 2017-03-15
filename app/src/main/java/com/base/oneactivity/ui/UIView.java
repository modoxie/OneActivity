package com.base.oneactivity.ui;

import android.app.Activity;
import android.content.Intent;
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
    private UIControl uiControl;
    public static ExecutorService executorService;

    @Override
    public String getName() {
        return getClass().getSimpleName();
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
    public UIControl getUIControl() {
        return uiControl;
    }

    @Override
    public void setUIControl(UIControl uiControl) {
        this.uiControl = uiControl;
    }

    @Override
    public void createView(UIControl uiControl) {
        this.uiControl = uiControl;
        mView = onCreateView(uiControl);
        onViewCreate();
    }

    @Override
    public abstract View onCreateView(UIControl uiControl);

    @Override
    public void onViewCreate() {

    }

    @Override
    public void onShow() {

    }

    @Override
    public void onHint() {
        if (getView() != null) {
            InputMethodManager imm = (InputMethodManager) getView().getContext().getSystemService(Activity.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(getView().getWindowToken(), 0);
        }
    }

    @Override
    public boolean onBack() {
        return false;
    }

    @Override
    public void onDestroy() {
        uiControl = null;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

    }

    @Override
    public void refresh() {

    }

    @Override
    public void onRestart() {

    }

    @Override
    public void onStart() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onStop() {

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
