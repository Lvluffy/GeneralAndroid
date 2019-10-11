package com.luffy.generalandroidlib.template.webview.extend.video;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Build;
import android.util.Pair;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.FrameLayout;

import com.luffy.generalandroidlib.R;
import com.luffy.generalutilslib.utils.StatusBarUtils;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by zxing on 2019/3/27
 *
 * @desc H5视屏全屏 具体实现
 */
public class VideoImpl implements IVideo {

    private Activity mActivity;
    private WebView mWebView;
    private Set<Pair<Integer, Integer>> mFlags = null;
    private View mMoiveView = null;
    private ViewGroup mMoiveParentView = null;
    private WebChromeClient.CustomViewCallback mCallback;

    public VideoImpl(Activity mActivity, WebView webView) {
        this.mActivity = mActivity;
        this.mWebView = webView;
        mFlags = new HashSet<>();
    }

    @Override
    public void onShowCustomView(View view, WebChromeClient.CustomViewCallback callback) {
        Activity activity = mActivity;
        if (activity == null || activity.isFinishing()) {
            return;
        }
        // 横屏
        activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        //隐藏虚拟按键
        fullScreen(true);

        Window mWindow = activity.getWindow();
        Pair<Integer, Integer> mPair;
        // 保存当前屏幕状态
        if ((mWindow.getAttributes().flags & WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON) == 0) {
            mPair = new Pair<>(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON, 0);
            mWindow.setFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON, WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
            mFlags.add(mPair);
        }

        if ((Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) && (mWindow.getAttributes().flags & WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED) == 0) {
            mPair = new Pair<>(WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED, 0);
            mWindow.setFlags(WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED, WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED);
            mFlags.add(mPair);
        }

        if (mMoiveView != null) {
            callback.onCustomViewHidden();
            return;
        }

        if (mWebView != null) {
            mWebView.setVisibility(View.VISIBLE);
        }

        if (mMoiveParentView == null) {
            FrameLayout mDecorView = (FrameLayout) mActivity.getWindow().getDecorView();
            mMoiveParentView = new FrameLayout(mActivity);
            mMoiveParentView.setBackgroundColor(Color.BLACK);
            mDecorView.addView(mMoiveParentView);
        }
        this.mCallback = callback;
        this.mMoiveView = view;
        mMoiveParentView.addView(mMoiveView);
        mMoiveParentView.setVisibility(View.VISIBLE);
    }

    @Override
    public void onHideCustomView() {
        if (mMoiveView == null) {
            return;
        }
        if (mActivity != null && mActivity.getRequestedOrientation() != ActivityInfo.SCREEN_ORIENTATION_PORTRAIT) {
            mActivity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }

        //显示虚拟按键
        fullScreen(false);

        if (!mFlags.isEmpty()) {
            for (Pair<Integer, Integer> mPair : mFlags) {
                mActivity.getWindow().setFlags(mPair.second, mPair.first);
            }
            mFlags.clear();
        }

        mMoiveView.setVisibility(View.GONE);
        if (mMoiveParentView != null && mMoiveView != null) {
            mMoiveParentView.removeView(mMoiveView);
        }

        if (mMoiveParentView != null) {
            mMoiveParentView.setVisibility(View.GONE);
        }

        if (this.mCallback != null) {
            mCallback.onCustomViewHidden();
        }
        this.mMoiveView = null;
        if (mWebView != null) {
            mWebView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public boolean isVideoState() {
        return mMoiveView != null;
    }

    /**
     * 隐藏/显示 navigationBar
     *
     * @param enable
     */
    private void fullScreen(boolean enable) {
        if (enable) {
            if (Build.VERSION.SDK_INT >= 19) {
                mActivity.getWindow().getDecorView().setSystemUiVisibility(
                        View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                                | View.SYSTEM_UI_FLAG_FULLSCREEN
                                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
            }
        } else {
            if (Build.VERSION.SDK_INT >= 19) {
                mActivity.getWindow().getDecorView().setSystemUiVisibility(
                        View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
            }
            /*沉浸式状态栏*/
            StatusBarUtils.getInstance().setStatusBar(mActivity, R.color.white, true);
        }
    }
}
