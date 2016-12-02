package com.zly.www.simple.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zly.www.easyrecyclerview.adapter.CommonAdapter;
import com.zly.www.easyrecyclerview.adapter.viewholder.BaseViewHolder;
import com.zly.www.simple.R;
import com.zly.www.simple.customdecoration.CityBean;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by zly on 2016/11/30 0030.
 */

public class CustomDecorationAdapter extends CommonAdapter<CityBean,CustomDecorationAdapter.ViewHolder> {


    @Override
    public ViewHolder createCustomViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(inflateView(R.layout.item_custom_stick,parent));
    }

    @Override
    public void bindCustomViewHolder(ViewHolder holder, CityBean cityBean, int position) {
        holder.tv.setText(cityBean.name);
    }

    class ViewHolder extends BaseViewHolder {

        @BindView(R.id.tv)
        TextView tv;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
