package com.luffy.generalandroidlib.template.webview.core.webViewClient;

import android.net.http.SslError;
import android.webkit.SslErrorHandler;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by lvlufei on 2019/6/20
 *
 * @name 公用的web客户端
 * @desc
 */
public class BaseLayerWebViewClient extends WebViewClient {

    private IBaseLayerWebViewClient mIBaseLayerWebViewClient;

    public BaseLayerWebViewClient(IBaseLayerWebViewClient mIBaseLayerWebViewClient) {
        this.mIBaseLayerWebViewClient = mIBaseLayerWebViewClient;
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        if (mIBaseLayerWebViewClient != null) {
            return mIBaseLayerWebViewClient.shouldOverrideUrlLoadingBase(view, url);
        }
        return super.shouldOverrideUrlLoading(view, url);
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
        if (mIBaseLayerWebViewClient != null) {
            return mIBaseLayerWebViewClient.shouldOverrideUrlLoadingBase(view, request);
        }
        return super.shouldOverrideUrlLoading(view, request);
    }

    @Override
    public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
        /*接受所有网站的证书*/
        handler.proceed();
        super.onReceivedSslError(view, handler, error);
    }

}
