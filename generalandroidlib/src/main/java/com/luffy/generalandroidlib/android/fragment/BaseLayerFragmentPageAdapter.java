package com.luffy.generalandroidlib.android.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lvlufei on 2019/5/28
 *
 * @name 公用的Fragment页面适配器
 * @desc
 */
public class BaseLayerFragmentPageAdapter extends FragmentPagerAdapter {

    private List<Fragment> fragmentList = new ArrayList<>();

    public BaseLayerFragmentPageAdapter(List<Fragment> fragmentList, FragmentManager fragmentManager) {
        super(fragmentManager);
        this.fragmentList = fragmentList;
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList == null ? 0 : fragmentList.size();
    }
}
