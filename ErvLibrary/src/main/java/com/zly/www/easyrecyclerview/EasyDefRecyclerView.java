package com.zly.www.easyrecyclerview;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.util.AttributeSet;
import android.view.View;

import com.zly.www.easyrecyclerview.footer.ErvDefaultFooter;
import com.zly.www.easyrecyclerview.ptrlib.PtrClassicDefaultHeader;

/**
 * 默认底部空布局实现
 * Created by zly on 2016/11/1 0001.
 */

public class EasyDefRecyclerView extends EasyRecyclerView {


    private PtrClassicDefaultHeader mPtrClassicHeader;

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
        setHeaderView(mPtrClassicHeader = new PtrClassicDefaultHeader(context));
        setFooterView(new ErvDefaultFooter(context));
        setLayoutManager(new LinearLayoutManager(getContext()));
        setOnEmptyViewClick(new OnClickListener() {
            @Override
            public void onClick(View v) {
                autoRefresh();
            }
        });
    }

    public void setLastUpdateTimeKey(String key) {
        if (mPtrClassicHeader != null) {
            mPtrClassicHeader.setLastUpdateTimeKey(key);
        }
    }

    public void setLastUpdateTimeRelateObject(Object object) {
        if (mPtrClassicHeader != null) {
            mPtrClassicHeader.setLastUpdateTimeRelateObject(object);
        }
    }

}
