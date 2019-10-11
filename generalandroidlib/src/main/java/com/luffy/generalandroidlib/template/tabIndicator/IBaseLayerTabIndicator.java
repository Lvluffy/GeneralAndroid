package com.luffy.generalandroidlib.template.tabIndicator;

/**
 * Created by lvlufei on 2019/5/30
 *
 * @name 公用的TabIndicator
 * @desc
 */
public interface IBaseLayerTabIndicator {
    /**
     * 找控件
     */
    void findView();

    /**
     * 初始化-业务逻辑（扩展）
     */
    void init();

    /**
     * 绑定-标题
     */
    void bindTitle();

    /**
     * 绑定-fragment
     */
    void bindFragments();

    /**
     * 初始化-指示器配置
     */
    void initIndicatorConfig();

    /**
     * 初始化-指示器
     */
    void initMagicIndicator();

    /**
     * 初始化-适配器
     */
    void initAdapter();

    /**
     * 通知改变
     */
    void notifyChange();
}
