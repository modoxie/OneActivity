package com.base.example;

import android.os.Bundle;

import com.base.oneactivity.tool.UIUtil;
import com.base.oneactivity.ui.AContext;

/**
 * Created by Administrator on 2017/3/15
 */

public class MainActivity extends AContext{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(savedInstanceState==null){
            UIUtil.show(new TestUI());
        }
    }

}
