package com.base.oneactivity.ui;

import android.content.Intent;
import android.view.View;

import org.json.JSONObject;

/**
 * Created by Administrator on 2016/6/22
 */
public interface UI{
    String getName();
    View getView();
    void setView(View v);
    int getID();
    JSONObject getData();
    UI setData(JSONObject data);
    void createView(UIControl uiControl);
    View onCreateView(UIControl uiControl);
    void refresh();
    void onViewCreate();
    void onShow();
    void onHint();
    void onRestart();
    void onStart();
    void onResume();
    void onPause();
    void onStop();
    void onDestroy();
    boolean onBack();
    void onActivityResult(int requestCode, int resultCode, Intent data);
    <T extends View> T find(int id);
}
