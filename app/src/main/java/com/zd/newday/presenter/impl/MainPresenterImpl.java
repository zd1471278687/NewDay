package com.zd.newday.presenter.impl;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.zd.newday.adapter.BottomTabAdapter;
import com.zd.newday.R;
import com.zd.newday.bean.MainTab;
import com.zd.newday.utils.ExtendUtil;
import com.zd.newday.view.MainView;
import com.zd.newdaylib.GlobalConstant;
import com.zd.newdaylib.baseadapter.RecyclerViewOnItemClickListener;

import java.util.ArrayList;
import java.util.List;

/**
 * main presenter
 * Created by zhangdong on 2017/11/18.
 */

public class MainPresenterImpl extends BasePresenterImpl implements RecyclerViewOnItemClickListener {
    private Context mContext;
    private MainView mView;
    private BottomTabAdapter mTabAdapter;

    public MainPresenterImpl(Context context, MainView view) {
        this.mContext = context;
        this.mView = view;
    }

    public void initBottomTab(RecyclerView bottomTabView) {
        List<MainTab> loansMainTabList = getDefaultLoansTab();
        if (!ExtendUtil.listIsNullOrEmpty(loansMainTabList)) {
            mTabAdapter = new BottomTabAdapter(mContext);
            mTabAdapter.setData(loansMainTabList);
            mTabAdapter.setOnItemClickListener(this);
            bottomTabView.setLayoutManager(new GridLayoutManager(mContext, loansMainTabList.size()));
            bottomTabView.setAdapter(mTabAdapter);
        }
    }

    private List<MainTab> getDefaultLoansTab() {
        List<MainTab> loansMainTabList = new ArrayList<>();
        MainTab homeTab = new MainTab();
        homeTab.id = GlobalConstant.PageTabId.TAB_HOME;
        homeTab.normal = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1513251360755&di=f5ad217c55a5629366a1e177b926f655&imgtype=0&src=http%3A%2F%2Fpic.58pic.com%2F58pic%2F12%2F50%2F33%2F61s58PICaSK.jpg";
        homeTab.title = mContext.getString(R.string.tab_home);
        loansMainTabList.add(homeTab);
        MainTab goodsTab = new MainTab();
        goodsTab.id = GlobalConstant.PageTabId.TAB_ZHIHU;
        goodsTab.title = mContext.getString(R.string.tab_know);
        loansMainTabList.add(goodsTab);
        MainTab strategyTab = new MainTab();
        strategyTab.id = GlobalConstant.PageTabId.TAB_NEWS;
        strategyTab.title = mContext.getString(R.string.tab_news);
        loansMainTabList.add(strategyTab);
        MainTab myTab = new MainTab();
        myTab.id = GlobalConstant.PageTabId.TAB_MY;
        myTab.title = mContext.getString(R.string.tab_my);
        loansMainTabList.add(myTab);
        return loansMainTabList;
    }

    @Override
    public void onItemClickListener(View view, int position) {
        if (mTabAdapter != null) {
            mTabAdapter.setCurrentFocusItem(position);
            if (mView != null) {
                MainTab mainTab = mTabAdapter.getItem(position);
                mView.changeMainTab(mainTab.id); //切换tab
            }
        }
    }
}
