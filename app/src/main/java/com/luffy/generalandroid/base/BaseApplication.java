package com.luffy.generalandroid.base;


import com.luffy.componentlib.application.BaseLayerApplication;
import com.luffy.generalandroid.R;
import com.luffy.lifycycle.statusbarlib.StatusBarClient;

/**
 * Created by lvlufei on 2017/11/24
 *
 * @desc 公共-Application
 */
public class BaseApplication extends BaseLayerApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        StatusBarClient.install(this, R.color.white, true);
    }
}
