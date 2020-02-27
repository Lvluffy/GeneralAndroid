package com.luffy.generalandroid.ui.module.main.type;


import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.luffy.generalandroid.R;
import com.luffy.generalandroid.base.BaseListFragment;
import com.luffy.generalandroid.helper.IntentHelper;
import com.luffy.generalandroid.manager.TestDataManager;
import com.luffy.generalandroid.mvp.model.ListBean;
import com.luffy.generalandroid.ui.module.list.ListAdapter;
import com.luffy.generalutilslib.utils.RxTimerUtils;

import java.util.concurrent.TimeUnit;

/**
 * Created by lvlufei on 2018/1/1
 *
 * @desc 首页
 */
public class HomeFragment extends BaseListFragment {

    /*适配器*/
    ListAdapter adapter;

    /*数据*/
    ListBean mListBean;

    @Override
    public boolean visibilityDivider() {
        return false;
    }

    @Override
    public boolean visibilityTitleLayout() {
        return true;
    }

    @Override
    public String setTitleString() {
        return "欢迎进入";
    }

    @Override
    public int setBgColor() {
        return R.color.white;
    }

    @Override
    public int setLayoutView() {
        return R.layout.fragment_home;
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
        }
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                IntentHelper.List.gotoListActivity(mContext, null);
            }
        });
    }

    @Override
    public void onRefresh() {
        swipeRefreshLayout.setRefreshing(true);
        requestData();
    }

    @Override
    public void onLoadMoreRequested() {
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
