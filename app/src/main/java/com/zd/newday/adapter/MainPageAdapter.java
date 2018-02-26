package com.zd.newday.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;

import com.zd.newday.activity.MainActivity;
import com.zd.newday.fragment.HomeFragment;
import com.zd.newday.fragment.MyFragment;
import com.zd.newday.fragment.NewsFragment;
import com.zd.newday.fragment.ZhiHuFragment;
import com.zd.newdaylib.GlobalConstant;

import java.util.ArrayList;
import java.util.List;

/**
 * 首页Fragment 适配
 * Created by zhangdong on 2017/11/18.
 */

public class MainPageAdapter {
    //首页fragment总个数
    private static final int MAIN_FRAGMENT_SIZE = 4;
    private static final int DEFAULT_TAB = 0;
    //Fragment所属的Activity
    private FragmentActivity mFragmentActivity;
    //Activity中所要被替换的区域的id
    private int mCurrentFragmentId;
    //当前Tab页面索引
    private int mCurrentTab = DEFAULT_TAB;
    //一个tab页面对应一个Fragment
    private List<Fragment> mFragments = new ArrayList<>(MAIN_FRAGMENT_SIZE);
    //防止重复add fragment
    private int[] mStatus = {0, 0, 0, 0, 0};

    public MainPageAdapter(FragmentActivity fragmentActivity, int fragmentContentId, Bundle savedInstanceState) {
        this.mFragmentActivity = fragmentActivity;
        this.mCurrentFragmentId = fragmentContentId;
        FragmentManager fm = fragmentActivity.getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        if (null != savedInstanceState) {
            mCurrentTab = savedInstanceState.getInt(MainActivity.FRAGMENT_ITEM, 0);
        }
        //首页
        Fragment fragmentHome = mFragmentActivity.getSupportFragmentManager().findFragmentByTag(String.valueOf(GlobalConstant.PageTabId.TAB_HOME));
        if (null == fragmentHome) {
            fragmentHome = new HomeFragment();
        }
        mFragments.add(GlobalConstant.PageTabId.TAB_HOME, fragmentHome);

        //知乎
        Fragment fragmentZhiHu = mFragmentActivity.getSupportFragmentManager().findFragmentByTag(String.valueOf(GlobalConstant.PageTabId.TAB_ZHIHU));
        if (null == fragmentZhiHu) {
            fragmentZhiHu = new ZhiHuFragment();
        }
        mFragments.add(GlobalConstant.PageTabId.TAB_ZHIHU, fragmentZhiHu);

        //网易新闻
        Fragment fragmentNews = mFragmentActivity.getSupportFragmentManager().findFragmentByTag(String.valueOf(GlobalConstant.PageTabId.TAB_NEWS));
        if (null == fragmentNews) {
            fragmentNews = new NewsFragment();
        }
        mFragments.add(GlobalConstant.PageTabId.TAB_NEWS, fragmentNews);

        //我的
        Fragment fragmentMy = mFragmentActivity.getSupportFragmentManager().findFragmentByTag(String.valueOf(GlobalConstant.PageTabId.TAB_MY));
        if (null == fragmentMy) {
            fragmentMy = new MyFragment();
        }
        mFragments.add(GlobalConstant.PageTabId.TAB_MY, fragmentMy);

        // 恢复当前页
        Fragment fragment = mFragments.get(mCurrentTab);
        if (!fragment.isAdded()) {
            changeStatusToSelect(mCurrentTab);
            ft.add(mCurrentFragmentId, fragment, String.valueOf(mCurrentTab));
        }
        showTab(ft, mCurrentTab);
        ft.commitAllowingStateLoss();
    }
    /**
     * 切换fragment
     *
     * @param index fragment对应的tab索引.
     */
    public boolean switchFragment(int index) {
        Fragment fragment = mFragments.get(index);
        if (fragment == null) {
            return false;
        }
        FragmentTransaction ft = mFragmentActivity.getSupportFragmentManager().beginTransaction();
        Fragment currentFragment = getCurrentFragment();
        if (currentFragment != null && currentFragment.isVisible()) {
            currentFragment.onPause(); // 暂停当前tab
        }
        mCurrentTab = index;
        if (fragment.isAdded()) {
            fragment.onResume(); // 启动目标tab的onResume()
        } else {
            List<Fragment> fragments = mFragmentActivity.getSupportFragmentManager().getFragments();
            if (null != mFragmentActivity.getSupportFragmentManager().findFragmentByTag(String.valueOf(index)) ||
                    (mStatus.length > index && mStatus[index] == 1) || (null != fragments && fragments.contains(fragment))) {
                FragmentTransaction transaction = mFragmentActivity.getSupportFragmentManager().beginTransaction();
                transaction.remove(fragment).commitAllowingStateLoss();
                try {
                    mFragmentActivity.getSupportFragmentManager().executePendingTransactions();
                } catch (IllegalStateException e) {
                    Log.e(MainPageAdapter.class.getSimpleName(), e.toString());
                }
            }
            changeStatusToSelect(index);
            ft.add(mCurrentFragmentId, fragment, String.valueOf(index));
        }
        showTab(ft, index);
        ft.commitAllowingStateLoss();
        return true;
    }

    public Fragment getCurrentFragment() {
        return mFragments.get(mCurrentTab);
    }

    public int getCurrentFragmentIndex() {
        return mCurrentTab;
    }

    private void showTab(FragmentTransaction ft, int label) {
        for (int i = 0; i < mFragments.size(); i++) {
            Fragment fragment = mFragments.get(i);
            if (i == label) {
                ft.show(fragment);
            } else {
                ft.hide(fragment);
            }
        }
    }

    private void changeStatusToSelect(int index) {
        if (mStatus != null && index < mStatus.length) {
            mStatus[index] = 1;
        }
    }
}
