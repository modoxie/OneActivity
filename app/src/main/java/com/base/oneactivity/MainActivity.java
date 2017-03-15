package com.base.oneactivity;

import android.os.Bundle;
import android.widget.TextView;

import com.base.oneactivity.tool.UIUtil;
import com.base.oneactivity.ui.AContext;
import com.base.oneactivity.ui.TestUI;

public class MainActivity extends AContext {

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(savedInstanceState==null){
            UIUtil.show(new TestUI());
        }
    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public native String stringFromJNI();
}
