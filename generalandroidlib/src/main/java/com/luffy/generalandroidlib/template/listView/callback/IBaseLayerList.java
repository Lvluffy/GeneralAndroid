package com.luffy.generalandroidlib.template.listView.callback;

/**
 * Created by lvlufei on 2019/4/29
 *
 * @name
 * @desc
 */
public interface IBaseLayerList {
    /**
     * 初始化SwipeRefresh
     */
    void initSwipeRefresh();

    /**
     * 初始化RecycleView
     */
    void initRecycleView();

    /**
     * 初始化Adapter
     */
    void initAdapter();
}
