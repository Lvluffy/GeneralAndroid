package com.luffy.generalandroidlib.template.webview.core.webViewClient;

import android.graphics.Bitmap;
import android.net.http.SslError;
import android.webkit.SslErrorHandler;
import android.webkit.WebResourceError;
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

    /**
     * 页面开始
     *
     * @param view
     * @param url
     * @param favicon
     */
    void onPageStartedBase(WebView view, String url, Bitmap favicon);

    /**
     * 页面结束
     *
     * @param view
     * @param url
     */
    void onPageFinishedBase(WebView view, String url);

    /**
     * 网页收到Ssl异常
     *
     * @param view
     * @param handler
     * @param error
     */
    void onReceivedSslErrorBase(WebView view, SslErrorHandler handler, SslError error);

    /**
     * 网页收到错误
     *
     * @param view
     * @param request
     * @param error
     */
    void onReceivedErrorBase(WebView view, WebResourceRequest request, WebResourceError error);
}
