package com.luffy.generalandroidlib.android.callback.network;

/**
 * Created by lvlufei on 2018/1/1
 *
 * @desc 网络状态改变的监听
 */
public interface IBaseLayerNetwork {
    /**
     * 网络状态改变监听
     *
     * @param isConnected
     */
    void onNetChange(boolean isConnected);
}
