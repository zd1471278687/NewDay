package com.zd.newdaylib.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.util.DisplayMetrics;
import android.util.Log;

import java.lang.reflect.Field;

/**
 * screen util
 * Created by zhangdong on 2017/11/8.
 */

public class ScreenUtil {
    private static final String LOG_TAG = ScreenUtil.class.getSimpleName();

    /**
     * Don't let anyone instantiate this class
     */
    private ScreenUtil() {
    }

    /**
     * dp -> px
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * px -> dp
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * 获取状态栏/通知栏的高度
     *
     * @return px 状态栏高度
     */
    public static int getStatusBarHeight(Context context) {
        if (context instanceof Activity) {
            Rect frame = new Rect();
            ((Activity) context).getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
            int statusBarHeight = frame.top;
            if (statusBarHeight > 0) {
                return statusBarHeight;
            }

            // 反射获取高度
            try {
                Class<?> c = Class.forName("com.android.internal.R$dimen");
                Object obj = c.newInstance();
                Field field = c.getField("status_bar_height");
                int x = Integer.parseInt(field.get(obj).toString());
                statusBarHeight = context.getResources().getDimensionPixelSize(x);
                return statusBarHeight;
            } catch (Exception e) {
                Log.e(LOG_TAG, "get status bar height error.");
            }
        }

        // 以上均失效时，使用默认高度为25dp。
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        return (int) Math.ceil(25 * metrics.density);
    }
}
