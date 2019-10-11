package com.luffy.generalandroidlib.android.broadcastReceiver;

import android.content.Context;
import android.content.Intent;

/**
 * Created by lvlufei on 2019/4/9
 *
 * @desc 公用的广播接收者-回调
 */
public interface IBaseLayerReceiver {
    /**
     * 接收广播
     *
     * @param context
     * @param intent
     */
    void onReceiveBroadcast(Context context, Intent intent);
}
