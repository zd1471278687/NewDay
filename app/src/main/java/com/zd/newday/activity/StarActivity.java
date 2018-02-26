package com.zd.newday.activity;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.ConfigurationInfo;
import android.opengl.GLSurfaceView;

import com.zd.newday.R;
import com.zd.newdaylib.opengl.particlesys.ParticleSystemRenderer;

import butterknife.BindView;

/**
 * star activity
 * Created by zhangdong on 2017/11/10.
 */

public class StarActivity extends BaseActivity {

    @BindView(R.id.gl_surface_view)
    GLSurfaceView mGlSurfaceView;

    @Override
    protected int getContentView() {
        return R.layout.activity_star;
    }

    @Override
    protected void initPresenter() {

    }

    @Override
    protected void initContentView() {
        super.initContentView();
        // Check if the system supports OpenGL ES 2.0.
        final ActivityManager activityManager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        final ConfigurationInfo configurationInfo = activityManager.getDeviceConfigurationInfo();
        final boolean supportsEs2 = configurationInfo.reqGlEsVersion >= 0x20000;

        if (supportsEs2) {
            // Request an OpenGL ES 2.0 compatible context.
            mGlSurfaceView.setEGLContextClientVersion(2);

            // Set the renderer to our demo renderer, defined below.
            ParticleSystemRenderer mRenderer = new ParticleSystemRenderer(mGlSurfaceView, getApplicationContext());
            mGlSurfaceView.setRenderer(mRenderer);
            mGlSurfaceView.setRenderMode(GLSurfaceView.RENDERMODE_CONTINUOUSLY);
        } else {
            throw new UnsupportedOperationException();
        }
    }

    @Override
    protected void initData() {

    }
}
