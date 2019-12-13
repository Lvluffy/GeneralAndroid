package com.luffy.generalandroidlib.template.webview.extend.urlLoading;

import android.app.Activity;

import com.luffy.generalutilslib.utils.IntentUtils;
import com.luffy.generalutilslib.utils.ValidUtils;

/**
 * Created by lvlufei on 2019/6/20
 *
 * @name url加载拦截-文件
 * @desc
 */
public class FileUrlLoadingIntercept extends BaseUrlLoadingIntercept {

    @Override
    public boolean urlLoadingIntercept(Activity mContext, String url) {
        if (ValidUtils.getInstance().isValid(url) && (url.endsWith(".docx")
                || url.endsWith(".doc")
                || url.endsWith(".xls")
                || url.endsWith(".xlsx")
                || url.endsWith(".ppt")
                || url.endsWith(".pptx")
                || url.endsWith(".pdf")
                || url.endsWith(".apk"))) {
            try {
                IntentUtils.getInstance().openBrowser(mContext, url);
            } catch (Exception ignored) {
            }
            return true;
        }
        return false;
    }
}
