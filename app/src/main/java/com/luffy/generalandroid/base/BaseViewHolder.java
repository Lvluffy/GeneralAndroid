package com.luffy.generalandroid.base;

import android.view.View;

import com.luffy.generalandroidlib.android.list.viewHolder.BaseLayerViewHolder;

import butterknife.ButterKnife;

/**
 * Created by lvlufei on 2017/12/1
 *
 * @desc 公共-ViewHolder
 */
public class BaseViewHolder extends BaseLayerViewHolder {
    public BaseViewHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void bindButterKnife(View itemView) {
        ButterKnife.bind(this, itemView);
    }
}
