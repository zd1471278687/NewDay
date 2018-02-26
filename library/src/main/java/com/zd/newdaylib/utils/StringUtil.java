package com.zd.newdaylib.utils;

import android.text.Html;
import android.text.Spanned;
import android.util.Log;

/**
 * string util
 * Created by zhangdong on 2017/11/8.
 */

public class StringUtil {

    /**
     * Don't let anyone instantiate this class
     */
    private StringUtil() {
    }

    /**
     * string is null or empty
     *
     * @param input string
     * @return true: empty false: not empty
     */
    public static boolean isNullOrEmpty(String input) {
        if (null == input || input.trim().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * string to html show string
     *
     * @param source source
     * @return string
     */
    public static String getHtmlString(String source) {
        if (isNullOrEmpty(source)) {
            return source;
        }
        try {
            Spanned spanned =  Html.fromHtml(source);
            return spanned.toString();
        } catch (Exception e) {
            Log.e(StringUtil.class.getSimpleName(), "string form html failed");
        }
        return source;
    }
}
