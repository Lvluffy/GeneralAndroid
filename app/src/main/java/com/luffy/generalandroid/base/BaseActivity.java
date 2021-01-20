package com.luffy.generalandroid.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.luffy.generalandroid.R;
import com.luffy.lifycycle.screenlib.impl.IBaseScreen;
import com.luffy.lifycycle.statusbarlib.IStatus;
import com.luffy.lifycycle.titlebarlib.TitleBarWidget;
import com.luffy.lifycycle.titlebarlib.impl.IPresenter;
import com.luffy.lifycycle.titlebarlib.impl.ITitleClick;
import com.luffy.lifycycle.titlebarlib.impl.ITitleLayout;
import com.luffy.lifycycle.titlebarlib.impl.IUIInit;
import com.luffy.window.dialoglib.dialog.loadingDialog.LoadingDialog;

import butterknife.ButterKnife;

/**
 * Created by lvlufei on 2017/12/1
 *
 * @desc 公共-Activity
 */
public abstract class BaseActivity extends AppCompatActivity implements ITitleLayout,
        ITitleClick,
        IPresenter,
        IUIInit<Activity>,
        IBaseScreen,
        IStatus {

    public Activity mContext;
    public static Context mApiObserverContext;
    /**
     * 网络加载loading
     */
    LoadingDialog mLoadingDialog;

    public void showLoading() {
        try {
            if (mContext == null || mContext.isFinishing()) {
                return;
            }
            if (mLoadingDialog == null) {
                mLoadingDialog = new LoadingDialog(mContext);
            }
            mLoadingDialog.show();
        } catch (Exception ignored) {

        }
    }

    public void showLoading(String content) {
        try {
            if (mContext == null || mContext.isFinishing()) {
                return;
            }
            if (mLoadingDialog == null) {
                mLoadingDialog = new LoadingDialog(mContext);
            }
            mLoadingDialog.setText(content).show();
        } catch (Exception ignored) {
        }
    }

    public void dismissLoading() {
        try {
            if (mContext == null || mContext.isFinishing()) {
                return;
            }
            if (mLoadingDialog != null) {
                mLoadingDialog.dismiss();
            }
        } catch (Exception ignored) {
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        mContext = this;
        mApiObserverContext = mContext;
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onClickNav() {

    }

    @Override
    public void onClickBack() {
        onBackPressed();
    }

    @Override
    public void onClickMore() {

    }

    @Override
    public void onClickClose() {

    }

    @Override
    public boolean visibilityTitleLayout() {
        return true;
    }

    @Override
    public boolean visibilityBack() {
        return true;
    }

    @Override
    public boolean visibilityBackImg() {
        return true;
    }

    @Override
    public boolean visibilityBackTxt() {
        return false;
    }

    @Override
    public boolean visibilityCloseImg() {
        return false;
    }

    @Override
    public void visibilityCloseImg(boolean b) {

    }

    @Override
    public boolean visibilityMore() {
        return true;
    }

    @Override
    public boolean visibilityMoreView() {
        return false;
    }

    @Override
    public boolean visibilityMoreImg() {
        return false;
    }

    @Override
    public boolean visibilityMoreTxt() {
        return false;
    }

    @Override
    public boolean visibilityDivider() {
        return false;
    }

    @Override
    public int setBgColor() {
        return 0;
    }

    @Override
    public int setAlpha() {
        return 255;
    }

    @Override
    public int setBackImg() {
        return R.drawable.base_nav_back;
    }

    @Override
    public int setCloseImg() {
        return 0;
    }

    @Override
    public int setBackTextInt() {
        return 0;
    }

    @Override
    public String setBackTextString() {
        return null;
    }

    @Override
    public void setBackTextString(String backStr) {

    }

    @Override
    public int setBackTextColor() {
        return 0;
    }

    @Override
    public int setBackTextSize() {
        return 0;
    }

    @Override
    public int setTitleInt() {
        return 0;
    }

    @Override
    public String setTitleString() {
        return null;
    }

    @Override
    public void setTitleString(String titleStr) {
        TitleBarWidget.getInstance().getNavTitle().setText(titleStr);
    }

    @Override
    public int setTitleColor() {
        return R.color.color_333333;
    }

    @Override
    public int setTitleSize() {
        return 17;
    }

    @Override
    public View setMoreView() {
        return null;
    }

    @Override
    public int setMoreImg() {
        return 0;
    }

    @Override
    public int setMoreTextInt() {
        return 0;
    }

    @Override
    public String setMoreTextString() {
        return null;
    }

    @Override
    public void setMoreTextString(String moreStr) {

    }

    @Override
    public int setMoreTextColor() {
        return 0;
    }

    @Override
    public int setMoreTextSize() {
        return 0;
    }

    @Override
    public int setDividerColor() {
        return 0;
    }

    @Override
    public void bindButterKnife(Activity target) {
        ButterKnife.bind(target);
    }

    @Override
    public void initReceiveData() {

    }

    @Override
    public boolean visibleTitleBar() {
        return true;
    }

    @Override
    public boolean visibleInfoBar() {
        return true;
    }

    @Override
    public boolean isLockScreen() {
        return true;
    }

}

