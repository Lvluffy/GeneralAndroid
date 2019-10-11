package com.luffy.generalandroidlib.template.uiLoader;

import android.content.Context;

/**
 * Created by lvlufei on 2019/7/24
 *
 * @name 通用的二级UI加载器
 * @desc
 */
public abstract class BaseLayerSecondUILoader<V, T> {

    public Context mContext;
    public V view;
    public T data;

    public BaseLayerSecondUILoader(Context mContext, V view, T data) {
        this.mContext = mContext;
        this.view = view;
        this.data = data;
        init();
    }

    protected abstract void init();
}
