package com.luffy.generalandroidlib.android.model;

import java.io.Serializable;

/**
 * Created by lvlufei on 2018/1/1
 *
 * @desc TabIndicator（选项卡指示器）子项数据模型
 */
public class BaseLayerTabIndicatorItemModel implements Serializable {
    /*名称*/
    private String name;
    /*类型*/
    private int type;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
