package com.luffy.generalandroid.ui.viewHolder;

import android.view.View;
import android.widget.TextView;

import com.luffy.generalandroid.R;
import com.luffy.generalandroid.base.BaseViewHolder;

import butterknife.BindView;

/**
 * Created by lvlufei on 2019/4/29
 *
 * @desc
 */
public class ListViewHolder extends BaseViewHolder {

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
