package com.luffy.generalandroidlib.android.helper;

import com.luffy.generalandroidlib.R;
import com.luffy.generalandroidlib.android.application.BaseLayerApplication;
import com.luffy.generalutilslib.utils.NetUtils;
import com.luffy.toastlib.ToastBuilder;

/**
 * Created by lvlufei on 2018/1/1
 *
 * @desc 网络提示-帮助类
 */
public class NetworkHintHelper {
    /**
     * 去提示
     */
    public static void gotoHint(NetworkHintCallback networkHintCallback) {
        if (networkHintCallback != null) {
            if (NetUtils.getInstance().isConnected(BaseLayerApplication.getInstance())) {
                networkHintCallback.hasNetwork();
                if (NetUtils.getInstance().isWifi(BaseLayerApplication.getInstance())) {
                    networkHintCallback.wifiNetwork();
                } else if (NetUtils.getInstance().isMobile(BaseLayerApplication.getInstance())) {
                    networkHintCallback.mobileNetwork();
                }
            } else {
                networkHintCallback.noNetwork();
            }
        }
    }

    /**
     * 网络提示回调监听
     */
    public abstract static class NetworkHintCallback {
        /**
         * 有网络
         *
         * @return true:自己处理；false:向下处理
         */
        public abstract void hasNetwork();

        /**
         * WIFI网络
         */
        public void wifiNetwork() {
        }

        /**
         * 手机（4G/3G/2G）网络
         */
        public void mobileNetwork() {
        }

        /**
         * 无网络
         */
        public void noNetwork() {
            new ToastBuilder(BaseLayerApplication.getInstance()).setTitle(R.string.error_net_error).show();
        }
    }
}
