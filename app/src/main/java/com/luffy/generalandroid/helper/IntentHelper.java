package com.luffy.generalandroid.helper;

import android.content.Context;
import android.content.Intent;

import com.luffy.generalandroid.ui.module.list.ListActivity;
import com.luffy.generalandroid.ui.module.web.WebActivity;

import java.io.Serializable;
import java.util.Map;

/**
 * Created by lvlufei on 2018/1/1
 *
 * @desc 界面跳转-帮助类
 */
public class IntentHelper {

    /**
     * Web界面
     */
    public static class Web {
        /**
         * 跳转至——Web界面
         *
         * @param context
         * @param parameter 跳转界面所需参数
         */
        public static void gotoWebActivity(Context context, Map<String, Object> parameter) {
            Intent intent = new Intent(context, WebActivity.class);
            intent.putExtra("parameter", (Serializable) parameter);
            context.startActivity(intent);
        }
    }

    public static class List{
        /**
         * 跳转至——List界面
         *
         * @param context
         * @param parameter 跳转界面所需参数
         */
        public static void gotoListActivity(Context context, Map<String, Object> parameter) {
            Intent intent = new Intent(context, ListActivity.class);
            intent.putExtra("parameter", (Serializable) parameter);
            context.startActivity(intent);
        }
    }
}
