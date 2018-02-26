package com.zd.newdaylib.commonview;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.DataSetObserver;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListAdapter;

import com.zd.newdaylib.R;

/**
 * 类ListView LinearLayout
 * 主要用于嵌套ListView或ScrollView
 */
public class ViewGroupListView extends LinearLayout implements View.OnClickListener {

    public interface OnItemClickListener {
        void onItemClick(View viewGroup, View view, int position);
    }

    //控件是否需要点击事件
    private boolean mNeedOnClickListener = true;
    private OnItemClickListener mItemClickListener;
    private ListAdapter mAdapter;
    private List<View> mViewList = new ArrayList<View>();

    private DataSetObserver mDataSetObserver = new DataSetObserver() {
        @Override
        public void onChanged() {
            super.onChanged();
            bindView();
        }

        @Override
        public void onInvalidated() {
            super.onInvalidated();
            mViewList.clear();
            bindView();
        }
    };

    public ViewGroupListView(Context context) {
        super(context);
    }

    public ViewGroupListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mItemClickListener = listener;
    }

    public void setAdapter(ListAdapter adapter) {
        this.setAdapter(adapter, true);
    }

    public void setAdapter(ListAdapter adapter, boolean needOnClickListener) {
        this.mNeedOnClickListener = needOnClickListener;
        mViewList.clear();
        this.removeAllViews();
        if (mAdapter != null) {
            mAdapter.unregisterDataSetObserver(mDataSetObserver);
        }
        mAdapter = adapter;
        if (mAdapter != null) {
            mAdapter.registerDataSetObserver(mDataSetObserver);
        }
        bindView();
    }

    private void bindView() {
        if (mAdapter == null) {
            return;
        }
        int position = 0;
        for (; position < mAdapter.getCount(); position++) {
            if (mViewList.size() <= position) {
                View view = mAdapter.getView(position, null, null);
                mViewList.add(view);
                this.addView(view);
            } else {
                View convertView = mAdapter.getView(position, mViewList.get(position), null);
                // update view if adapter returns a different view.
                if (!convertView.equals(mViewList.get(position))) {
                    this.removeViewAt(position);
                    this.addView(convertView, position);
                    mViewList.set(position, convertView);
                }
            }
            mViewList.get(position).setVisibility(VISIBLE);
            mViewList.get(position).setTag(R.id.position, position);
            if (mNeedOnClickListener) {
                mViewList.get(position).setOnClickListener(this);
            }
        }
        // 隐藏多余的view
        for (; position < mViewList.size(); position++) {
            mViewList.get(position).setVisibility(GONE);
        }
    }

    @Override
    public void onClick(View v) {
        if (!(v.getTag(R.id.position) instanceof Integer)) {
            return;
        }
        if (mItemClickListener != null) {
            mItemClickListener.onItemClick(this, v, (Integer) v.getTag(R.id.position));
        }
    }
}
