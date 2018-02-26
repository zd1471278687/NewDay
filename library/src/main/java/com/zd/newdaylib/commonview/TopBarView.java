package com.zd.newdaylib.commonview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zd.newdaylib.R;
import com.zd.newdaylib.utils.StringUtil;

/**
 * 通用标题
 * Created by zhangdong on 2017/11/1.
 */

public class TopBarView extends RelativeLayout {
    //是否展示返回（默认不展示）
    private boolean mShowBack = false;
    private String mTitle;
    private OnClickListener mBackListener;
    private View mRootView;

    public TopBarView(Context context) {
        this(context, null);
    }

    public TopBarView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public void setBuilder() {
        if (mRootView != null) {
            ImageView backIv = (ImageView) mRootView.findViewById(R.id.iv_credit_bar_back);
            backIv.setOnClickListener(mBackListener);
            backIv.setVisibility(mShowBack ? VISIBLE : GONE);
            TextView titleTv = (TextView) mRootView.findViewById(R.id.tv_credit_title);
            titleTv.setText(StringUtil.isNullOrEmpty(mTitle) ? "" : mTitle);
        }
    }

    private void initView() {
        mRootView = LayoutInflater.from(getContext()).inflate(R.layout.view_top_bar, this);
    }

    /**
     * 创建BackModule的Builder类
     */
    public static class Builder {
        TopBarView mBackModule;

        /**
         * 构造函数
         *
         * @param topBarView 标题控件
         */
        public Builder(TopBarView topBarView) {
            this.mBackModule = topBarView;
        }

        /**
         * 设置back的点击事件
         *
         * @param onClickListener 点击事件
         * @return Builder
         */
        public Builder setBackClickListener(OnClickListener onClickListener) {
            mBackModule.mShowBack = onClickListener != null;
            mBackModule.mBackListener = onClickListener;
            return this;
        }

        /**
         * 设置文案,当style为TEXT的时候有效
         *
         * @param text 文案模式 back显示的文案
         * @return Builder
         */
        public Builder setText(String text) {
            mBackModule.mTitle = text;
            return this;
        }

        /**
         * 设置文案颜色,当style为TEXT的时候有效
         *
         * @param show 是否展示返回
         * @return Builder
         */
        public Builder showBack(boolean show) {
            mBackModule.mShowBack = show;
            return this;
        }

        public TopBarView build() {
            mBackModule.setBuilder();
            return mBackModule;
        }
    }
}
