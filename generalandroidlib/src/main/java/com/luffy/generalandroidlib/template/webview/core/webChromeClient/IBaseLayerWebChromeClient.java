package com.luffy.generalandroidlib.template.webview.core.webChromeClient;

import android.webkit.WebView;

/**
 * Created by lvlufei on 2019/3/27
 *
 * @desc 公用的Web浏览器客户端接口
 */
public interface IBaseLayerWebChromeClient {

    /**
     * 进度监听
     *
     * @param view
     * @param newProgress
     */
    void onProgressChangedBase(WebView view, int newProgress);

    /**
     * 接收标题
     *
     * @param view
     * @param title
     */
    void onReceivedTitleBase(WebView view, String title);
}
