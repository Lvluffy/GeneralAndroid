package com.luffy.generalandroidlib.android.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * Created by lvlufei on 2019/4/9
 *
 * @desc 公共的服务
 */
public abstract class BaseLayerService extends Service implements IBaseLayerService {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return onBindService(intent);
    }
}
