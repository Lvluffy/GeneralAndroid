package com.luffy.generalandroidlib.template.webview.core.ui;

/**
 * Created by lvlufei on 2018/5/24
 *
 * @desc 公用的Webview-公共流程接口
 */
public interface IBaseLayerWeb {
    /**
     * 初始化控件
     */
    void findView();

    /**
     * 初始化操作
     */
    void init();

    /**
     * 配置webView
     */
    void configView();

    /**
     * 处理异常界面
     */
    void handlerErrorView();

    /**
     * 处理Url
     */
    void handlerUrl();

    /**
     * 处理分享Url
     */
    void handlerShareUrl();

    /**
     * 加载Url
     */
    void loadUrl();

    /**
     * 动态修改标题
     *
     * @return
     */
    boolean modifyTitle();

    /**
     * webview是否可以返回
     *
     * @return
     */
    boolean webCanGoBack();

    /**
     * 监听url重定向
     */
    void monitorUrlLoading();
}
