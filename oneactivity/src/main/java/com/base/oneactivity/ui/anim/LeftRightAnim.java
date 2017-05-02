package com.base.oneactivity.ui.anim;

import com.base.oneactivity.tool.ScreenUtil;
import com.base.oneactivity.ui.UI;
import com.base.oneactivity.ui.UIControl;

/**
 * Created by Administrator on 2017/3/20
 */

public class LeftRightAnim implements AnimControl {

    int sceneWidth = ScreenUtil.getSceneWidth();

    public UIControl.ChangeAnimator getInAnim() {
        return new UIControl.ChangeAnimator() {

            @Override
            public UIControl.OnChange getOnChange() {
                return new UIControl.OnChange() {
                    @Override
                    public void action(UI one, UI two, Float three) {
                        if (one != null && two != null) {
                            one.getView().setTranslationX(-sceneWidth * three);
                            two.getView().setTranslationX(sceneWidth * (1 - three));
                        }
                    }
                };
            }

            @Override
            public UIControl.OnStart getOnStart() {
                return new UIControl.OnStart() {
                    @Override
                    public void action(UI one, UI two) {
                        if (one != null && two != null) {
                            one.getView().setTranslationX(0);
                            two.getView().setTranslationX(sceneWidth);
                        }
                    }
                };
            }

            @Override
            public UIControl.OnFinish getOnFinish() {
                return new UIControl.OnFinish() {
                    @Override
                    public void action(UI one, UI two) {
                        if (one != null && two != null) {
                            one.getView().setTranslationX(0);
                            two.getView().setTranslationX(0);
                        }
                    }
                };
            }
        };
    }

    public UIControl.ChangeAnimator getOutAnim() {
        return new UIControl.ChangeAnimator() {

            @Override
            public UIControl.OnChange getOnChange() {
                return new UIControl.OnChange() {
                    @Override
                    public void action(UI one, UI two, Float three) {
                        if (one != null && two != null) {
                            two.getView().setTranslationX(-sceneWidth * (1 - three));
                            one.getView().setTranslationX(sceneWidth * three);
                        }
                    }
                };
            }

            @Override
            public UIControl.OnStart getOnStart() {
                return new UIControl.OnStart() {
                    @Override
                    public void action(UI one, UI two) {
                        if (one != null && two != null) {
                            two.getView().setTranslationX(-sceneWidth);
                            one.getView().setTranslationX(0);
                        }
                    }
                };
            }

            @Override
            public UIControl.OnFinish getOnFinish() {
                return new UIControl.OnFinish() {
                    @Override
                    public void action(UI one, UI two) {
                        if (one != null && two != null) {
                            one.getView().setTranslationX(0);
                            two.getView().setTranslationX(0);
                        }
                    }
                };
            }
        };
    }
}
