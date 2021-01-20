package com.luffy.generalandroid.ui.module.list;

import android.support.v4.app.Fragment;

import com.google.gson.Gson;
import com.luffy.generalandroid.base.BaseListFragment;
import com.luffy.generalandroid.manager.TestDataManager;
import com.luffy.generalandroid.mvp.model.ListBean;
import com.luffy.recyclerviewlib.loader.BaseLayerLoadMoreView;
import com.luffy.utils.rxlib.RxTimerUtils;

import java.util.concurrent.TimeUnit;

/**
 * A simple {@link Fragment} subclass.
 */
public class ListFragment extends BaseListFragment {

    /*适配器*/
    ListAdapter adapter;

    /*数据*/
    ListBean mListBean;

    @Override
    public void initReceiveData() {

    }

    @Override
    public void initPresenter() {
        onRefresh();
    }

    @Override
    public void detachViewForPresenter() {

    }

    @Override
    public void onRefresh() {
        swipeRefreshLayout.setRefreshing(true);
        requestData();
    }

    @Override
    public void onLoadMoreRequested() {

    }

    @Override
    public void initAdapter() {
        if (adapter == null) {
            adapter = new ListAdapter(null);
            adapter.setOnLoadMoreListener(this, recyclerView);
            adapter.setLoadMoreView(new BaseLayerLoadMoreView());
        }
        recyclerView.setAdapter(adapter);
    }

    private void requestData() {
        mListBean = new Gson().fromJson(TestDataManager.message, ListBean.class);
        /*延时操作*/
        RxTimerUtils.getInstance().interval(2, TimeUnit.SECONDS, new RxTimerUtils.IRxNext() {
            @Override
            public void doNext(long l) {
                swipeRefreshLayout.setRefreshing(false);
                adapter.setNewData(mListBean.getBody());
                adapter.loadMoreEnd();
                recyclerView.scrollToPosition(0);
            }
        });
    }
}
