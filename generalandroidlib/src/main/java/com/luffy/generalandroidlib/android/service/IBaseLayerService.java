package com.luffy.generalandroidlib.android.service;

import android.content.Intent;
import android.os.IBinder;

/**
 * Created by lvlufei on 2019/4/9
 *
 * @desc 公共的服务-回调
 */
public interface IBaseLayerService {
    /**
     * 绑定服务
     *
     * @param intent
     * @return
     */
    IBinder onBindService(Intent intent);
}
