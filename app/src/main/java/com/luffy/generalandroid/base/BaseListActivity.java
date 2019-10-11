package com.luffy.generalandroid.base;

import android.app.Activity;

import com.luffy.generalandroidlib.template.listView.general.BaseLayerListActivity;

import butterknife.ButterKnife;

/**
 * Created by lvlufei on 2019/4/29
 *
 * @desc 公用的列表界面
 */
public abstract class BaseListActivity extends BaseLayerListActivity {

    @Override
    public void bindButterKnife(Activity target) {
        ButterKnife.bind(this);
    }
}
