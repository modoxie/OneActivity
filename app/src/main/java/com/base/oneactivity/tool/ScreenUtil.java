package com.base.oneactivity.tool;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

/**
 * Created by Administrator on 2016/5/25
 */
public class ScreenUtil {
    private static DisplayMetrics getMetrics(Context context) {
        WindowManager winMgr = (WindowManager) context.getApplicationContext()
                .getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        winMgr.getDefaultDisplay().getMetrics(dm);
        return dm;
    }

    public static int getSceneWidth() {
        int SCREEN_WIDTH = getMetrics(AppUtil.getApplication()).widthPixels;
        return SCREEN_WIDTH;
    }

    public static int getSceneHeight() {
        int SCREEN_HEIGHT = getMetrics(AppUtil.getApplication()).heightPixels;
        return SCREEN_HEIGHT;
    }

    public static int dip2px(float dipValue) {
        final float scale = AppUtil.getApplication().getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

    public static int px2dip(float pxValue) {
        final float scale = AppUtil.getApplication().getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }
}
