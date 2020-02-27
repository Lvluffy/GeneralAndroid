package com.luffy.generalandroid.ui.module.main.entrance;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.luffy.generalandroid.R;
import com.luffy.generalandroid.base.BaseActivity;
import com.luffy.generalandroid.ui.module.main.type.HomeFragment;
import com.luffy.generalandroid.ui.module.main.type.MessageFragment;
import com.luffy.generalandroid.ui.module.main.type.UserCenterFragment;
import com.luffy.generalandroidlib.android.fragment.BaseLayerFragmentStatePageAdapter;
import com.luffy.generalutilslib.utils.StatusBarUtils;
import com.luffy.generalviewlib.customView.bottomBar.BottomBarItem;
import com.luffy.generalviewlib.customView.bottomBar.BottomBarLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MainActivity extends BaseActivity {

    @BindView(R.id.vp_content)
    ViewPager mVpContent;
    @BindView(R.id.bottom_bar)
    BottomBarLayout mBottomBarLayout;

    HomeFragment mHomeFragment;
    MessageFragment mMessageFragment;
    UserCenterFragment mUserCenterFragment;

    @Override
    public boolean visibilityTitleLayout() {
        return false;
    }

    @Override
    public int setLayoutView() {
        return R.layout.activity_main;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void detachViewForPresenter() {

    }

    @Override
    public void initView() {
        //沉浸式状态栏
        StatusBarUtils.getInstance().setStatusBar(mContext, R.color.white, true);
        //初始化
        List<Fragment> mFragments = new ArrayList<>();
        mHomeFragment = new HomeFragment();
        mMessageFragment = new MessageFragment();
        mUserCenterFragment = new UserCenterFragment();
        mFragments.add(mHomeFragment);
        mFragments.add(mMessageFragment);
        mFragments.add(mUserCenterFragment);
        mVpContent.setAdapter(new BaseLayerFragmentStatePageAdapter(mFragments, getSupportFragmentManager()));
        mVpContent.setOffscreenPageLimit(mFragments.size());
        mBottomBarLayout.setViewPager(mVpContent);
        //设置条目点击的监听
        mBottomBarLayout.setOnItemSelectedListener(new BottomBarLayout.OnItemSelectedListener() {
            @Override
            public void onItemSelected(BottomBarItem bottomBarItem, int i, int position) {
                switch (position) {
                    //首页
                    case 0:
                        //沉浸式状态栏
                        StatusBarUtils.getInstance().setStatusBar(mContext, R.color.white, true);
                        break;
                    //我的
                    case 2:
                        //沉浸式状态栏
                        StatusBarUtils.getInstance().setStatusBar(mContext, R.color.colorPrimary, false);
                        break;
                }
            }
        });
    }

    @Override
    public void initReceiveData() {

    }
}
