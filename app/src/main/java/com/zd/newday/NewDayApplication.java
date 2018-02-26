package com.zd.newday;

import android.app.Application;

import com.zd.newdaylib.AppConfigLib;

/**
 * application
 * Created by zhangdong on 2017/11/8.
 */

public class NewDayApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        AppConfigLib.init(this);
    }
}
