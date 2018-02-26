package com.zd.newday.bean.zhihu;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ZhiHuDaily {
    @SerializedName("date")
    public String date;
    @SerializedName("top_stories")
    public ArrayList<ZhiHuDailyItem> mZhihuDailyItems;
    @SerializedName("stories")
    public ArrayList<ZhiHuDailyItem> stories;
}
