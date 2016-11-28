package com.zly.www.simple.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zly.www.easyrecyclerview.adapter.CommonAdapter;
import com.zly.www.easyrecyclerview.adapter.viewholder.BaseViewHolder;
import com.zly.www.simple.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by zly on 2016/10/13 0013.
 */

public class RvAdapter extends CommonAdapter<String, RvAdapter.ViewHolder> {


    @Override
    public ViewHolder createCustomViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(inflateView(R.layout.item, parent));
    }

    @Override
    public void bindCustomViewHolder(ViewHolder holder, String s, int position) {
        holder.tv.setText(s);
    }

    class ViewHolder extends BaseViewHolder {
        @BindView(R.id.tv)
        TextView tv;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

}
