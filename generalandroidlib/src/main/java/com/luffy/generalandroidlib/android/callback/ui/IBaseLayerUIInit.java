package com.luffy.generalandroidlib.android.callback.ui;

import android.view.LayoutInflater;

/**
 * Created by lvlufei on 2019/7/8
 *
 * @name 公共UI初始化-回调监听
 * @desc
 */
public interface IBaseLayerUIInit<T> {
    /**
     * 设置布局
     *
     * @return
     */
    int setLayoutView();

    /**
     * 总布局
     *
     * @return
     */
    int baseLayoutView();

    /**
     * 初始化公共标题栏控件
     *
     * @param inflater
     */
    void initPublicTitlebar(LayoutInflater inflater);

    /**
     * 绑定控件
     *
     * @param target Activity-Activity;Fragment-View
     */
    void bindButterKnife(T target);

    /**
     * 初始化配置
     */
    void initConfig();

    /**
     * 初始化接收到的数据
     */
    void initReceiveData();

    /**
     * 初始化布局
     */
    void initView();

    /**
     * 初始化Presenter
     */
    void initPresenter();

    /**
     * 为Presenter分离视图
     */
    void detachViewForPresenter();
}
