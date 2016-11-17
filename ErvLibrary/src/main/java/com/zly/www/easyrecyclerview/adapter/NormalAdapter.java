package com.zly.www.easyrecyclerview.adapter;

import com.zly.www.easyrecyclerview.adapter.viewholder.BaseViewHolder;

/**
 * 一般单条目adapter
 * Created by zly on 2010/31 0031.
 */

public abstract class NormalAdapter<T, VH extends BaseViewHolder> extends BaseAdapter<T, VH> {

    @Override
    public int getItemViewType(int position) {
        if(getFooter() != null && getItemCount() - 1 == position){
            return TYPE_FOOTER;
        }
        return TYPE_NORMAL;
    }
}
