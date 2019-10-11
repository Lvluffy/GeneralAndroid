package com.luffy.generalandroid.ui.module.list;

import android.support.annotation.Nullable;
import android.view.ViewGroup;

import com.luffy.generalandroid.R;
import com.luffy.generalandroid.mvp.model.ListBean;
import com.luffy.generalandroid.ui.viewHolder.ListViewHolder;
import com.luffy.generalandroidlib.android.list.adapter.BaseLayerAdapter;

import java.util.List;

/**
 * Created by lvlufei on 2019/4/29
 *
 * @desc
 */
public class ListAdapter extends BaseLayerAdapter<ListBean.BodyBean, ListViewHolder> {
    public ListAdapter(@Nullable List<ListBean.BodyBean> data) {
        super(data);
    }
    @Override
    protected ListViewHolder createBaseViewHolder(ViewGroup parent, int layoutResId) {
        return super.createBaseViewHolder(parent, R.layout.item_list_layout);
    }

    @Override
    protected void convert(ListViewHolder helper, ListBean.BodyBean item) {
        /*顾问名称*/
        helper.txtCounselorName.setText(item.getName());
        /*顾问类别*/
        helper.txtCounselorType.setText(item.getType());
        /*点赞数量*/
        helper.txtPraise.setText(item.getAllProfessionalLevel());
        /*评价数量*/
        helper.txtEvaluate.setText(item.getTotalCount());
    }
}
