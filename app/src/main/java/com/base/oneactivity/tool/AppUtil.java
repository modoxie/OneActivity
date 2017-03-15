package com.base.oneactivity.tool;

import android.app.Application;

/**
 * Created by Administrator on 2017/1/3
 */

public class AppUtil {
    public static Application application;

    public static void init(Application app) {
        application = app;
    }

    public static Application getApplication(){
        return application;
    }

    public static void out(){
        application=null;
    }
}
