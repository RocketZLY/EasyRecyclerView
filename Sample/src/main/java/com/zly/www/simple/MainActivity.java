package com.zly.www.simple;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.zly.www.easyrecyclerview.EasyDefRecyclerView;
import com.zly.www.easyrecyclerview.listener.OnLoadListener;
import com.zly.www.easyrecyclerview.listener.OnRefreshListener;


public class MainActivity extends AppCompatActivity implements OnRefreshListener, OnLoadListener {

    private EasyDefRecyclerView erv;
    private RvAdapter rvAdapter;
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        erv = (EasyDefRecyclerView) findViewById(R.id.erv);
        erv.setAdapter(rvAdapter = new RvAdapter());
        erv.setOnRefreshListener(this);
        erv.setOnLoadListener(this);

    }

    @Override
    public void onRefreshListener() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                for (int i = 10; i > 0; i--) {
                    rvAdapter.addData("最新数据+" + i,0);
                }
            }
        }, 3000);
    }

    private boolean isFail = false;

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
                        rvAdapter.addData("更多数据+" + i);
                    }
                }
            }
        }, 3000);
    }
}
