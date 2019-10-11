package com.luffy.generalandroid.base;

import android.view.View;

import com.luffy.generalandroid.R;
import com.luffy.generalandroidlib.android.fragment.BaseLayerFragment;

import butterknife.ButterKnife;

/**
 * Created by lvlufei on 2017/12/1
 *
 * @desc 公共-Fragment
 */
public abstract class BaseFragment extends BaseLayerFragment {

    @Override
    public void bindButterKnife(View rootView) {
        ButterKnife.bind(this, rootView);
    }

    @Override
    public int onFragmentBackgroundResource() {
        return R.color.color_F6F6F6;
    }
}
