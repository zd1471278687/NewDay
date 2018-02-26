package com.zd.newday.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.zd.newday.R;
import com.zd.newday.bean.zhihu.ZhiHuDailyItem;
import com.zd.newday.utils.ExtendUtil;
import com.zd.newdaylib.baseadapter.ViewHolder;

import java.util.ArrayList;

/**
 * 知乎列表适配
 * Created by zhangdong on 2017/11/18.
 */

public class ZhiHuAdapter extends RecyclerView.Adapter<ViewHolder> {
    private Context mContext;
    private ArrayList<ZhiHuDailyItem> mList = new ArrayList<>();

    public ZhiHuAdapter(Context context) {
        mContext = context;
    }

    public void setData(ArrayList<ZhiHuDailyItem> list) {
        if (ExtendUtil.listIsNullOrEmpty(list)) {
            return;
        }
        mList.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(mContext, LayoutInflater.from(mContext).inflate(R.layout.list_item_zhihu_list, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ZhiHuDailyItem item = getItem(position);
        if (item == null) {
            return;
        }
        if (item.images != null && item.images.length > 0) {
            holder.setNdImageUrl(R.id.niv_news_image, item.images[0]);
        }
        holder.setText(R.id.tv_news_title, item.title);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public ZhiHuDailyItem getItem(int position) {
        if (position < 0 || position >= getItemCount()) {
            return null;
        }
        return mList.get(position);
    }
}
