package com.luffy.generalandroidlib.template.webview.extend.urlLoading;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;

/**
 * Created by lvlufei on 2019/6/20
 *
 * @name url加载拦截-打电话
 * @desc
 */
public class TelUrlLoadingIntercept extends BaseUrlLoadingIntercept {

    @Override
    public boolean urlLoadingIntercept(Activity mContext, String url) {
        if (url.startsWith("tel:")) {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            mContext.startActivity(intent);
            return true;
        }
        return false;
    }
}
