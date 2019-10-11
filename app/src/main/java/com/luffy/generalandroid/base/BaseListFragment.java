package com.luffy.generalandroid.base;

import android.view.View;

import com.luffy.generalandroid.R;
import com.luffy.generalandroidlib.template.listView.general.BaseLayerListFragment;

import butterknife.ButterKnife;

/**
 * Created by lvlufei on 2019/4/29
 *
 * @desc 公用的列表界面
 */
public abstract class BaseListFragment extends BaseLayerListFragment {

    @Override
    public void bindButterKnife(View rootView) {
        ButterKnife.bind(this, rootView);
    }

    @Override
    public int onFragmentBackgroundResource() {
        return R.color.color_F6F6F6;
    }
}
