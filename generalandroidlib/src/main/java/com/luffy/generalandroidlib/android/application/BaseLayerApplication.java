package com.luffy.generalandroidlib.android.application;

import android.app.Application;

/**
 * Created by lvlufei on 2018/1/1
 *
 * @desc 公用的Application
 * 包括：
 * 1，网络监听
 * 2，方法数超出处理
 */
public class BaseLayerApplication extends Application {

    private static BaseLayerApplication instance;

    public static BaseLayerApplication getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

}
