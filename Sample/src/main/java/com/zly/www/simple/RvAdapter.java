package com.zly.www.simple;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zly.www.easyrecyclerview.adapter.NormalAdapter;
import com.zly.www.easyrecyclerview.adapter.viewholder.BaseViewHolder;

/**
 * Created by zly on 2016/10/13 0013.
 */

public class RvAdapter extends NormalAdapter<String, RvAdapter.ViewHolder> {


    @Override
    public ViewHolder createCustomViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_test, parent, false));
    }

    @Override
    public void bindCustomViewHolder(ViewHolder holder, String s, int position) {
        holder.tv.setText(s);
    }

    class ViewHolder extends BaseViewHolder {
        TextView tv;

        ViewHolder(View itemView) {
            super(itemView);
            tv = (TextView) itemView.findViewById(R.id.tv);
        }
    }

}
