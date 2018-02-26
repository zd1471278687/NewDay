package com.zd.newday.bean.zhihu;

import com.google.gson.annotations.SerializedName;

public class ZhiHuDailyItem {
    @SerializedName("images")
    public String[] images;
    @SerializedName("type")
    public int type;
    @SerializedName("id")
    public String id;
    @SerializedName("title")
    public String title;
    public String date;
}
