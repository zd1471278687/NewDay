package com.zd.newday.activity;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import com.zd.newday.R;
import com.zd.newday.adapter.MainPageAdapter;
import com.zd.newday.presenter.impl.MainPresenterImpl;
import com.zd.newday.view.MainView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements MainView {
    public static final String FRAGMENT_ITEM = "fragment_item";
    @BindView(R.id.nav_view)
    NavigationView mNavView;
    @BindView(R.id.fl_main)
    FrameLayout mMainFl;
    @BindView(R.id.rv_bottom_tab)
    RecyclerView mBottomTabRv;
    private MainPageAdapter mMainPageAdapter;
    private MainPresenterImpl mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initData();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //保存当前Fragment数据
        if (mMainPageAdapter != null) {
            outState.putInt(FRAGMENT_ITEM, mMainPageAdapter.getCurrentFragmentIndex());
        }
    }

    private void initData() {
        mPresenter = new MainPresenterImpl(this, this);
        mPresenter.initBottomTab(mBottomTabRv);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @OnClick(R.id.fab)
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fab:
                Snackbar.make(view, "这是个测试app", Snackbar.LENGTH_SHORT)
                        .setAction("Action", null).show();
                break;
            default:
                break;
        }
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void showErrorView(boolean show) {

    }

    @Override
    public void changeMainTab(int index) {
        if (mMainPageAdapter != null) {
            mMainPageAdapter.switchFragment(index);
        }
    }
}
