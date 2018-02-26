package com.zd.newdaylib.net;

/**
 * 接口请求异常
 */

public class RestRequestException extends Exception {
    private int restErrorCode;
    private int mHttpCode;
    private String mErrorMsg;
    private Object mErrorData;

    public int getRestErrorCode() {
        return restErrorCode;
    }

    public void setRestErrorCode(int restErrorCode) {
        this.restErrorCode = restErrorCode;
    }

    public int getmHttpCode() {
        return mHttpCode;
    }

    public void setmHttpCode(int mHttpCode) {
        this.mHttpCode = mHttpCode;
    }

    public String getmErrorMsg() {
        return mErrorMsg;
    }

    public void setmErrorMsg(String mErrorMsg) {
        this.mErrorMsg = mErrorMsg;
    }

    public Object getmErrorData() {
        return mErrorData;
    }

    public void setmErrorData(Object mErrorData) {
        this.mErrorData = mErrorData;
    }
}
