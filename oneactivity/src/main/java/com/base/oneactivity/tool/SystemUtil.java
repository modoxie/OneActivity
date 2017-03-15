package com.base.oneactivity.tool;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

/**
 * Created by Administrator on 2016/5/23
 */
public class SystemUtil {
    /**
     * 获取当前客户端版本信息
     */
    public static String getCurrentVersionName() {

        String versionName = "";
        try {
            // ---get the package info---
            PackageManager pm = AppUtil.getApplication().getPackageManager();
            PackageInfo pi = pm.getPackageInfo(AppUtil.getApplication().getPackageName(), 0);
            versionName = pi.versionName;
            if (versionName == null || versionName.length() <= 0) {
                return "";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return versionName;
    }
    /**
     * 获取当前客户端版本
     */
    public static int getCurrentVersionCode() {

        int versionName = 0;
        try {
            // ---get the package info---
            PackageManager pm = AppUtil.getApplication().getPackageManager();
            PackageInfo pi = pm.getPackageInfo( AppUtil.getApplication().getPackageName(), 0);
            versionName = pi.versionCode;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return versionName;
    }
    /**
     * 获取producer 生产厂家，是小米还是三星
     */
    public static String getProducer() {
        return android.os.Build.MANUFACTURER;
    }

    /**
     * 获取手机型号，如:M1 小米1
     */
    public static String getProducerModel() {
        return android.os.Build.MODEL;
    }

    /**
     * 获取系统版本
     */
    public static String getAndroidSystemVersion() {
        return android.os.Build.VERSION.RELEASE;
    }
    /**
     * 隐藏软键盘
     *
     * @param view
     */
    public static void hintSoftkeyboard(View view) {
        InputMethodManager imm = (InputMethodManager) AppUtil.getApplication()
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm.isActive()) {
            imm.showSoftInput(view, InputMethodManager.SHOW_FORCED);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0); // 隐藏键盘
        }
    }
    /**
     * 隐藏软键盘
     *
     * @param activity
     */
    public static void hintSoftkeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        // 隐藏软键盘
        if (activity.getWindow().getAttributes().softInputMode != WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN) {
            if (activity.getCurrentFocus() != null)
                imm.hideSoftInputFromWindow(
                        activity.getCurrentFocus().getWindowToken(),
                        InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }
}
