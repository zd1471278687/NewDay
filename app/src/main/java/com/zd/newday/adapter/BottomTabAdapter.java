package com.zd.newday.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zd.newday.R;
import com.zd.newday.bean.MainTab;
import com.zd.newday.utils.ExtendUtil;
import com.zd.newdaylib.utils.StringUtil;
import com.zd.newdaylib.baseadapter.RecyclerViewOnItemClickListener;
import com.zd.newdaylib.baseadapter.ViewHolder;
import com.zd.newdaylib.imageengine.NdImageView;

import java.util.List;

/**
 * 首页底部tab适配
 * Created by zhangdong on 2017/11/18.
 */

public class BottomTabAdapter extends RecyclerView.Adapter<ViewHolder> implements View.OnClickListener  {
    private Context mContext;
    private List<MainTab> mList;
    private int mCurrentFocusItem = 0;
    private RecyclerViewOnItemClickListener mOnItemClickListener;

    public BottomTabAdapter(Context context) {
        this.mContext = context;
    }

    public void setData(List<MainTab> list) {
        mList = ExtendUtil.removeNull(list);
    }

    public void setCurrentFocusItem(int currentFocusItem) {
        this.mCurrentFocusItem = currentFocusItem;
        notifyDataSetChanged();
    }

    public void setOnItemClickListener(RecyclerViewOnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(mContext, LayoutInflater.from(mContext).inflate(R.layout.list_item_home_bottom, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        MainTab item = getItem(position);
        if (item == null) {
            return;
        }
        View iconView = holder.getView(R.id.bottom_menu_image);
        if (iconView instanceof NdImageView) {
            NdImageView iconIv = (NdImageView) iconView;
            setTabIcon(item, position, iconIv);
            iconIv.setTag(R.id.glide_image_tag, mCurrentFocusItem == position); //必须设置key，好坑
        }
        holder.setTextColor(R.id.bottom_menu_text,
                ContextCompat.getColor(mContext, mCurrentFocusItem == position ? R.color.blue_03a9f4
                        : R.color.gray_999999));
        holder.setText(R.id.bottom_menu_text, item.title);
        holder.setTag(R.id.ll_tab_root, R.id.position, position);
        holder.setOnClickListener(R.id.ll_tab_root, this);
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    public MainTab getItem(int position) {
        if (position < 0 || position >= getItemCount()) {
            return null;
        }
        return mList.get(position);
    }

    /**
     * 设置图标
     *
     * @param item     tab数据
     * @param position 位置信息
     * @param iconView 图片控件
     */
    private void setTabIcon(MainTab item, int position, NdImageView iconView) {
        Object tagObj = iconView.getTag(R.id.glide_image_tag);
        if (mCurrentFocusItem == position) {
            if (tagObj instanceof Boolean && (boolean) tagObj) {
                //当前被选中不再更新
                return;
            }
            if (StringUtil.isNullOrEmpty(item.press)) {
                iconView.setImageResId(item.getDefaultPressRes());
            } else {
                iconView.setImageURL(item.press);
            }
        } else {
            if (tagObj instanceof Boolean && !(boolean) tagObj) {
                //当前未被选中不再更新
                return;
            }
            if (StringUtil.isNullOrEmpty(item.normal)) {
                iconView.setImageResId(item.getDefaultNormalRes());
            } else {
                iconView.setImageURL(item.normal);
            }
        }
    }

    @Override
    public void onClick(View view) {
        int i = view.getId();
        if (i == R.id.ll_tab_root) {
            Object positionObject = view.getTag(R.id.position);
            if (mOnItemClickListener != null && positionObject instanceof Integer) {
                mOnItemClickListener.onItemClickListener(view, (int) positionObject);
            }
        }
    }
}
