package com.base.oneactivity.tool;

import android.support.annotation.NonNull;
import android.util.Log;

import com.base.oneactivity.ui.UI;
import com.base.oneactivity.ui.UIControl;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Administrator on 2017/1/3
 */

public class UIUtil {
    private static LinkedList<UIControl> uiControls = new LinkedList<>();

    public static void init(@NonNull UIControl uiControl) {
        uiControls.add(uiControl);
        AppUtil.init(uiControl.getActivity().getApplication());
    }

    public static void out(@NonNull UIControl uiControl) {
        uiControls.remove(uiControl);
        if (uiControls.size() < 1) {
            AppUtil.out();
        }
    }

    public static void finish(){
        uiControls.clear();
        AppUtil.out();
    }

    public static void show(@NonNull UI ui) {
        if (uiControls.size() < 1) {
            Log.e("UIUtil", "加载UI出错name:" + ui.getName() + "\nuiControl:" + uiControls);
            return;
        }
        uiControls.getLast().show(ui);
    }

    public static void show(@NonNull UI ui, UIControl.ChangeAnimator animator) {
        if (uiControls.size() < 1) {
            Log.e("UIUtil", "加载UI出错name:" + ui.getName() + "\nuiControl:" + uiControls);
            return;
        }
        uiControls.getLast().show(ui, animator);
    }

    public static void destroy(UI ui) {
        if (ui == null) return;
        if (uiControls.size() < 1) {
            Log.e("UIUtil", "加载UI出错name:" + ui.getName() + "\nuiControl:" + uiControls);
            return;
        }
        uiControls.getLast().destroy(ui);
    }

    public static void back() {
        if (uiControls.size() < 1) return;
        uiControls.getLast().back();
    }

    public static void back(UIControl.ChangeAnimator animator) {
        if (uiControls.size() < 1) return;
        uiControls.getLast().back(animator);
    }
}
