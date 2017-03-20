package com.base.oneactivity.ui.anim;

import com.base.oneactivity.ui.UIControl;

/**
 * Created by Administrator on 2017/3/20
 */

public interface AnimControl {
   UIControl.ChangeAnimator getInAnim();
   UIControl.ChangeAnimator getOutAnim();
}
