package com.luffy.generalandroidlib.template.listView.general;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import com.luffy.generalandroidlib.template.listView.callback.IBaseLayerList;

/**
 * Created by lvlufei on 2019/6/28
 *
 * @name 通用的二级列表加载器
 * @desc
 */
public abstract class BaseLayerSecondListLoader<T> implements IBaseLayerList {

    public Context mContext;
    public RecyclerView recyclerView;
    public T data;

    public BaseLayerSecondListLoader(Context mContext, RecyclerView recyclerView, T data) {
        this.mContext = mContext;
        this.recyclerView = recyclerView;
        this.data = data;
        init();
    }

    private void init() {
        initSwipeRefresh();
        initRecycleView();
        initAdapter();
    }

}
