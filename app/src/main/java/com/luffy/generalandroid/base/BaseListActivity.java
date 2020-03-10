package com.luffy.generalandroid.base;

import android.app.Activity;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.luffy.componentlib.activity.BaseLayerActivity;
import com.luffy.recyclerviewlib.callback.IBaseLayerList;

import butterknife.ButterKnife;

/**
 * Created by lvlufei on 2019/4/29
 *
 * @desc 公用的列表界面
 */
public abstract class BaseListActivity extends BaseLayerActivity implements IBaseLayerList,
        SwipeRefreshLayout.OnRefreshListener,
        BaseQuickAdapter.RequestLoadMoreListener {

    /*控件*/
    public SwipeRefreshLayout swipeRefreshLayout;
    public RecyclerView recyclerView;

    @Override
    public int setLayoutView() {
        return com.luffy.componentlib.R.layout.activity_base_list;
    }

    @Override
    public void bindButterKnife(Activity target) {
        ButterKnife.bind(this);
    }

    @Override
    public void initView() {
        findView();
        initSwipeRefresh();
        initRecycleView();
        initAdapter();
    }

    private void findView() {
        swipeRefreshLayout = findViewById(com.luffy.componentlib.R.id.swipeRefreshLayout);
        recyclerView = findViewById(com.luffy.componentlib.R.id.recyclerView);
    }

    @Override
    public void initSwipeRefresh() {
        swipeRefreshLayout.setProgressBackgroundColorSchemeResource(android.R.color.white);
        swipeRefreshLayout.setColorSchemeResources(com.luffy.componentlib.R.color.colorPrimary);
        swipeRefreshLayout.setOnRefreshListener(this);
    }

    @Override
    public void initRecycleView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }
}
