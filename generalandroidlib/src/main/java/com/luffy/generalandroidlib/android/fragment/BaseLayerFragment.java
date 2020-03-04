package com.luffy.generalandroidlib.android.fragment;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.luffy.dialoglib.dialog.loadingDialog.LoadingDialog;
import com.luffy.generalandroidlib.R;
import com.luffy.generalandroidlib.android.application.BaseLayerApplication;
import com.luffy.generalandroidlib.android.callback.basetitle.IBaseLayerTitle;
import com.luffy.generalandroidlib.android.callback.loading.IBaseLayerLoading;
import com.luffy.generalandroidlib.android.callback.network.IBaseLayerNetwork;
import com.luffy.generalandroidlib.android.callback.ui.IBaseLayerUIInit;
import com.luffy.generalutilslib.utils.NetUtils;

/**
 * Created by lvlufei on 2018/1/1
 *
 * @desc 公用的Fragment
 * 包括：
 * 1，公共的头布局；可自己扩展和替换
 * 2，网络监听
 * 3，定制子Fragment界面
 */
public abstract class BaseLayerFragment extends Fragment implements View.OnClickListener,
        IBaseLayerUIInit<View>,
        IBaseLayerFragment,
        IBaseLayerNetwork,
        IBaseLayerLoading,
        IBaseLayerTitle {
    /**
     * 上下文对象
     */
    public Activity mContext;
    /**
     * 根布局
     */
    protected View rootView;
    /**
     * 标题栏布局
     */
    RelativeLayout navLayout;
    LinearLayout navBack;
    ImageView navBackImg;
    ImageView navCloseImg;
    TextView navBackTxt;
    TextView navTitle;
    LinearLayout navMore;
    ImageView navMoreImg;
    TextView navMoreTxt;
    View navDivider;
    /**
     * 子界面布局
     */
    FrameLayout baseChildLayout;
    /**
     * 界面是否已经附属，默认：false
     */
    protected boolean isAttach;
    /**
     * 视图是否已经初始化，默认：false
     */
    protected boolean isInit;
    /**
     * 网络监听广播
     */
    protected BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (ConnectivityManager.CONNECTIVITY_ACTION.equals(action)) {
                onNetChange(NetUtils.getInstance().isConnected(BaseLayerApplication.getInstance()));
            }
        }
    };
    /**
     * 网络加载loading
     */
    protected LoadingDialog mLoadingDialog;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        isAttach = true;
        /*注册网络监听广播*/
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        BaseLayerApplication.getInstance().registerReceiver(broadcastReceiver, intentFilter);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        /*初始化界面*/
        if (rootView == null) {
            mContext = getActivity();
            /*绑定布局*/
            rootView = inflater.inflate(baseLayoutView(), null);
            /*初始化公共标题栏控件*/
            initPublicTitlebar(inflater);
            /*绑定控件*/
            bindButterKnife(rootView);
            /*初始化接收到的数据*/
            initReceiveData();
            /*初始化配置*/
            initConfig();
            /*界面对用户可见时，初始化界面和数据*/
            if (getUserVisibleHint()) {
                /*初始化界面*/
                initView();
                /*初始化数据*/
                initPresenter();
                /*更改初始化状态*/
                isInit = true;
            }
        }
        ViewGroup parent = (ViewGroup) rootView.getParent();
        if (parent != null) {
            parent.removeView(rootView);
        }
        return rootView;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        detachViewForPresenter();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        isAttach = false;
        /*注销网络监听广播*/
        BaseLayerApplication.getInstance().unregisterReceiver(broadcastReceiver);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isAttach && !isInit && getUserVisibleHint()) {
            /*初始化界面*/
            initView();
            /*初始化数据*/
            initPresenter();
            /*更改初始化状态*/
            isInit = true;
            return;
        }
        if (isAttach && isInit && getUserVisibleHint() && onVisibleRefresh()) {
            /*初始化界面*/
            initView();
            /*初始化数据*/
            initPresenter();
        }
    }

    @Override
    public void onClick(View view) {
        int i = view.getId();
        if (i == R.id.nav_layout) {
            onClickNav();
        } else if (i == R.id.nav_back) {
            onClickBack();
        } else if (i == R.id.nav_close_img) {
            mContext.onBackPressed();
        } else if (i == R.id.nav_more) {
            onClickMore();
        }
    }

    /**
     * Fragment公共布局
     *
     * @return
     */
    @Override
    public int baseLayoutView() {
        return R.layout.activity_base;
    }

    /**
     * 初始化公共标题栏控件
     *
     * @param inflater
     */
    @Override
    public void initPublicTitlebar(LayoutInflater inflater) {
        navLayout = rootView.findViewById(R.id.nav_layout);
        navBack = rootView.findViewById(R.id.nav_back);
        navBackImg = rootView.findViewById(R.id.nav_back_img);
        navCloseImg = rootView.findViewById(R.id.nav_close_img);
        navBackTxt = rootView.findViewById(R.id.nav_back_txt);
        navTitle = rootView.findViewById(R.id.nav_title);
        navMore = rootView.findViewById(R.id.nav_more);
        navMoreImg = rootView.findViewById(R.id.nav_more_img);
        navMoreTxt = rootView.findViewById(R.id.nav_more_txt);
        navDivider = rootView.findViewById(R.id.nav_divider);
        baseChildLayout = rootView.findViewById(R.id.base_child_layout);
        navLayout.setOnClickListener(this);
        navBack.setOnClickListener(this);
        navCloseImg.setOnClickListener(this);
        navMore.setOnClickListener(this);
        /*添加子布局*/
        if (setLayoutView() != 0) {
            baseChildLayout.addView(inflater.inflate(setLayoutView(), null));
            baseChildLayout.setBackgroundResource(onFragmentBackgroundResource());
        }
    }

    /**
     * 初始化配置
     */
    @Override
    public void initConfig() {
        /*设置标题栏是否可见*/
        if (visibilityTitleLayout()) {
            navLayout.setVisibility(View.VISIBLE);
        } else {
            navLayout.setVisibility(View.GONE);
        }
        /*设置返回是否可见*/
        if (visibilityBack()) {
            navBack.setVisibility(View.VISIBLE);
        } else {
            navBack.setVisibility(View.GONE);
        }
        if (visibilityBackImg()) {
            navBackImg.setVisibility(View.VISIBLE);
        } else {
            navBackImg.setVisibility(View.GONE);
        }
        if (visibilityCloseImg()) {
            navCloseImg.setVisibility(View.VISIBLE);
        } else {
            navCloseImg.setVisibility(View.GONE);
        }
        if (visibilityBackTxt()) {
            navBackTxt.setVisibility(View.VISIBLE);
        } else {
            navBackTxt.setVisibility(View.GONE);
        }
        /*设置更多是否可见*/
        if (visibilityMore()) {
            navMore.setVisibility(View.VISIBLE);
        } else {
            navMore.setVisibility(View.GONE);
        }
        if (visibilityMoreImg()) {
            navMoreImg.setVisibility(View.VISIBLE);
        } else {
            navMoreImg.setVisibility(View.GONE);
        }
        if (visibilityMoreTxt()) {
            navMoreTxt.setVisibility(View.VISIBLE);
        } else {
            navMoreTxt.setVisibility(View.GONE);
        }
        /*设置标题栏背景色*/
        if (setBgColor() != 0) {
            navLayout.setBackgroundResource(setBgColor());
        }
        /*设置不透明度*/
        navLayout.getBackground().setAlpha(setAlpha());
        /*设置返回图标*/
        if (setBackImg() != 0) {
            navBackImg.setImageResource(setBackImg());
        }
        /*设置关闭图标*/
        if (setCloseImg() != 0) {
            navCloseImg.setImageResource(setCloseImg());
        }
        /*设置返回*/
        if (setBackInt() != 0) {
            navBackTxt.setText(setBackInt());
        } else if (!TextUtils.isEmpty(setBackString())) {
            navBackTxt.setText(setBackString());
        }
        /*设置返回颜色*/
        if (setBackColor() != 0) {
            navBackTxt.setTextColor(ContextCompat.getColor(mContext, setBackColor()));
        }
        /*设置返回大小*/
        if (setBackSize() != 0) {
            navBackTxt.setTextSize(TypedValue.COMPLEX_UNIT_SP, setBackSize());
        }
        /*设置标题*/
        if (setTitleInt() != 0) {
            navTitle.setText(setTitleInt());
        } else if (!TextUtils.isEmpty(setTitleString())) {
            navTitle.setText(setTitleString());
        }
        /*设置标题颜色*/
        if (setTitleColor() != 0) {
            navTitle.setTextColor(ContextCompat.getColor(mContext, setTitleColor()));
        }
        /*设置标题大小*/
        if (setTitleSize() != 0) {
            navTitle.setTextSize(TypedValue.COMPLEX_UNIT_SP, setTitleSize());
        }
        /*设置更多图标*/
        if (setMoreImg() != 0) {
            navMoreImg.setImageResource(setMoreImg());
        }
        /*设置更多*/
        if (setMoreInt() != 0) {
            navMoreTxt.setText(setMoreInt());
        } else if (!TextUtils.isEmpty(setMoreString())) {
            navMoreTxt.setText(setMoreString());
        }
        /*设置更多颜色*/
        if (setMoreColor() != 0) {
            navMoreTxt.setTextColor(ContextCompat.getColor(mContext, setMoreColor()));
        }
        /*设置更多大小*/
        if (setMoreSize() != 0) {
            navMoreTxt.setTextSize(TypedValue.COMPLEX_UNIT_SP, setMoreSize());
        }
        /*设置分割线是否可见*/
        if (visibilityDivider()) {
            navDivider.setVisibility(View.VISIBLE);
        } else {
            navDivider.setVisibility(View.GONE);
        }
        /*设置分割线颜色*/
        if (setDividerColor() != 0) {
            navDivider.setBackgroundColor(ContextCompat.getColor(mContext, setDividerColor()));
        }
    }

    /**
     * 设置Fragment背景色
     *
     * @return
     */
    @Override
    public int onFragmentBackgroundResource() {
        return R.color.color_F6F6F6;
    }

    /**
     * 可见刷新
     *
     * @return
     */
    @Override
    public boolean onVisibleRefresh() {
        return false;
    }

    /**
     * 网络状态改变监听
     *
     * @param isConnected
     */
    @Override
    public void onNetChange(boolean isConnected) {

    }

    /**
     * 显示-加载Loading
     */
    @Override
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

    @Override
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

    /**
     * 取消-加载Loading
     */
    @Override
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

    /**
     * 设置标题栏可见与否
     *
     * @return
     */
    @Override
    public boolean visibilityTitleLayout() {
        return false;
    }

    /**
     * 设置返回可见与否
     *
     * @return
     */
    @Override
    public boolean visibilityBack() {
        return false;
    }

    /**
     * 设置返回可见与否
     *
     * @return
     */
    @Override
    public boolean visibilityBackImg() {
        return false;
    }

    /**
     * 设置关闭可见与否
     *
     * @return
     */
    @Override
    public boolean visibilityCloseImg() {
        return false;
    }

    /**
     * 设置关闭可见与否
     *
     * @param b
     */
    @Override
    public void visibilityCloseImg(boolean b) {
        if (b) {
            navCloseImg.setVisibility(View.VISIBLE);
        } else {
            navCloseImg.setVisibility(View.GONE);
        }
    }

    /**
     * 设置返回可见与否
     *
     * @return
     */
    @Override
    public boolean visibilityBackTxt() {
        return false;
    }

    /**
     * 设置更多可见与否
     *
     * @return
     */
    @Override
    public boolean visibilityMore() {
        return true;
    }

    /**
     * 设置更多可见与否
     *
     * @return
     */
    @Override
    public boolean visibilityMoreImg() {
        return false;
    }

    /**
     * 设置更多可见与否
     *
     * @return
     */
    @Override
    public boolean visibilityMoreTxt() {
        return false;
    }

    /**
     * 设置标题栏背景色
     *
     * @return
     */
    @Override
    public int setBgColor() {
        return 0;
    }

    /**
     * 设置不透明度
     *
     * @@return alpha（0-255：0为完全透明，255为不透明）
     */
    @Override
    public int setAlpha() {
        return 255;
    }

    /**
     * 设置返回图标
     *
     * @return
     */
    @Override
    public int setBackImg() {
        return 0;
    }

    /**
     * 设置关闭图标
     *
     * @return
     */
    @Override
    public int setCloseImg() {
        return 0;
    }

    /**
     * 设置返回
     *
     * @return
     */
    @Override
    public int setBackInt() {
        return 0;
    }

    /**
     * 设置返回
     *
     * @return
     */
    @Override
    public String setBackString() {
        return "";
    }

    /**
     * 设置返回文案（网络获取）
     *
     * @param backStr
     */
    @Override
    public void setBackString(String backStr) {
        this.navBackTxt.setText(backStr);
    }

    /**
     * 设置返回字体颜色
     *
     * @return
     */
    @Override
    public int setBackColor() {
        return 0;
    }

    /**
     * 设置返回字体大小
     *
     * @return
     */
    @Override
    public int setBackSize() {
        return 0;
    }

    /**
     * 设置标题
     *
     * @return
     */
    @Override
    public int setTitleInt() {
        return 0;
    }

    /**
     * 设置标题
     *
     * @return
     */
    @Override
    public String setTitleString() {
        return "";
    }

    /**
     * 设置标题文案（网络获取）
     *
     * @param titleStr
     */
    @Override
    public void setTitleString(String titleStr) {
        this.navTitle.setText(titleStr);
    }

    /**
     * 设置标题字体颜色
     *
     * @return
     */
    @Override
    public int setTitleColor() {
        return 0;
    }

    /**
     * 设置标题字体大小
     *
     * @return
     */
    @Override
    public int setTitleSize() {
        return 0;
    }

    /**
     * 设置更多图标
     *
     * @return
     */
    @Override
    public int setMoreImg() {
        return 0;
    }

    /**
     * 设置更多
     *
     * @return
     */
    @Override
    public int setMoreInt() {
        return 0;
    }

    /**
     * 设置更多
     *
     * @return
     */
    @Override
    public String setMoreString() {
        return "";
    }

    /**
     * 设置更多文案（网络获取）
     *
     * @param moreStr
     */
    @Override
    public void setMoreString(String moreStr) {
        this.navMoreTxt.setText(moreStr);
    }

    /**
     * 设置更多字体颜色
     *
     * @return
     */
    @Override
    public int setMoreColor() {
        return 0;
    }

    /**
     * 设置更多字体大小
     *
     * @return
     */
    @Override
    public int setMoreSize() {
        return 0;
    }

    /**
     * 设置分割线可见与否
     *
     * @return
     */
    @Override
    public boolean visibilityDivider() {
        return true;
    }

    /**
     * 设置分割线颜色
     *
     * @return
     */
    @Override
    public int setDividerColor() {
        return 0;
    }

    /**
     * 返回按钮点击事件
     */
    @Override
    public void onClickNav() {

    }

    /**
     * 返回点击监听
     */
    @Override
    public void onClickBack() {
        mContext.onBackPressed();
    }

    /**
     * 更多点击监听
     */
    @Override
    public void onClickMore() {

    }
}
