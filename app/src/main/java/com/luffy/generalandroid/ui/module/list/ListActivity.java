package com.luffy.generalandroid.ui.module.list;

import com.luffy.generalandroid.R;
import com.luffy.generalandroid.base.BaseActivity;

/**
 * Created by lvlufei on 2019/4/29
 *
 * @desc
 */
public class ListActivity extends BaseActivity {

    @Override
    public int setLayoutView() {
        return 0;
    }

    @Override
    public void initView() {
        replaceFragment(this, R.id.base_child_layout, new ListFragment());
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void detachViewForPresenter() {

    }

    public void replaceFragment(android.support.v4.app.FragmentActivity activity, int resId, android.support.v4.app.Fragment target) {
        if (activity == null) return;
        //碎片管理器
        android.support.v4.app.FragmentManager manager = activity.getSupportFragmentManager();
        //碎片事务
        android.support.v4.app.FragmentTransaction transaction = manager.beginTransaction();
        //替换
        transaction.replace(resId, target, target.getClass().getName());
        //提交
        transaction.commitAllowingStateLoss();
    }
}
