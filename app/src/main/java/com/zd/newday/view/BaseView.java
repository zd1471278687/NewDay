package com.zd.newday.view;

/**
 * base view
 * Created by zhangdong on 2017/11/18.
 */

public interface BaseView {
    void showProgress();

    void hideProgress();

    void showErrorView(boolean show);
}
