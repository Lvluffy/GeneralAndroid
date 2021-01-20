package com.luffy.generalandroid.base;


import android.app.Application;

import com.luffy.generalandroid.R;
import com.luffy.lifycycle.statusbarlib.StatusBarClient;
import com.luffy.lifycycle.titlebarlib.TitleBarClient;

/**
 * Created by lvlufei on 2017/11/24
 *
 * @desc 公共-Application
 */
public class BaseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        TitleBarClient.install(this, 0);
        StatusBarClient.install(this, R.color.white, true);
    }
}
