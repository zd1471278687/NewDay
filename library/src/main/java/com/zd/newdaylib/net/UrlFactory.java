package com.zd.newdaylib.net;

/**
 * url factory
 * Created by zhangdong on 2017/11/20.
 */

public class UrlFactory {
    protected static final int DEFAULT_TIME_OUT = 10000;
    protected int mTimeout = DEFAULT_TIME_OUT;
    protected boolean mUseCache = false;
    //default is get
    protected boolean mIsPost = false;
    protected boolean mIsHttps = false;
    protected String mRelativePath;
    protected String mUrl;

    public UrlFactory() {
    }

    public int getTimeout() {
        return mTimeout;
    }

    public boolean isUseCache() {
        return mUseCache;
    }

    public boolean isPost() {
        return mIsPost;
    }

    public boolean isHttps() {
        return mIsHttps;
    }

    public String getUrl() {
        return mUrl;
    }
}
