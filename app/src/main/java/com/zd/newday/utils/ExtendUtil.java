package com.zd.newday.utils;

import java.util.Collection;
import java.util.List;

/**
 * extend util
 * Created by zhangdong on 2017/11/8.
 */

public class ExtendUtil {

    /**
     * Don't let anyone instantiate this class
     */
    private ExtendUtil() {
    }

    /**
     * judge list is null or empty
     *
     * @param collection data
     * @return true: is empty false: not empty
     */
    public static boolean listIsNullOrEmpty(Collection<?> collection) {
        return collection == null || collection.isEmpty();
    }

    /**
     * remove null data from list
     *
     * @param list list
     * @return list
     */
    public static <T> List<T> removeNull(List<T> list) {
        if (list != null) {
            for (int pos = list.size() - 1; pos >= 0; pos--) {
                if (list.get(pos) == null) {
                    list.remove(pos);
                }
            }
        }
        return list;
    }
}
