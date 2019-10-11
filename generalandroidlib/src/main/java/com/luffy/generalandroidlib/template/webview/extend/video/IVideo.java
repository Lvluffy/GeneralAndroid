package com.luffy.generalandroidlib.template.webview.extend.video;

import android.view.View;
import android.webkit.WebChromeClient;

/**
 * Created by lvlufei on 2019/6/20
 *
 * @name 适配H5播放视频全屏
 * @desc
 */
public interface IVideo {

    /**
     * 开启全屏回调
     *
     * @param view
     * @param callback
     */
    void onShowCustomView(View view, WebChromeClient.CustomViewCallback callback);

    /**
     * 关闭全屏回调
     */
    void onHideCustomView();

    /**
     * 视频横屏/竖屏的状态
     *
     * @return
     */
    boolean isVideoState();
}
