package com.zd.newdaylib;

import android.content.Context;

/**
 * app config
 * Created by zhangdong on 2017/11/20.
 */

public class AppConfigLib {
    private static Context sContext;

    public static void init(Context context) {
        sContext = context;
    }

    public static Context getContext() {
        return sContext;
    }
}
