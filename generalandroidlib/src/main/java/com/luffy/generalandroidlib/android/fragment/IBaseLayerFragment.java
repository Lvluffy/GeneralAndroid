package com.luffy.generalandroidlib.android.fragment;

/**
 * Created by lvlufei on 2019/8/28
 *
 * @name 回调接口
 * @desc
 */
public interface IBaseLayerFragment {
    /**
     * 设置Fragment背景色
     *
     * @return
     */
    int onFragmentBackgroundResource();

    /**
     * 可见刷新
     *
     * @return
     */
    boolean onVisibleRefresh();
}
