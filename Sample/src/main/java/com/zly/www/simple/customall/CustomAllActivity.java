package com.zly.www.simple.customall;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.zly.www.easyrecyclerview.EasyDefRecyclerView;
import com.zly.www.easyrecyclerview.listener.OnLoadListener;
import com.zly.www.easyrecyclerview.listener.OnRefreshListener;
import com.zly.www.simple.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 自定义头部和底部
 * Created by zly on 2016/11/28 0028.
 */
public class CustomAllActivity extends AppCompatActivity implements OnRefreshListener, OnLoadListener {

    @BindView(R.id.erv)
    EasyDefRecyclerView erv;

    private RvAdapter rvAdapter;
    private Handler handler = new Handler();
    private boolean isFail = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_all);
        ButterKnife.bind(this);

        erv.setAdapter(rvAdapter = new RvAdapter());
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
                    rvAdapter.insert("最新数据+" + i,0);
                }
            }
        }, 3000);
    }

    @Override
    public void onLoadListener() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                if (rvAdapter.getItemCount() > 20 && !isFail) {
                    isFail = true;
                    erv.loadFail();
                } else if (rvAdapter.getItemCount() > 30) {
                    erv.noMore();
                } else {
                    for (int i = 0; i < 10; i++) {
                        rvAdapter.add("更多数据+" + i);
                    }
                }
            }
        }, 3000);
    }
}
