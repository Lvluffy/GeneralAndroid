package com.luffy.android.listviewlib.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseArrayAdapter<T> extends BaseAdapter {

    protected Context mContext;
    protected List<T> mData;

    public BaseArrayAdapter(Context context) {
        mContext = context;
    }

    @Override
    public int getCount() {
        if (mData == null) {
            return 0;
        } else {
            return mData.size();
        }
    }

    @Override
    public T getItem(int position) {
        if (mData == null) {
            return null;
        } else {
            return mData.get(position);
        }
    }

    @Override
    public long getItemId(int position) {
        if (mData == null) {
            return 0;
        } else {
            return position;
        }
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (mData == null) {
            throw new IllegalStateException("this should only be called when the data is valid");
        }
        if (position < 0 || position >= mData.size()) {
            throw new IllegalStateException("couldn't get view at this position " + position);
        }
        T data = mData.get(position);
        View v;
        if (convertView == null) {
            v = newView(mContext, data, parent);
        } else {
            v = convertView;
        }
        bindData(v, position, data);
        return v;
    }

    /**
     * 更新数据
     *
     * @param data
     */
    public void updateData(List<T> data) {
        if (data != null) {
            mData = data;
            notifyDataSetChanged();
        } else {
            notifyDataSetInvalidated();
        }
    }

    /**
     * 获取数据
     *
     * @return
     */
    public ArrayList<T> getData() {
        ArrayList<T> data = new ArrayList<T>();
        if (mData != null) {
            data.addAll(mData);
        }
        return data;
    }

    /**
     * 获取指定数据的索引
     *
     * @param item
     * @return
     */
    public int getItemPosition(T item) {
        if (item == null || mData == null || mData.isEmpty()) {
            return -1;
        } else {
            return mData.indexOf(item);
        }
    }

    public abstract View newView(Context context, T data, ViewGroup parent);

    public abstract void bindData(View view, int position, T data);
}

