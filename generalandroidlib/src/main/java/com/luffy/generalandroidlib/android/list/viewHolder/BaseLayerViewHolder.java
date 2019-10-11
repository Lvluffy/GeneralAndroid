package com.luffy.generalandroidlib.android.list.viewHolder;

import android.support.annotation.IdRes;
import android.util.SparseArray;
import android.view.View;

import com.chad.library.adapter.base.BaseViewHolder;

/**
 * Created by lvlufei on 2018/1/1
 *
 * @desc 公用的ViewHolder
 * 包括：来自于BaseQuickAdapter中BaseViewHolder的再次封装
 */
public abstract class BaseLayerViewHolder extends BaseViewHolder implements IBaseLayerViewHolder {

    private SparseArray<View> views;

    public BaseLayerViewHolder(View itemView) {
        super(itemView);
        this.views = new SparseArray<>();
        bindButterKnife(itemView);
    }

    public BaseLayerViewHolder setVisible(@IdRes int viewId, boolean visible) {
        View view = getView(viewId);
        view.setVisibility(visible ? View.VISIBLE : View.GONE);
        return this;
    }

    public <T extends View> T getView(@IdRes int viewId) {
        View view = views.get(viewId);
        if (view == null) {
            view = itemView.findViewById(viewId);
            views.put(viewId, view);
        }
        return (T) view;
    }
}
