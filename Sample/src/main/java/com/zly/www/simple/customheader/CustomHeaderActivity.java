package com.zly.www.simple.customheader;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.zly.www.easyrecyclerview.EasyDefRecyclerView;
import com.zly.www.easyrecyclerview.listener.OnLoadListener;
import com.zly.www.easyrecyclerview.listener.OnRefreshListener;
import com.zly.www.easyrecyclerview.ptrlib.header.MaterialHeader;
import com.zly.www.simple.R;
import com.zly.www.simple.adapter.CustomAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 自定义头部
 * Created by zly on 2016/11/28 0028.
 */
public class CustomHeaderActivity extends AppCompatActivity implements OnRefreshListener, OnLoadListener {

    @BindView(R.id.erv)
    EasyDefRecyclerView erv;

    private CustomAdapter mAdapter;
    private Handler handler = new Handler();
    private boolean isFail = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom);
        ButterKnife.bind(this);

        erv.setHeaderView(new MaterialHeader(this));
        erv.setPinContent(true);
        erv.setAdapter(mAdapter = new CustomAdapter());
        erv.setLastUpdateTimeRelateObject(this);
        erv.setOnRefreshListener(this);
        erv.setOnLoadListener(this);
    }

    @Override
    public void onRefreshListener() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    mAdapter.insert("最新数据",0);
                }
            }
        }, 3000);
    }

    @Override
    public void onLoadListener() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                if (mAdapter.getItemCount() > 20 && !isFail) {
                    isFail = true;
                    erv.loadFail();
                } else if (mAdapter.getItemCount() > 30) {
                    erv.noMore();
                } else {
                    for (int i = 0; i < 10; i++) {
                        mAdapter.add("更多数据");
                    }
                }
            }
        }, 3000);
    }
}
