package com.zd.newday.bean.zhihu;

import com.google.gson.annotations.SerializedName;

public class ZhiHuStory {
    @SerializedName("body")
    public String body;
    @SerializedName("title")
    public String title;
    @SerializedName("image")
    public String image;
    @SerializedName("share_url")
    public String mShareUrl;
    @SerializedName("css")
    public String[] css;
}
