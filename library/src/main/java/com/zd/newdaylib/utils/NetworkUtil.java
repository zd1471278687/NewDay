package com.zd.newdaylib.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * network
 * Created by zhangdong on 2017/11/20.
 */

public class NetworkUtil {
    /**
     * Don't let anyone instantiate this class
     */
    private NetworkUtil() {}

    /**
     * judge whether has network connection
     *
     * @param context
     * @return true/false
     */
    public static boolean isNetWorkAvailable(Context context) {
        ConnectivityManager conn = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = conn.getActiveNetworkInfo();
        return (info != null && info.isConnected());
    }
}
