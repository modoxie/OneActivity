package com.base.example;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.base.oneactivity.tool.UIUtil;
import com.base.oneactivity.ui.UIControl;
import com.base.oneactivity.ui.UIView;

/**
 * Created by Administrator on 2017/3/15
 */

public class TestUI extends UIView {

    public TestUI(){

    }
    public TestUI(String name){
        super(name);
    }

    @Override
    public View onCreateView(UIControl uiControl) {
        return uiControl.getLayoutInflater().inflate(R.layout.activity_main, uiControl.getRoot(), false);
    }

    @Override
    public void onViewCreate() {
        super.onViewCreate();
        this.<TextView>find(R.id.sample_text).setText(getName()+Math.random());
        this.find(R.id.sample_text).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UIUtil.show(new TestUI());
            }
        });
    }
}

