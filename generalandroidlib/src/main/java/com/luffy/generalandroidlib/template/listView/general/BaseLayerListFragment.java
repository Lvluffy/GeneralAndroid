package com.luffy.generalandroidlib.template.listView.general;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.luffy.generalandroidlib.R;
import com.luffy.generalandroidlib.android.fragment.BaseLayerFragment;
import com.luffy.generalandroidlib.template.listView.callback.IBaseLayerList;

/**
 * Created by lvlufei on 2019/4/29
 *
 * @desc 公用的列表模版
 */
public abstract class BaseLayerListFragment extends BaseLayerFragment implements IBaseLayerList,
        SwipeRefreshLayout.OnRefreshListener,
        BaseQuickAdapter.RequestLoadMoreListener {

    /*控件*/
    public SwipeRefreshLayout swipeRefreshLayout;
    public RecyclerView recyclerView;

    @Override
    public int setLayoutView() {
        return R.layout.activity_base_list;
    }

    @Override
    public void bindButterKnife(View rootView) {

    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initView() {
        findView();
        initSwipeRefresh();
        initRecycleView();
        initAdapter();
    }

    private void findView() {
        swipeRefreshLayout = rootView.findViewById(R.id.swipeRefreshLayout);
        recyclerView = rootView.findViewById(R.id.recyclerView);
    }

    @Override
    public void initSwipeRefresh() {
        swipeRefreshLayout.setProgressBackgroundColorSchemeResource(android.R.color.white);
        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        swipeRefreshLayout.setOnRefreshListener(this);
    }

    @Override
    public void initRecycleView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }
}
