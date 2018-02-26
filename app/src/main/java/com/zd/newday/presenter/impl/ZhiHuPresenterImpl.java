package com.zd.newday.presenter.impl;

import android.content.Context;

import com.zd.newday.presenter.IZhiHuPresenter;
import com.zd.newday.view.ZhiHuView;

import rx.Subscription;

/**
 * 知乎
 * Created by zhangdong on 2017/11/20.
 */

public class ZhiHuPresenterImpl extends BasePresenterImpl implements IZhiHuPresenter {
    private Context mContext;
    private ZhiHuView mView;

    public ZhiHuPresenterImpl(Context context, ZhiHuView view) {
        this.mContext = context;
        this.mView = view;
    }

    @Override
    public void getLastZhihuNews() {
        if (mView != null) {
            mView.showProgress();
        }
//        Subscription subscription =
    }

    @Override
    public void getTheDaily(String date) {

    }
}
