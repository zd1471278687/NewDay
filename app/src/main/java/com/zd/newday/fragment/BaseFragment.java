package com.zd.newday.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zd.newdaylib.dialog.IProgressDialog;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * base fragment
 * Created by zhangdong on 2017/11/18.
 */

public abstract class BaseFragment extends Fragment {
    protected View mRootView;
    protected Unbinder mUnbinder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView = inflater.inflate(getContentLayout(), null);
        mUnbinder = ButterKnife.bind(this, mRootView);
        initHeaderView();
        initFooterView();
        initContentView();
        initPresenter();
        initData();
        return mRootView;
    }

    @Override
    public void onDestroyView() {
        mUnbinder.unbind();
        super.onDestroyView();
    }

    protected abstract int getContentLayout();

    protected abstract void initData();

    protected abstract void initPresenter();

    protected void initContentView() {
    }

    protected void initHeaderView() {
    }

    protected void initFooterView() {
    }

    public void showProgressDialog(int resId) {
        Activity parent = getActivity();
        if (parent instanceof IProgressDialog) {
            ((IProgressDialog) parent).showProgressDialog(resId);
        }
    }

    public void showProgressDialog(int resId, boolean isCancelable) {
        Activity parent = getActivity();
        if (parent instanceof IProgressDialog) {
            ((IProgressDialog) parent).showProgressDialog(resId, isCancelable);
        }
    }

    public void showProgressDialog(String resString) {
        Activity parent = getActivity();
        if (parent instanceof IProgressDialog) {
            ((IProgressDialog) parent).showProgressDialog(resString);
        }
    }

    public void showProgressDialog(String resString, boolean isCancelable) {
        Activity parent = getActivity();
        if (parent instanceof IProgressDialog) {
            ((IProgressDialog) parent).showProgressDialog(resString, isCancelable);
        }
    }

    public void dismissProgressDialog() {
        Activity parent = getActivity();
        if (parent instanceof IProgressDialog) {
            ((IProgressDialog) parent).dismissProgressDialog();
        }
    }
}
