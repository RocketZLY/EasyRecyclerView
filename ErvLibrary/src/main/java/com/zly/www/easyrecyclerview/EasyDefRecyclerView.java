package com.zly.www.easyrecyclerview;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.util.AttributeSet;
import android.view.View;

import com.zly.www.easyrecyclerview.footer.ErvDefaultFooter;

/**
 * 默认底部空布局实现
 * Created by zly on 2016/11/1 0001.
 */

public class EasyDefRecyclerView extends EasyRecyclerView {


    public EasyDefRecyclerView(Context context) {
        this(context, null);
    }

    public EasyDefRecyclerView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public EasyDefRecyclerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        setLayoutManager(new LinearLayoutManager(getContext()));
        setFooterView(new ErvDefaultFooter(context));
        setOnEmptyViewClick(new OnClickListener() {
            @Override
            public void onClick(View v) {
                autoRefresh();
            }
        });
    }

}
