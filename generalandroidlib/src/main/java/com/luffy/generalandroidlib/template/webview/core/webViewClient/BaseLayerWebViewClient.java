package com.luffy.generalandroidlib.template.webview.core.webViewClient;

import android.graphics.Bitmap;
import android.net.http.SslError;
import android.webkit.SslErrorHandler;
import android.webkit.WebResourceError;
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
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        if (mIBaseLayerWebViewClient != null) {
            mIBaseLayerWebViewClient.onPageStartedBase(view, url, favicon);
        }
        super.onPageStarted(view, url, favicon);
    }

    @Override
    public void onPageFinished(WebView view, String url) {
        if (mIBaseLayerWebViewClient != null) {
            mIBaseLayerWebViewClient.onPageFinishedBase(view, url);
        }
        super.onPageFinished(view, url);
    }

    @Override
    public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
        if (mIBaseLayerWebViewClient != null) {
            mIBaseLayerWebViewClient.onReceivedSslErrorBase(view, handler, error);
        }
        super.onReceivedSslError(view, handler, error);
    }

    @Override
    public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
        if (mIBaseLayerWebViewClient != null) {
            mIBaseLayerWebViewClient.onReceivedErrorBase(view, request, error);
        }
        super.onReceivedError(view, request, error);
    }

}
