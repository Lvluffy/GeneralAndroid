package com.luffy.generalandroid.ui.module.web;


import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;

import com.luffy.generalandroidlib.template.webview.core.ui.BaseLayerWebviewActivity;

import java.util.Map;

public class WebActivity extends BaseLayerWebviewActivity {

    /*上个界面传过来的数据*/
    Map<String, Object> parameter;
    /*上个界面传过来的详细内容*/
    String title;
    String url;

    @Override
    public int setTitleInt() {
        return 0;
    }

    @Override
    public String setTitleString() {
        return title;
    }

    @Override
    public void init() {

    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initReceiveData() {
        parameter = (Map<String, Object>) getIntent().getSerializableExtra("parameter");
        if (parameter != null && parameter.size() > 0) {
            for (Map.Entry<String, Object> entry : parameter.entrySet()) {
                if ("title".equals(entry.getKey())) {
                    title = (String) entry.getValue();
                } else if ("url".equals(entry.getKey())) {
                    url = (String) entry.getValue();
                }
            }
        }
    }

    @Override
    public void handlerUrl() {
        requestUrl = url;
    }

}
