package com.zd.newdaylib.imageengine;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;

import com.bumptech.glide.Glide;
import com.zd.newdaylib.R;

/**
 * load network image
 * Created by zhangdong on 2017/11/18.
 */

public class NdImageView extends AppCompatImageView {

    public NdImageView(Context context) {
        this(context, null, -1);
    }

    public NdImageView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, -1);
    }

    public NdImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Deprecated
    @Override
    public void setTag(Object tag) { //this is for glide
        super.setTag(tag);
    }

    @Deprecated
    @Override
    public Object getTag() { //this is for glide
        return super.getTag();
    }

    /**
     * Displays an image given by the url
     *
     * @param url url of the image
     */
    public void setImageURL(String url) {
        Uri uri = url == null ? null : Uri.parse(url);
        Glide.with(getContext()).load(uri).into(this);
    }

    /**
     * Displays an image by resource id
     *
     * @param resId resource id
     */
    public void setImageResId(int resId) {
        if (resId <= 0) {
            return;
        }
        Glide.with(getContext()).load(resId).into(this);
    }

    /**
     * Displays an image by local path
     *
     * @param path local path of the image
     */
    public void setImageLocalPath(String path) {
        if (path == null) {
            return;
        }
        Glide.with(getContext()).load(path).into(this);
    }
}
