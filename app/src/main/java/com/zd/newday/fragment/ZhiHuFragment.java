package com.zd.newday.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.zd.newday.R;
import com.zd.newday.adapter.ZhiHuAdapter;
import com.zd.newday.bean.zhihu.ZhiHuDaily;
import com.zd.newday.presenter.impl.ZhiHuPresenterImpl;
import com.zd.newday.view.ZhiHuView;

import butterknife.BindView;

/**
 * 首页
 * Created by zhangdong on 2017/11/18.
 */

public class ZhiHuFragment extends BaseFragment implements ZhiHuView {
    @BindView(R.id.rv_zhi_hu)
    RecyclerView mZhiHuRv;
    private ZhiHuAdapter mAdapter;
    private ZhiHuPresenterImpl mPresenter;

    @Override
    protected int getContentLayout() {
        return R.layout.fragment_zhihu;
    }

    @Override
    protected void initData() {
        mAdapter = new ZhiHuAdapter(getActivity());
        mZhiHuRv.setLayoutManager(new LinearLayoutManager(getContext()));
        mZhiHuRv.setAdapter(mAdapter);
    }

    @Override
    protected void initPresenter() {
        mPresenter = new ZhiHuPresenterImpl(getActivity(), this);
    }

    @Override
    public void showProgress() {
        showProgressDialog(R.string.loading);
    }

    @Override
    public void hideProgress() {
        dismissProgressDialog();
    }

    @Override
    public void showErrorView(boolean show) {

    }

    @Override
    public void initNewsList(ZhiHuDaily zhiHuDaily) {

    }
}
