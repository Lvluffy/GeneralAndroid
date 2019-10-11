package com.luffy.generalandroidlib.android.callback.ui;

/**
 * Created by lvlufei on 2019/7/8
 *
 * @name 公共UI刷新-回调监听
 * @desc
 */
public interface IBaseLayerUIRefresh<T> {

    /**
     * 刷新UI
     *
     * @param mObject
     */
    void refreshUI(T mObject);
}
