package com.luffy.generalandroid.ui.module.list;

import com.google.gson.Gson;
import com.luffy.generalandroid.R;
import com.luffy.generalandroid.base.BaseListActivity;
import com.luffy.generalandroid.manager.TestDataManager;
import com.luffy.generalandroid.mvp.model.ListBean;
import com.luffy.generalandroidlib.android.list.loader.BaseLayerLoadMoreView;
import com.luffy.generalutilslib.utils.RxTimerUtils;

import java.util.concurrent.TimeUnit;

/**
 * Created by lvlufei on 2019/4/29
 *
 * @desc
 */
public class ListActivity extends BaseListActivity {

    /*适配器*/
    ListAdapter adapter;

    /*数据*/
    ListBean mListBean;

    @Override
    public int setLayoutView() {
        return R.layout.activity_recycle_view;
    }

    @Override
    public void initPresenter() {
        onRefresh();
    }

    @Override
    public void detachViewForPresenter() {
        
    }

    @Override
    public void initReceiveData() {

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

    @Override
    public void onRefresh() {
        swipeRefreshLayout.setRefreshing(true);
        requestData();
    }

    @Override
    public void onLoadMoreRequested() {
        requestData();
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
