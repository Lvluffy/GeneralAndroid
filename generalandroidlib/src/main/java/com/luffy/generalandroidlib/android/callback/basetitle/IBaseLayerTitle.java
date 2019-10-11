package com.luffy.generalandroidlib.android.callback.basetitle;

/**
 * Created by lvlufei on 2018/1/1
 *
 * @desc 公共标题栏回调监听（Activity、fragment）
 */
public interface IBaseLayerTitle {
    /**
     * 设置标题栏可见与否
     *
     * @return
     */
    boolean visibilityTitleLayout();

    /**
     * 设置返回可见与否
     *
     * @return
     */
    boolean visibilityBack();

    /**
     * 设置返回可见与否
     *
     * @return
     */
    boolean visibilityBackImg();

    /**
     * 设置关闭可见与否
     *
     * @return
     */
    boolean visibilityCloseImg();

    /**
     * 设置关闭可见与否
     *
     * @param b
     */
    void visibilityCloseImg(boolean b);

    /**
     * 设置返回可见与否
     *
     * @return
     */
    boolean visibilityBackTxt();

    /**
     * 设置更多可见与否
     *
     * @return
     */
    boolean visibilityMore();

    /**
     * 设置更多可见与否
     *
     * @return
     */
    boolean visibilityMoreImg();

    /**
     * 设置更多可见与否
     *
     * @return
     */
    boolean visibilityMoreTxt();

    /**
     * 设置标题栏背景色
     *
     * @return
     */
    int setBgColor();

    /**
     * 设置不透明度
     *
     * @@return alpha（0-255：0为完全透明，255为不透明）
     */
    int setAlpha();

    /**
     * 设置返回图标
     *
     * @return
     */
    int setBackImg();

    /**
     * 设置关闭图标
     *
     * @return
     */
    int setCloseImg();

    /**
     * 设置返回
     *
     * @return
     */
    int setBackInt();

    /**
     * 设置返回
     *
     * @return
     */
    String setBackString();

    /**
     * 设置返回文案（网络获取）
     *
     * @param backStr
     */
    void setBackString(String backStr);

    /**
     * 设置返回字体颜色
     *
     * @return
     */
    int setBackColor();

    /**
     * 设置返回字体大小
     *
     * @return
     */
    int setBackSize();

    /**
     * 设置标题
     *
     * @return
     */
    int setTitleInt();

    /**
     * 设置标题
     *
     * @return
     */
    String setTitleString();

    /**
     * 设置标题文案（网络获取）
     *
     * @param titleStr
     */
    void setTitleString(String titleStr);

    /**
     * 设置标题字体颜色
     *
     * @return
     */
    int setTitleColor();

    /**
     * 设置标题字体大小
     *
     * @return
     */
    int setTitleSize();

    /**
     * 设置更多图标
     *
     * @return
     */
    int setMoreImg();

    /**
     * 设置更多
     *
     * @return
     */
    int setMoreInt();

    /**
     * 设置更多
     *
     * @return
     */
    String setMoreString();

    /**
     * 设置更多文案（网络获取）
     *
     * @param moreStr
     */
    void setMoreString(String moreStr);

    /**
     * 设置更多字体颜色
     *
     * @return
     */
    int setMoreColor();

    /**
     * 设置更多字体大小
     *
     * @return
     */
    int setMoreSize();

    /**
     * 设置分割线可见与否
     *
     * @return
     */
    boolean visibilityDivider();

    /**
     * 设置分割线颜色
     *
     * @return
     */
    int setDividerColor();

    /**
     * 导航栏点击事件
     */
    void onClickNav();

    /**
     * 返回按钮点击事件
     */
    void onClickBack();

    /**
     * 更多按钮点击事件
     */
    void onClickMore();
}
