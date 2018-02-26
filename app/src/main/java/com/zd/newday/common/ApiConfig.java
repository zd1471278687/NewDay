package com.zd.newday.common;

import com.zd.newdaylib.net.UrlFactory;

/**
 * API
 * Created by zhangdong on 2017/11/20.
 */

public class ApiConfig extends UrlFactory {

    /**
     * 构造接口常量，Build模式使用
     *
     * @param uri 接口的相对路径
     */
    private ApiConfig(String uri) {
        mRelativePath = uri;
    }

    private static ApiConfig with(String uri) {
        return new ApiConfig(uri);
    }

    private ApiConfig build() {
        StringBuilder urlBuilder = new StringBuilder();
        if (mIsHttps) {
            urlBuilder.append("https://");
        } else {
            urlBuilder.append("http://");
        }
        urlBuilder.append(mRelativePath);
        mUrl = urlBuilder.toString();
        return this;
    }

    public static final ApiConfig ZHIHU_LIST = ApiConfig.with("news-at.zhihu.com").build();
}
