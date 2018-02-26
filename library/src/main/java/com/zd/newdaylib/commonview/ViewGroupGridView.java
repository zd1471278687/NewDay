package com.zd.newdaylib.commonview;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.DataSetObserver;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListAdapter;

import com.zd.newdaylib.R;

/**
 * 类GridView LinearLayout
 * 主要用于嵌套ListView或ScrollView
 */
public class ViewGroupGridView extends LinearLayout implements View.OnClickListener {

    public interface OnItemClickListener {
        void onItemClick(View viewGroup, View view, int position);
    }

    private ListAdapter mAdapter;
    // row
    private List<LinearLayout> mRowLinearLayout = new ArrayList<LinearLayout>();
    // item list
    private List<View> mViewList = new ArrayList<View>();
    private OnItemClickListener mItemClickListener;
    private int mDividerHeight = 0;
    private int mDividerColor = 0;
    private int mColumn = 1;
    private int mDividerWidth = 1;
    private int mColumnDividerWidth = 0;
    private boolean mIsNeedItemClick = true;

    private DataSetObserver mDataSetObserver = new DataSetObserver() {
        @Override
        public void onChanged() {
            super.onChanged();
            bindView();
        }

        @Override
        public void onInvalidated() {
            super.onInvalidated();
            mRowLinearLayout.clear();
            mViewList.clear();
            bindView();
        }
    };

    public ViewGroupGridView(Context context) {
        super(context);
        setOrientation(VERTICAL);
    }

    public final void setIsNeedItemClick(boolean isNeedItemClick) {
        mIsNeedItemClick = isNeedItemClick;
    }

    public void setDividerWidth(int dividerWidth) {
        mDividerWidth = dividerWidth;
    }

    public void setColumnDividerWidth(int columnDividerWidth) {
        mColumnDividerWidth = columnDividerWidth;
    }

    public void setColumn(int column) {
        mColumn = column;
    }

    public ViewGroupGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setOrientation(VERTICAL);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mItemClickListener = listener;
    }

    public void setAdapter(ListAdapter adapter) {
        mRowLinearLayout.clear();
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

    public ListAdapter getAdapter() {
        return mAdapter;
    }

    private void bindView() {
        if (mAdapter == null) {
            return;
        }
        int position = 0;
        for (; position < mAdapter.getCount(); position++) {
            LinearLayout row = getRowLayout(position);
            row.setVisibility(VISIBLE);
            if (mViewList.size() <= position) {
                if (position % mColumn > 0) { //add divider
                    row.addView(getColumnDivider());
                }
                View view = mAdapter.getView(position, null, null);
                view.setLayoutParams(new LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1));
                row.addView(view);
                mViewList.add(view);
            } else {
                View convertView = mAdapter.getView(position, mViewList.get(position), null);
                // update view if adapter returns a different view.
                if (!convertView.equals(mViewList.get(position))) {
                    convertView.setLayoutParams(new LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1));
                    int viewPos = getChildPosition(row, mViewList.get(position));
                    row.removeViewAt(viewPos);
                    row.addView(convertView, viewPos);
                    mViewList.set(position, convertView);
                }
            }
            mViewList.get(position).setVisibility(VISIBLE);
            mViewList.get(position).setTag(R.id.position, position);
            if (mIsNeedItemClick) {
                mViewList.get(position).setOnClickListener(this);
            }
        }
        // hide redundant views
        for (; position < mViewList.size(); position++) {
            if (position % mColumn == 0) {
                getRowLayout(position).setVisibility(GONE);
                position += mColumn - 1;
            } else {
                mViewList.get(position).setVisibility(GONE);
            }
        }
    }

    private int getChildPosition(ViewGroup parent, View view) {
        if (parent == null || view == null) {
            return -1;
        }
        for (int position = 0; position < parent.getChildCount(); position++) {
            if (view.equals(parent.getChildAt(position))) {
                return position;
            }
        }
        return -1;
    }

    private LinearLayout getRowLayout(int position) {
        if (position % mColumn == 0) {
            LinearLayout row = new LinearLayout(getContext());
            row.setWeightSum(mColumn);
            row.setOrientation(HORIZONTAL);
            row.setGravity(Gravity.CENTER_VERTICAL);
            row.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            if (position != 0 && mColumnDividerWidth > 0) {
                row.setPadding(0, mColumnDividerWidth, 0, 0);
            }
            mRowLinearLayout.add(row);
            this.addView(row);
        }
        return mRowLinearLayout.get(position / mColumn);
    }

    public void setDividerParams(int height, int color) {
        mDividerHeight = height;
        mDividerColor = color;
    }

    private View getColumnDivider() {
        View divider = new View(getContext());
        divider.setLayoutParams(new LayoutParams(mDividerWidth, mDividerHeight == 0 ? ViewGroup.LayoutParams.MATCH_PARENT : mDividerHeight));
        if (mDividerColor != 0) {
            divider.setBackgroundColor(getContext().getResources().getColor(mDividerColor));
        }
        return divider;
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
