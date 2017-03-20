package com.base.oneactivity.tool;

import android.support.annotation.NonNull;
import android.util.Log;

import com.base.oneactivity.ui.UI;
import com.base.oneactivity.ui.UIControl;
import com.base.oneactivity.ui.anim.AnimControl;
import com.base.oneactivity.ui.anim.LeftRightAnim;

import java.util.LinkedList;

/**
 * Created by Administrator on 2017/1/3
 */

public class UIUtil {
    private static LinkedList<UIControl> uiControls = new LinkedList<>();
    private static AnimControl defaultAnim;


    public static void init(@NonNull UIControl uiControl) {
        uiControls.add(uiControl);
        AppUtil.init(uiControl.getActivity().getApplication());
        defaultAnim = new LeftRightAnim();
    }

    public static void out(@NonNull UIControl uiControl) {
        uiControls.remove(uiControl);
        if (uiControls.size() < 1) {
            AppUtil.out();
        }
    }

    public static void finish() {
        uiControls.clear();
        AppUtil.out();
    }

    public static void show(@NonNull UI ui) {
        show(ui, defaultAnim.getInAnim());
    }

    public static void show(@NonNull UI ui, UIControl.ChangeAnimator animator) {
        if (uiControls.size() < 1) {
            Log.e("UIUtil", "加载UI出错name:" + ui.getName() + "\nuiControl:" + uiControls);
            return;
        }
        if (animator == null) {
            uiControls.getLast().show(ui);
        } else {
            uiControls.getLast().show(ui, animator);
        }
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
        back(defaultAnim.getOutAnim());
    }

    public static void back(UIControl.ChangeAnimator animator) {
        if (uiControls.size() < 1) return;
        if(animator==null){
            uiControls.getLast().back();
        }else {
            uiControls.getLast().back(animator);
        }
    }

    public static void setDefaultAnim(AnimControl defaultAnim) {
        UIUtil.defaultAnim = defaultAnim;
    }
}
