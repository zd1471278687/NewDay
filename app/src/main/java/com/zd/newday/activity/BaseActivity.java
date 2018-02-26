package com.zd.newday.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;

import com.zd.newday.R;
import com.zd.newday.event.BaseEvent;
import com.zd.newdaylib.commonview.TopBarView;
import com.zd.newdaylib.dialog.IProgressDialog;
import com.zd.newdaylib.dialog.LoadingDialog;

import butterknife.ButterKnife;

/**
 * base activity
 * Created by zhangdong on 2017/11/8.
 */

public abstract class BaseActivity extends AppCompatActivity implements IProgressDialog {
    private static final String LOG_TAG = BaseActivity.class.getSimpleName();
    protected View mRootView;
    protected TopBarView mTopBarView;
    private volatile LoadingDialog mProgressDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mRootView = LayoutInflater.from(this).inflate(getContentView(), null);
        setContentView(mRootView);
        ButterKnife.bind(this);
        getIntentData();
        getIntentData(savedInstanceState);
        initPresenter();
        initHeaderView();
        initContentView();
        initFooterView();
        initSavedInstancesState(savedInstanceState);
        initData();
    }

    protected abstract int getContentView();

    protected abstract void initPresenter();

    protected abstract void initData();

    protected void initContentView() {
    }

    protected void initHeaderView() {
    }

    private void initFooterView() {
    }

    protected void getIntentData() {
    }

    protected void getIntentData(Bundle savedInstanceState) {
    }

    protected void initSavedInstancesState(Bundle savedInstanceState) {
    }

    protected void onEventMainThread(BaseEvent event) {
    }

    public void setViewVisible(View... views) {
        if (views == null) {
            return;
        }
        for (View view: views) {
            if (view != null) {
                view.setVisibility(View.VISIBLE);
            }
        }
    }

    public void setViewGone(View... views) {
        if (views == null) {
            return;
        }
        for (View view: views) {
            if (view != null) {
                view.setVisibility(View.GONE);
            }
        }
    }

    public synchronized void showProgressDialog(int resId) {
        showProgressDialog(resId, true);
    }

    public synchronized void showProgressDialog(final int resId, final boolean isCancel) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (mProgressDialog == null) {
                    mProgressDialog = new LoadingDialog(BaseActivity.this, R.style.loading_dialog);
                    mProgressDialog.setCancelable(true);
                }
                mProgressDialog.setCanceledOnTouchOutside(isCancel);
                mProgressDialog.setLoadingMessageRes(resId);
                if (!mProgressDialog.isShowing() && !isFinishing()) {
                    try {
                        mProgressDialog.show();
                    } catch (WindowManager.BadTokenException e) {
                        Log.e(LOG_TAG, "show progress dialog " + e.toString());
                    }
                }
            }
        });
    }

    public synchronized void showProgressDialog(String resString) {
        showProgressDialog(resString, true);
    }

    public synchronized void showProgressDialog(final String resString, final boolean isCancel) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (mProgressDialog == null) {
                    mProgressDialog = new LoadingDialog(BaseActivity.this, R.style.loading_dialog);
                    mProgressDialog.setCancelable(true);
                }
                mProgressDialog.setCanceledOnTouchOutside(isCancel);
                mProgressDialog.setLoadingMessageString(resString);
                if (!mProgressDialog.isShowing() && !isFinishing()) {
                    try {
                        mProgressDialog.show();
                    } catch (WindowManager.BadTokenException e) {
                        Log.e(LOG_TAG, "show progress dialog " + e.toString());
                    }
                }
            }
        });
    }

    public synchronized void dismissProgressDialog() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (mProgressDialog != null && mProgressDialog.isShowing() && !isFinishing()) {
                    mProgressDialog.dismiss();
                }
            }
        });
    }
}
