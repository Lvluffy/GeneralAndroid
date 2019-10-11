package com.luffy.generalandroidlib.android.list.adapter;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.util.List;

/**
 * Created by lvlufei on 2018/1/1
 *
 * @desc 公用的Adapter
 * 包括：来自于BaseQuickAdapter的再次封装
 */
public abstract class BaseLayerMultiItemAdapter<T extends MultiItemEntity, K extends BaseViewHolder> extends BaseMultiItemQuickAdapter<T, K> {
    public BaseLayerMultiItemAdapter(List<T> data) {
        super(data);
    }

    public int getItemPosition(T item) {
        return item != null && this.mData != null && !this.mData.isEmpty() ? this.mData.indexOf(item) : -1;
    }
}
