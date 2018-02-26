package com.zd.newday.presenter;

/**
 * 知乎 presenter
 * Created by zhangdong on 2017/11/18.
 */

public interface IZhiHuPresenter extends BasePresenter {
    void getLastZhihuNews();

    void getTheDaily(String date);
}
