package com.zd.newdaylib.baseadapter.recyclerview;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.view.ViewGroup;

import com.zd.newdaylib.R;
import com.zd.newdaylib.baseadapter.ViewHolder;
import com.zd.newdaylib.commonview.circleprogress.CircleProgressView;

/**
 * 加载更多
 * Created by zhangdong on 2017/8/24.
 */
public class LoadMoreWrapper<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public static final int ITEM_TYPE_LOAD_MORE = Integer.MAX_VALUE - 2;

    private RecyclerView.Adapter mInnerAdapter;
    private View mLoadMoreView;
    //是否是最后一页
    private boolean mIsLastPage = false;
    //是否使用默认的布局样式
    private boolean mUseDefaultLayoutId = true;
    //是否需要展示更多（用于控制无数据时是否展示）
    private boolean mNeedMoreView = true;
    //设置一个默认值
    private int mLoadMoreLayoutId = R.layout.view_default_load_more;
    private OnLoadMoreListener mOnLoadMoreListener;

    public LoadMoreWrapper(RecyclerView.Adapter adapter) {
        mInnerAdapter = adapter;
    }

    private boolean hasLoadMore() {
        return (mLoadMoreView != null || mLoadMoreLayoutId != 0) && mNeedMoreView;
    }


    private boolean isShowLoadMore(int position) {
        return hasLoadMore() && (position >= mInnerAdapter.getItemCount());
    }

    @Override
    public int getItemViewType(int position) {
        if (isShowLoadMore(position)) {
            return ITEM_TYPE_LOAD_MORE;
        }
        return mInnerAdapter.getItemViewType(position);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == ITEM_TYPE_LOAD_MORE) {
            ViewHolder holder;
            if (mLoadMoreView != null) {
                holder = ViewHolder.createViewHolder(parent.getContext(), mLoadMoreView);
            } else {
                holder = ViewHolder.createViewHolder(parent.getContext(), parent, mLoadMoreLayoutId);
            }
            return holder;
        }
        return mInnerAdapter.onCreateViewHolder(parent, viewType);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (isShowLoadMore(position)) {
            if (mOnLoadMoreListener != null) {
                mOnLoadMoreListener.onLoadMoreRequested();
            }
            if (mLoadMoreLayoutId == R.layout.view_default_load_more && mUseDefaultLayoutId && holder instanceof ViewHolder) {
                initMoreView((ViewHolder) holder); //设置默认的加载更多样式，用户设置的不处理
            }
            return;
        }
        mInnerAdapter.onBindViewHolder(holder, position);
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        WrapperUtils.onAttachedToRecyclerView(mInnerAdapter, recyclerView, new WrapperUtils.SpanSizeCallback() {
            @Override
            public int getSpanSize(GridLayoutManager layoutManager, GridLayoutManager.SpanSizeLookup oldLookup, int position) {
                if (isShowLoadMore(position)) {
                    return layoutManager.getSpanCount();
                }
                if (oldLookup != null) {
                    return oldLookup.getSpanSize(position);
                }
                return 1;
            }
        });
    }


    @Override
    public void onViewAttachedToWindow(RecyclerView.ViewHolder holder) {
        mInnerAdapter.onViewAttachedToWindow(holder);
        if (isShowLoadMore(holder.getLayoutPosition())) {
            setFullSpan(holder);
        }
    }

    private void setFullSpan(RecyclerView.ViewHolder holder) {
        ViewGroup.LayoutParams lp = holder.itemView.getLayoutParams();

        if (lp != null
                && lp instanceof StaggeredGridLayoutManager.LayoutParams) {
            StaggeredGridLayoutManager.LayoutParams p = (StaggeredGridLayoutManager.LayoutParams) lp;
            p.setFullSpan(true);
        }
    }

    private void initMoreView(ViewHolder holder) {
        if (holder == null) {
            return;
        }
        View view = holder.getView(R.id.cpv_default_loading);
        CircleProgressView circleProgressView = null;
        if (view instanceof CircleProgressView) {
            circleProgressView = (CircleProgressView) view;
            circleProgressView.startAnimation();
        }
        if (mIsLastPage) {
            holder.setVisible(R.id.ll_default_load_next_page, false);
            holder.setVisible(R.id.tv_default_no_more, true);
            if (circleProgressView != null) {
                circleProgressView.stopAnimation();
            }
        } else {
            holder.setVisible(R.id.ll_default_load_next_page, true);
            holder.setVisible(R.id.tv_default_no_more, false);
        }

    }

    @Override
    public int getItemCount() {
        return mInnerAdapter.getItemCount() + (hasLoadMore() ? 1 : 0);
    }

    public interface OnLoadMoreListener {
        void onLoadMoreRequested();
    }

    public LoadMoreWrapper setOnLoadMoreListener(OnLoadMoreListener loadMoreListener) {
        if (loadMoreListener != null) {
            mOnLoadMoreListener = loadMoreListener;
        }
        return this;
    }

    public LoadMoreWrapper setLoadMoreView(View loadMoreView) {
        mUseDefaultLayoutId = false;
        mLoadMoreView = loadMoreView;
        return this;
    }

    public LoadMoreWrapper setLoadMoreView(int layoutId) {
        mUseDefaultLayoutId = false;
        mLoadMoreLayoutId = layoutId;
        return this;
    }

    public boolean ismIsLastPage() {
        return mIsLastPage;
    }

    public void setmIsLastPage(boolean mIsLastPage) {
        this.mIsLastPage = mIsLastPage;
    }

    public boolean ismNeedMoreView() {
        return mNeedMoreView;
    }

    public void setmNeedMoreView(boolean mNeedMoreView) {
        this.mNeedMoreView = mNeedMoreView;
    }
}
