package com.luffy.generalandroidlib.template.webview.extend.urlLoading;

import android.app.Activity;

import com.luffy.generalutilslib.utils.IntentUtils;

/**
 * Created by lvlufei on 2019/6/20
 *
 * @name url加载拦截-短链接
 * @desc
 */
public class ShortLinkUrlLoadingIntercept extends BaseUrlLoadingIntercept {

    @Override
    public boolean urlLoadingIntercept(Activity mContext, String url) {
        if (!url.startsWith("http://") && !url.startsWith("https://")) {
            try {
                IntentUtils.getInstance().openBrowser(mContext, url);
            } catch (Exception ignored) {
            }
            return true;
        }
        return false;
    }
}
