package com.zd.newdaylib.dialog;

/**
 * 确保只有Activity才实现这个接口
 */
public interface IProgressDialog {

    void showProgressDialog(int resId);

    void showProgressDialog(int resId, boolean isCancelable);

    void showProgressDialog(String resString);

    void showProgressDialog(String resString, boolean isCancelable);

    void dismissProgressDialog();
}
