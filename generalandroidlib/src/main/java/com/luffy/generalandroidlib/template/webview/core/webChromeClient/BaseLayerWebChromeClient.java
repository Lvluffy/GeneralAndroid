package com.luffy.generalandroidlib.template.webview.core.webChromeClient;

import android.app.Activity;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

import com.luffy.generalandroidlib.template.webview.extend.video.VideoImpl;

/**
 * Created by lvlufei on 2019/6/20
 *
 * @name 公用的web浏览器客户端
 * @desc
 */
public class BaseLayerWebChromeClient extends WebChromeClient {

    private IBaseLayerWebChromeClient mIBaseLayerWebChromeClient;
    private VideoImpl mVideoImpl;

    public BaseLayerWebChromeClient(Activity mContext, WebView mWebView, IBaseLayerWebChromeClient mIBaseLayerWebChromeClient) {
        this.mIBaseLayerWebChromeClient = mIBaseLayerWebChromeClient;
        mVideoImpl = new VideoImpl(mContext, mWebView);
    }

    @Override
    public void onProgressChanged(WebView view, int newProgress) {
        super.onProgressChanged(view, newProgress);
        if (mIBaseLayerWebChromeClient != null) {
            mIBaseLayerWebChromeClient.onProgressChangedBase(view, newProgress);
        }
    }

    /**
     * 获取WebView标题
     *
     * @param view  　WebView
     * @param title 　WebView标题
     */
    @Override
    public void onReceivedTitle(WebView view, String title) {
        super.onReceivedTitle(view, title);
        if (mIBaseLayerWebChromeClient != null) {
            mIBaseLayerWebChromeClient.onReceivedTitleBase(view, title);
        }
    }

    /**
     * 网页点击全屏会回调方法
     *
     * @param view
     * @param callback
     */
    @Override
    public void onShowCustomView(View view, CustomViewCallback callback) {
        mVideoImpl.onShowCustomView(view, callback);
    }

    /**
     * 关闭全屏回调方法
     */
    @Override
    public void onHideCustomView() {
        mVideoImpl.onHideCustomView();
    }
}
