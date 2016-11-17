package com.zly.www.easyrecyclerview.footer;

import com.zly.www.easyrecyclerview.listener.OnLoadListener;

/**
 * 刷新ui操作
 * Created by zly on 2016/10/24 0024.
 */

public interface ErvLoadUIHandle {

    /**
     * 允许加载更多
     */
    int LOAD = 1;

    /**
     * 暂无更多数据
     */
    int NOMORE = 2;

    /**
     * 加载失败
     */
    int LOADFAIL = 3;

    /**
     * @return 获取底部当前状态
     */
    int getState();

    void onLoading();

    void onNoMore();

    void onLoadFail(OnLoadListener listener);


}
