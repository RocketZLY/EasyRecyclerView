package com.zly.www.simple.customdecoration;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;

import com.zly.www.easyrecyclerview.EasyRecyclerView;
import com.zly.www.easyrecyclerview.decoration.StickItemDecoration;
import com.zly.www.simple.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 条目吸附demo
 * Created by zly on 2016/11/28 0028.
 */

public class CustomDecorationActivity extends AppCompatActivity {

    @BindView(R.id.erv)
    EasyRecyclerView erv;

    private CustomDecorationAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_stick);
        ButterKnife.bind(this);

        initView();
        initData();
    }

    private void initView() {
        erv.setLayoutManager(new LinearLayoutManager(this));
        erv.setAdapter(mAdapter = new CustomDecorationAdapter());
        erv.addItemDecoration(new StickItemDecoration(this,mAdapter.getData()) {
            @Override
            public String getTag(int position) {
                return mAdapter.getItem(position).tag;
            }

        });
    }
    
    private void initData() {
        mAdapter.add(new CityBean("a","安庆"));
        mAdapter.add(new CityBean("a","安徽"));
        mAdapter.add(new CityBean("a","鞍山"));
        mAdapter.add(new CityBean("a","澳门"));
        mAdapter.add(new CityBean("b","白城"));
        mAdapter.add(new CityBean("b","白沙"));
        mAdapter.add(new CityBean("b","北海"));
        mAdapter.add(new CityBean("b","北京"));
        mAdapter.add(new CityBean("b","保定"));
        mAdapter.add(new CityBean("b","宝鸡"));
        mAdapter.add(new CityBean("c","长沙"));
        mAdapter.add(new CityBean("c","成都"));
        mAdapter.add(new CityBean("c","常德"));
        mAdapter.add(new CityBean("d","大理"));
        mAdapter.add(new CityBean("d","大连"));
        mAdapter.add(new CityBean("d","大庆"));
        mAdapter.add(new CityBean("e","鄂州"));
        mAdapter.add(new CityBean("h","哈尔滨"));
        mAdapter.add(new CityBean("w","万宁"));
        mAdapter.add(new CityBean("w","潍坊"));
        mAdapter.add(new CityBean("w","威海"));
    }

}
