package com.zd.newdaylib.net;

/**
 * 默认请求的回调,onSuccess和onError都需要实现
 */

public abstract class ResCallBack<D> {
    public abstract void onSuccess(D d, boolean isFromCache);

    public abstract void onError(RestRequestException e);
}
