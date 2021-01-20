package com.luffy.componentlib.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.luffy.componentlib.R;
import com.luffy.componentlib.callback.IBaseLayerUIInit;

/**
 * Created by lvlufei on 2018/1/1
 *
 * @desc 公用的Fragment
 * 包括：
 * 1，公共的头布局；可自己扩展和替换
 * 2，网络监听
 * 3，定制子Fragment界面
 */
public abstract class BaseLayerFragment extends Fragment implements
        IBaseLayerUIInit<View>,
        IBaseLayerFragment {
    /**
     * 上下文对象
     */
    public Activity mContext;
    /**
     * 根布局
     */
    protected View rootView;
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

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        isAttach = true;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        /*初始化界面*/
        if (rootView == null) {
            mContext = getActivity();
            /*绑定布局*/
            rootView = inflater.inflate(baseLayoutView(), null);
            baseChildLayout = rootView.findViewById(R.id.base_child_layout);
            /*添加子布局*/
            if (setLayoutView() != 0) {
                baseChildLayout.addView(inflater.inflate(setLayoutView(), null));
                baseChildLayout.setBackgroundResource(onFragmentBackgroundResource());
            }
            /*绑定控件*/
            bindButterKnife(rootView);
            /*初始化接收到的数据*/
            initReceiveData();
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
}
