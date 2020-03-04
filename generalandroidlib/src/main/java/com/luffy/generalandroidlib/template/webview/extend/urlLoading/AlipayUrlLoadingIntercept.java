package com.luffy.generalandroidlib.template.webview.extend.urlLoading;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;

import com.luffy.toastlib.ToastBuilder;

/**
 * Created by lvlufei on 2019/6/20
 *
 * @name url加载拦截-支付宝
 * @desc
 */
public class AlipayUrlLoadingIntercept extends BaseUrlLoadingIntercept {

    @Override
    public boolean urlLoadingIntercept(Activity mContext, String url) {
        if (url.startsWith("alipays:") || url.startsWith("alipay")) {
            try {
                mContext.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(url)));
            } catch (Exception e) {
                new ToastBuilder(mContext).setTitle("未检测到支付宝客户端，请安装后重试。").show();
            }
            return true;
        }
        return false;
    }

}
