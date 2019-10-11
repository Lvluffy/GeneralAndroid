package com.luffy.generalandroid.base;

import android.app.Activity;

import com.luffy.generalandroidlib.android.activity.BaseLayerActivity;

import butterknife.ButterKnife;

/**
 * Created by lvlufei on 2017/12/1
 *
 * @desc 公共-Activity
 */
public abstract class BaseActivity extends BaseLayerActivity {

    @Override
    public void bindButterKnife(Activity target) {
        ButterKnife.bind(target);
    }
}
