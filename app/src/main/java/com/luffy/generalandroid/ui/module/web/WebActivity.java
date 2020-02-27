package com.luffy.generalandroid.ui.module.web;


import android.graphics.Typeface;
import android.os.Build;
import android.view.Gravity;
import android.view.View;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;

import com.luffy.generalandroid.R;
import com.luffy.generalandroidlib.template.webview.core.ui.BaseLayerWebviewActivity;
import com.luffy.generalviewlib.combinationView.child.empty.GeneralEmpty;

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
    public void detachViewForPresenter() {

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

    @Override
    public void handlerErrorView() {
        super.handlerErrorView();
        errorView = new GeneralEmpty(mContext)
                .setEmptyLayoutGravity(Gravity.CENTER)
                .setEmptyImg(R.drawable.user_center_bg)
                .setEmptyTxtColor(R.color.color_999999)
                .setEmptyTxtSize(14)
                .setEmptyTxtStyle(Typeface.BOLD)
                .setEmptyBtnBackground(R.drawable.base_btn_normal_full_radius_stroke_false_primary_selector)
                .setEmptyBtnColor(R.color.white)
                .setEmptyBtn("刷新重试", new GeneralEmpty.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        isError = false;
                        showLoading();
                        webview.reload();
                    }
                });
    }

    @Override
    public void onReceivedErrorBase(WebView view, WebResourceRequest request, WebResourceError error) {
        super.onReceivedErrorBase(view, request, error);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            webParentView.addView(((GeneralEmpty) errorView).setEmptyTxt(error.getDescription().toString()), view.getWidth(), view.getHeight());
        }
    }
}
