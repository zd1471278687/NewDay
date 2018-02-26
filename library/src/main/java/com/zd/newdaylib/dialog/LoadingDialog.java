package com.zd.newdaylib.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.StringRes;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.zd.newdaylib.R;
import com.zd.newdaylib.commonview.circleprogress.CircleProgressView;
import com.zd.newdaylib.utils.ScreenUtil;

/**
 * 加载动画
 * Created by zhangdong on 2017/4/25.
 */

public class LoadingDialog extends Dialog implements DialogInterface.OnDismissListener, DialogInterface.OnShowListener {
    private CircleProgressView mLoadingCpv;
    private TextView mLoadingTv;

    public LoadingDialog(Context context, int themeResId) {
        super(context, themeResId);
        initView();
    }

    public LoadingDialog(Context context) {
        super(context, 0);
    }

    public void setLoadingMessageRes(@StringRes int resId) {
        if (mLoadingTv != null) {
            mLoadingTv.setText(resId);
        }
    }

    public void setLoadingMessageString(String message) {
        if (mLoadingTv != null) {
            mLoadingTv.setText(message);
        }
    }

    private void initView() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.loading_dialog, null);
        setContentView(view);
        mLoadingCpv = (CircleProgressView) view.findViewById(R.id.cpv_loading);
        mLoadingCpv.setThickness(ScreenUtil.dip2px(getContext(), 4)); //设置宽度
        mLoadingCpv.setIndeterminate(true); //循环
        mLoadingCpv.setColor(getContext().getResources().getColor(R.color.colorPrimaryDark));
        mLoadingTv = (TextView) view.findViewById(R.id.tv_loading_text);
        setOnDismissListener(this);
        setOnShowListener(this);
    }

    @Override
    public void onDismiss(DialogInterface dialogInterface) {
        if (mLoadingCpv != null) {
            mLoadingCpv.setVisibility(View.GONE);
            mLoadingCpv.stopAnimation();
        }
    }

    @Override
    public void onShow(DialogInterface dialogInterface) {
        if (mLoadingCpv != null) {
            mLoadingCpv.setVisibility(View.VISIBLE);
            mLoadingCpv.startAnimation();
        }
    }
}
