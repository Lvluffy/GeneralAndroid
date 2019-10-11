package com.luffy.generalandroidlib.android.list.loader;

import com.chad.library.adapter.base.loadmore.LoadMoreView;
import com.luffy.generalandroidlib.R;

/**
 * Created by lvlufei on 2018/1/1
 *
 * @desc 加载更多的界面
 */
public class BaseLayerLoadMoreView extends LoadMoreView {
    @Override
    public int getLayoutId() {
        return R.layout.base_load_more;
    }

    @Override
    protected int getLoadingViewId() {
        return R.id.view_load_more_loading_view;
    }

    @Override
    protected int getLoadFailViewId() {
        return R.id.view_load_more_failed;
    }

    @Override
    protected int getLoadEndViewId() {
        return R.id.view_load_more_load;
    }
}
