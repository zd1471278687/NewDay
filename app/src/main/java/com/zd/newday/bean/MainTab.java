package com.zd.newday.bean;

import android.support.annotation.DrawableRes;

import com.zd.newday.R;
import com.zd.newdaylib.GlobalConstant;

/**
 * 首页底部tab数据
 * Created by zhangdong on 2017/11/18.
 */

public class MainTab {
    public String normal;
    public String press;
    public int id;
    public String title;

    @DrawableRes
    public int getDefaultNormalRes() {
        switch (id) {
            case GlobalConstant.PageTabId.TAB_HOME:
                return R.drawable.tab_home;
            case GlobalConstant.PageTabId.TAB_ZHIHU:
                return R.drawable.tab_loans;
            case GlobalConstant.PageTabId.TAB_NEWS:
                return R.drawable.tab_strategy;
            case GlobalConstant.PageTabId.TAB_MY:
                return R.drawable.tab_my;
            default:
                break;
        }
        return 0;
    }

    @DrawableRes
    public int getDefaultPressRes() {
        switch (id) {
            case GlobalConstant.PageTabId.TAB_HOME:
                return R.drawable.tab_home_press;
            case GlobalConstant.PageTabId.TAB_ZHIHU:
                return R.drawable.tab_loans_press;
            case GlobalConstant.PageTabId.TAB_NEWS:
                return R.drawable.tab_strategy_press;
            case GlobalConstant.PageTabId.TAB_MY:
                return R.drawable.tab_my_press;
            default:
                break;
        }
        return 0;
    }
}
