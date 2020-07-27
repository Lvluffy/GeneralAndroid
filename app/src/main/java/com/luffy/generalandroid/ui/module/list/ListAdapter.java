package com.luffy.generalandroid.ui.module.list;

import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.luffy.generalandroid.R;
import com.luffy.generalandroid.base.BaseViewHolder;
import com.luffy.generalandroid.mvp.model.ListBean;
import com.luffy.recyclerviewlib.adapter.BaseLayerAdapter;

import java.util.List;

import butterknife.BindView;

/**
 * Created by lvlufei on 2019/4/29
 *
 * @desc
 */
public class ListAdapter extends BaseLayerAdapter<ListBean.BodyBean, ListAdapter.ListViewHolder> {
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

    static class ListViewHolder extends BaseViewHolder {

        @BindView(R.id.txt_counselor_name)
        public TextView txtCounselorName;
        @BindView(R.id.txt_counselor_type)
        public TextView txtCounselorType;
        @BindView(R.id.txt_praise)
        public TextView txtPraise;
        @BindView(R.id.txt_evaluate)
        public TextView txtEvaluate;

        public ListViewHolder(View itemView) {
            super(itemView);
        }
    }
}
