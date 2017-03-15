package com.base.oneactivity.tool;

import android.support.annotation.NonNull;
import android.util.Log;

import com.base.oneactivity.ui.UI;
import com.base.oneactivity.ui.UIControl;

import org.json.JSONObject;

import java.lang.annotation.Annotation;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by Administrator on 2017/1/3
 */

public class UIUtil {
    private static UIControl mUiControl;

    public static void init(@NonNull UIControl uiControl) {
        mUiControl = uiControl;
        AppUtil.init(uiControl.getActivity().getApplication());
    }

    public static void out(@NonNull UIControl uiControl) {
        if (mUiControl == uiControl) {
            AppUtil.out();
            mUiControl = null;
        }
    }

    public static void show(@NonNull UI ui) {
        if (mUiControl == null) {
            Log.e("UIUtil", "加载UI出错name:" + ui.getName() + "\nuiControl:" + mUiControl);
            return;
        }
        mUiControl.show(ui);
    }

    public static void show(@NonNull UI ui, UIControl.ChangeAnimator animator) {
        if (mUiControl == null) {
            Log.e("UIUtil", "加载UI出错name:" + ui.getName() + "\nuiControl:" + mUiControl);
            return;
        }
        mUiControl.show(ui,animator);
    }
    public static void destroy(UI ui){
        if(ui==null) return;
        if (mUiControl == null) {
            Log.e("UIUtil", "加载UI出错name:" + ui.getName() + "\nuiControl:" + mUiControl);
            return;
        }
        mUiControl.destroy(ui);
    }
    public static void back() {
        if (mUiControl == null) return;
        mUiControl.back();
    }

    public static void back(UIControl.ChangeAnimator animator) {
        if (mUiControl == null) return;
        mUiControl.back(animator);
    }
}
