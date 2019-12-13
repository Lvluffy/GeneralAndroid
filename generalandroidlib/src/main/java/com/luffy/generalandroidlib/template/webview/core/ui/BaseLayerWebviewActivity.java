package com.luffy.generalandroidlib.template.webview.core.ui;

import android.app.Activity;
import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Build;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.SslErrorHandler;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.LinearLayout;

import com.luffy.generalandroidlib.R;
import com.luffy.generalandroidlib.android.activity.BaseLayerActivity;
import com.luffy.generalandroidlib.template.webview.core.webChromeClient.BaseLayerWebChromeClient;
import com.luffy.generalandroidlib.template.webview.core.webChromeClient.IBaseLayerWebChromeClient;
import com.luffy.generalandroidlib.template.webview.core.webViewClient.BaseLayerWebViewClient;
import com.luffy.generalandroidlib.template.webview.core.webViewClient.IBaseLayerWebViewClient;
import com.luffy.generalandroidlib.template.webview.extend.urlLoading.AlipayUrlLoadingIntercept;
import com.luffy.generalandroidlib.template.webview.extend.urlLoading.FileUrlLoadingIntercept;
import com.luffy.generalandroidlib.template.webview.extend.urlLoading.ShortLinkUrlLoadingIntercept;
import com.luffy.generalandroidlib.template.webview.extend.urlLoading.TelUrlLoadingIntercept;
import com.luffy.generalutilslib.utils.NetUtils;

/**
 * Created by lvlufei on 2018/1/11
 *
 * @desc 公用的Webview模板
 */
public abstract class BaseLayerWebviewActivity extends BaseLayerActivity implements IBaseLayerWeb,
        IBaseLayerWebChromeClient,
        IBaseLayerWebViewClient {

    protected WebView webview;
    protected String requestUrl;
    protected boolean isError;
    protected LinearLayout webParentView;
    protected View errorView;

    @Override
    public int setLayoutView() {
        return R.layout.activity_base_layer_webview;
    }

    @Override
    public void bindButterKnife(Activity target) {

    }

    @Override
    public void initView() {
        findView();
        init();
        configView();
        handlerErrorView();
        handlerUrl();
        handlerShareUrl();
        loadUrl();
    }

    @Override
    public void findView() {
        webview = findViewById(R.id.webview);
        webParentView = (LinearLayout) webview.getParent();
    }

    @Override
    public void configView() {
        WebSettings webSettings = webview.getSettings();
        // 支持JS
        webSettings.setJavaScriptEnabled(true);
        // 设置JS是否可以打开WebView新窗口
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        // 可以加载本地html文件
        webSettings.setAllowFileAccess(true);
        // 设置页面是否支持缩放
        webSettings.setSupportZoom(false);
        // 这句解决本地跨域问题，如果你的 PDF 文件在站点里，是不需要的，但是，我们一般情况是加载站点外部 PDF 文件
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            webSettings.setAllowFileAccessFromFileURLs(true);
        }
        // 密码保存功能
        webSettings.setSavePassword(false);
        // 表单数据的保存功能
        webSettings.setSaveFormData(true);
        // 打开WebView的存储功能，这样, JS的localStorage,sessionStorage对象才可以使用
        webSettings.setDomStorageEnabled(true);
        // 缓存
        webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        // 1.NARROW_COLUMNS：适应内容大小
        // 2.SINGLE_COLUMN：适应屏幕，内容将自动缩放
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        // 设置自适应屏幕，两者合用
        webSettings.setUseWideViewPort(true); // 将图片调整到适合webview的大小
        webSettings.setLoadWithOverviewMode(true); // 缩放至屏幕的大小
        // android 5.0以上默认不支持Mixed Content
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            webSettings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
        webview.setWebChromeClient(new BaseLayerWebChromeClient(mContext, webview, this));
        webview.setWebViewClient(new BaseLayerWebViewClient(this));
    }

    @Override
    public void handlerErrorView() {

    }

    @Override
    public void handlerShareUrl() {

    }

    @Override
    public void loadUrl() {
        if (requestUrl == null) {
            return;
        }
        if (NetUtils.getInstance().isConnected(mContext)) {
            showLoading();
        }
        webview.loadUrl(requestUrl);
    }

    @Override
    public boolean modifyTitle() {
        return true;
    }

    @Override
    public boolean webCanGoBack() {
        return webview.canGoBack();
    }

    @Override
    public void monitorUrlLoading() {
        visibilityCloseImg(webCanGoBack());
    }

    @Override
    public void onProgressChangedBase(WebView view, int newProgress) {
        if (newProgress == 100) {
            dismissLoading();
            monitorUrlLoading();
        }
    }

    @Override
    public void onReceivedTitleBase(WebView view, String title) {
        if (modifyTitle() && !TextUtils.isEmpty(title) && !webview.getUrl().contains(title)) {
            setTitleString(title);
        }
    }

    @Override
    public boolean shouldOverrideUrlLoadingBase(WebView view, String url) {
        /*处理打开支付宝*/
        AlipayUrlLoadingIntercept alipayUrlLoadingIntercept = new AlipayUrlLoadingIntercept();
        if (alipayUrlLoadingIntercept.urlLoadingIntercept(mContext, url)) {
            return true;
        }
        /*处理文件*/
        FileUrlLoadingIntercept fileUrlLoadingIntercept = new FileUrlLoadingIntercept();
        if (fileUrlLoadingIntercept.urlLoadingIntercept(mContext, url)) {
            return true;
        }
        /*处理打电话*/
        TelUrlLoadingIntercept telUrlLoadingIntercept = new TelUrlLoadingIntercept();
        if (telUrlLoadingIntercept.urlLoadingIntercept(mContext, url)) {
            return true;
        }
        /*处理短链接*/
        ShortLinkUrlLoadingIntercept shortLinkUrlLoadingIntercept = new ShortLinkUrlLoadingIntercept();
        if (shortLinkUrlLoadingIntercept.urlLoadingIntercept(mContext, url)) {
            return true;
        }
        /*普通加载*/
        view.loadUrl(url);
        return true;
    }

    @Override
    public boolean shouldOverrideUrlLoadingBase(WebView view, WebResourceRequest request) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            /*处理打开支付宝*/
            AlipayUrlLoadingIntercept alipayUrlLoadingIntercept = new AlipayUrlLoadingIntercept();
            if (alipayUrlLoadingIntercept.urlLoadingIntercept(mContext, request.getUrl().toString())) {
                return true;
            }
            /*处理文件*/
            FileUrlLoadingIntercept fileUrlLoadingIntercept = new FileUrlLoadingIntercept();
            if (fileUrlLoadingIntercept.urlLoadingIntercept(mContext, request.getUrl().toString())) {
                return true;
            }
            /*处理打电话*/
            TelUrlLoadingIntercept telUrlLoadingIntercept = new TelUrlLoadingIntercept();
            if (telUrlLoadingIntercept.urlLoadingIntercept(mContext, request.getUrl().toString())) {
                return true;
            }
            /*处理短链接*/
            ShortLinkUrlLoadingIntercept shortLinkUrlLoadingIntercept = new ShortLinkUrlLoadingIntercept();
            if (shortLinkUrlLoadingIntercept.urlLoadingIntercept(mContext, request.getUrl().toString())) {
                return true;
            }
            /*普通加载*/
            view.loadUrl(request.getUrl().toString());
        } else {
            /*处理打开支付宝*/
            AlipayUrlLoadingIntercept alipayUrlLoadingIntercept = new AlipayUrlLoadingIntercept();
            if (alipayUrlLoadingIntercept.urlLoadingIntercept(mContext, request.toString())) {
                return true;
            }
            /*处理文件*/
            FileUrlLoadingIntercept fileUrlLoadingIntercept = new FileUrlLoadingIntercept();
            if (fileUrlLoadingIntercept.urlLoadingIntercept(mContext, request.toString())) {
                return true;
            }
            /*处理打电话*/
            TelUrlLoadingIntercept telUrlLoadingIntercept = new TelUrlLoadingIntercept();
            if (telUrlLoadingIntercept.urlLoadingIntercept(mContext, request.toString())) {
                return true;
            }
            /*处理短链接*/
            ShortLinkUrlLoadingIntercept shortLinkUrlLoadingIntercept = new ShortLinkUrlLoadingIntercept();
            if (shortLinkUrlLoadingIntercept.urlLoadingIntercept(mContext, request.toString())) {
                return true;
            }
            /*普通加载*/
            view.loadUrl(request.toString());
        }
        return true;
    }

    @Override
    public void onPageStartedBase(WebView view, String url, Bitmap favicon) {

    }

    @Override
    public void onPageFinishedBase(WebView view, String url) {
        if (!isError) {
            webParentView.removeAllViews();
            webParentView.addView(webview);
        }
    }

    @Override
    public void onReceivedSslErrorBase(WebView view, SslErrorHandler handler, SslError error) {
        /*接受所有网站的证书*/
        handler.proceed();
    }

    @Override
    public void onReceivedErrorBase(WebView view, WebResourceRequest request, WebResourceError error) {
        isError = true;
        webParentView.removeAllViews();
    }

    @Override
    public void onClickBack() {
        if (webCanGoBack()) {
            webview.goBack();
        } else {
            onBackPressed();
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (webCanGoBack()) {
                webview.goBack();
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }

}
