package com.base.oneactivity.ui;

import android.view.View;
import android.widget.TextView;

import com.base.oneactivity.R;
import com.base.oneactivity.tool.ScreenUtil;
import com.base.oneactivity.tool.UIUtil;

/**
 * Created by Administrator on 2017/1/3
 */
public class TestUI extends UIView {

    @Override
    public View onCreateView(UIControl uiControl) {
        return uiControl.getLayoutInflater().inflate(R.layout.activity_main, uiControl.getRoot(), false);
    }

    @Override
    public void onViewCreate() {
        super.onViewCreate();
        this.<TextView>find(R.id.sample_text).setText(String.valueOf(add(13, 31)));
        this.find(R.id.sample_text).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UIUtil.show(new TestUI(), new UIControl.ChangeAnimator()
                        .setOnChange(new UIControl.OnChange() {
                            @Override
                            public void action(UI one, UI two, Float three) {
                                one.getView().setTranslationX(three * ScreenUtil.getSceneWidth() * -1);
                                two.getView().setTranslationX((1 - three) * ScreenUtil.getSceneWidth());
                            }
                        })
                .setOnFinish(new UIControl.ResetUI() {
                    @Override
                    public void action(UI one, UI two) {
                        UIUtil.destroy(one);
                    }
                }));
            }
        });
    }

    private int add(int a, int b) {
        int s = a ^ b;
        int c = a & b;
        return c == 0 ? s : add(s, c << 1);
    }
}
