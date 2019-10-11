package com.luffy.generalandroidlib.android.broadcastReceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by lvlufei on 2019/4/9
 *
 * @desc 公用的广播接收者
 */
public abstract class BaseLayerReceiver extends BroadcastReceiver implements IBaseLayerReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        onReceiveBroadcast(context, intent);
    }
}
