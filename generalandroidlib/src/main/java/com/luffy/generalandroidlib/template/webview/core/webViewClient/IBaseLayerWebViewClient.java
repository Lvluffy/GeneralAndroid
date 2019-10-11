package com.luffy.generalandroidlib.template.webview.core.webViewClient;

import android.webkit.WebResourceRequest;
import android.webkit.WebView;

/**
 * Created by lvlufei on 2019/3/27
 *
 * @desc 公用的Web客户端接口
 */
public interface IBaseLayerWebViewClient {
    /**
     * 重定向
     *
     * @param view
     * @param url
     * @return
     */
    boolean shouldOverrideUrlLoadingBase(WebView view, String url);

    /**
     * 重定向（android 7.0系统以上替换shouldOverrideUrlLoadingBase(WebView view, String url)）
     *
     * @param view
     * @param request
     * @return
     */
    boolean shouldOverrideUrlLoadingBase(WebView view, WebResourceRequest request);
}
