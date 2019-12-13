package com.luffy.generalandroidlib.template.tabIndicator;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.util.TypedValue;
import android.view.View;

import com.luffy.generalandroidlib.R;
import com.luffy.generalandroidlib.android.application.BaseLayerApplication;
import com.luffy.generalandroidlib.android.fragment.BaseLayerFragment;
import com.luffy.generalandroidlib.android.fragment.BaseLayerFragmentStatePageAdapter;
import com.luffy.generalandroidlib.android.model.BaseLayerTabIndicatorItemModel;
import com.luffy.indicatorlib.MagicIndicator;
import com.luffy.indicatorlib.ViewPagerHelper;
import com.luffy.indicatorlib.buildins.commonnavigator.CommonNavigator;
import com.luffy.indicatorlib.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import com.luffy.indicatorlib.buildins.commonnavigator.abs.IPagerIndicator;
import com.luffy.indicatorlib.buildins.commonnavigator.abs.IPagerTitleView;
import com.luffy.indicatorlib.buildins.commonnavigator.indicators.LinePagerIndicator;
import com.luffy.indicatorlib.buildins.commonnavigator.titles.ColorTransitionPagerTitleView;
import com.luffy.indicatorlib.buildins.commonnavigator.titles.SimplePagerTitleView;

import java.util.ArrayList;

/**
 * Created by lvlufei on 2018/1/1
 *
 * @desc 公用的TabIndicator（选项卡指示器）模板
 */
public abstract class BaseLayerTabIndicatorFragment extends BaseLayerFragment implements IBaseLayerTabIndicator {
    /*控件*/
    MagicIndicator mMagicIndicator;
    ViewPager mViewPager;
    /*数据*/
    protected ArrayList<BaseLayerTabIndicatorItemModel> tabIndicatorItemModelList = new ArrayList<>();
    /*Fragment*/
    protected ArrayList<Fragment> fragmentList = new ArrayList<>();
    /*当前选中项*/
    private int currentItem = 0;
    /*IndicatorConfig*/
    private int indicatorBackgroundColor = Color.WHITE;
    private boolean indicatorAdjustMode = true;
    private float indicatorTextSize = 16;
    private int indicatorTextStyle = Typeface.NORMAL;
    private int indicatorNormalColor = Color.GRAY;
    private int indicatorSelectedColor = ContextCompat.getColor(BaseLayerApplication.getInstance(), R.color.colorPrimary);
    private int indicatorMode = LinePagerIndicator.MODE_WRAP_CONTENT;
    private float indicatorLineHeight = 5;
    private int indicatorColor = ContextCompat.getColor(BaseLayerApplication.getInstance(), R.color.colorPrimary);

    @Override
    public int setLayoutView() {
        return R.layout.activity_base_layer_tab_indicator;
    }

    @Override
    public void initView() {
        findView();
        init();
        bindTitle();
        bindFragments();
        initIndicatorConfig();
        initMagicIndicator();
        initAdapter();
    }

    @Override
    public void findView() {
        mMagicIndicator = rootView.findViewById(R.id.magicIndicator);
        mViewPager = rootView.findViewById(R.id.viewPager);
    }

    @Override
    public void initMagicIndicator() {
        mMagicIndicator.setBackgroundColor(getIndicatorBackgroundColor());
        /*通用导航器*/
        CommonNavigator commonNavigator = new CommonNavigator(mContext);
        /*调整模式（true：标题平分屏幕宽度；false：默认模式）*/
        commonNavigator.setAdjustMode(isIndicatorAdjustMode());
        commonNavigator.setAdapter(new CommonNavigatorAdapter() {
            @Override
            public int getCount() {
                return tabIndicatorItemModelList == null ? 0 : tabIndicatorItemModelList.size();
            }

            @Override
            public IPagerTitleView getTitleView(Context context, final int index) {
                SimplePagerTitleView simplePagerTitleView = new ColorTransitionPagerTitleView(context);
                simplePagerTitleView.setText(tabIndicatorItemModelList.get(index).getName());
                simplePagerTitleView.setTextSize(TypedValue.COMPLEX_UNIT_SP, getIndicatorTextSize());
                simplePagerTitleView.setTypeface(Typeface.defaultFromStyle(getIndicatorTextStyle()));
                simplePagerTitleView.setNormalColor(getIndicatorNormalColor());
                simplePagerTitleView.setSelectedColor(getIndicatorSelectedColor());
                simplePagerTitleView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mViewPager.setCurrentItem(index);
                    }
                });
                return simplePagerTitleView;
            }

            @Override
            public IPagerIndicator getIndicator(Context context) {
                LinePagerIndicator linePagerIndicator = new LinePagerIndicator(context);
                linePagerIndicator.setMode(getIndicatorMode());
                linePagerIndicator.setLineHeight(getIndicatorLineHeight());
                linePagerIndicator.setColors(getIndicatorColor());
                return linePagerIndicator;
            }
        });
        mMagicIndicator.setNavigator(commonNavigator);
        ViewPagerHelper.bind(mMagicIndicator, mViewPager);
    }

    @Override
    public void initAdapter() {
        mViewPager.setAdapter(new BaseLayerFragmentStatePageAdapter(fragmentList, getChildFragmentManager()));
        mViewPager.setCurrentItem(getCurrentItem());
    }

    @Override
    public void notifyChange() {
        initMagicIndicator();
        initAdapter();
    }

    public int getCurrentItem() {
        return currentItem;
    }

    public void setCurrentItem(int currentItem) {
        this.currentItem = currentItem;
    }

    public int getIndicatorBackgroundColor() {
        return indicatorBackgroundColor;
    }

    public void setIndicatorBackgroundColor(int indicatorBackgroundColor) {
        this.indicatorBackgroundColor = indicatorBackgroundColor;
    }

    public boolean isIndicatorAdjustMode() {
        return indicatorAdjustMode;
    }

    public void setIndicatorAdjustMode(boolean indicatorAdjustMode) {
        this.indicatorAdjustMode = indicatorAdjustMode;
    }

    public float getIndicatorTextSize() {
        return indicatorTextSize;
    }

    public void setIndicatorTextSize(float indicatorTextSize) {
        this.indicatorTextSize = indicatorTextSize;
    }

    public int getIndicatorTextStyle() {
        return indicatorTextStyle;
    }

    public void setIndicatorTextStyle(int indicatorTextStyle) {
        this.indicatorTextStyle = indicatorTextStyle;
    }

    public int getIndicatorNormalColor() {
        return indicatorNormalColor;
    }

    public void setIndicatorNormalColor(int indicatorNormalColor) {
        this.indicatorNormalColor = indicatorNormalColor;
    }

    public int getIndicatorSelectedColor() {
        return indicatorSelectedColor;
    }

    public void setIndicatorSelectedColor(int indicatorSelectedColor) {
        this.indicatorSelectedColor = indicatorSelectedColor;
    }

    public int getIndicatorMode() {
        return indicatorMode;
    }

    public void setIndicatorMode(int indicatorMode) {
        this.indicatorMode = indicatorMode;
    }

    public float getIndicatorLineHeight() {
        return indicatorLineHeight;
    }

    public void setIndicatorLineHeight(float indicatorLineHeight) {
        this.indicatorLineHeight = indicatorLineHeight;
    }

    public int getIndicatorColor() {
        return indicatorColor;
    }

    public void setIndicatorColor(int indicatorColor) {
        this.indicatorColor = indicatorColor;
    }
}
