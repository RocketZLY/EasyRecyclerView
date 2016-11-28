package com.zly.www.easyrecyclerview.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zly.www.easyrecyclerview.adapter.viewholder.BaseViewHolder;
import com.zly.www.easyrecyclerview.adapter.viewholder.FooterViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * 通用adapter
 * Created by zly on 2016/10/27 0027.
 */

public abstract class CommonAdapter<T, VH extends BaseViewHolder> extends RecyclerView.Adapter<BaseViewHolder> {

    public static final int TYPE_FOOTER = 233333;

    private View mFooter;
    private List<T> mList;
    private Context mContext;

    @Override
    public int getItemCount() {
        return mList == null || mList.size() == 0 ? 0 : mFooter == null ? mList.size() : mList.size() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        if(getFooter() != null && getItemCount() - 1 == position){
            return TYPE_FOOTER;
        }else{
            return super.getItemViewType(position);
        }
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mContext == null)
            mContext = parent.getContext();

        if (viewType == TYPE_FOOTER) {
            return new FooterViewHolder(mFooter);
        }
        return createCustomViewHolder(parent, viewType);
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        if (getItemViewType(position) != TYPE_FOOTER)
            bindCustomViewHolder((VH) holder, mList.get(position), position);
    }

    public View inflateView(@LayoutRes int resId, ViewGroup parent) {
        return LayoutInflater.from(mContext).inflate(resId, parent, false);
    }

    public Context getContext() {
        return mContext;
    }

    public View getFooter() {
        return mFooter;
    }

    public void setFooter(View footer) {
        mFooter = footer;
    }

    public void removeFooter() {
        mFooter = null;
    }

    public List<T> getDatas() {
        return mList;
    }

    public void setDatas(List<T> list) {
        mList = list;
        notifyDataSetChanged();
    }

    public void addData(T t) {
        addData(getItemCount() - 1, t);
    }

    public void addData(int position, T t) {
        if (mList == null)
            mList = new ArrayList<>();

        mList.add(position, t);
        notifyItemInserted(position);
    }

    public void addDatas(List<T> list) {
        addDatas(getItemCount() - 1, list);
    }

    public void addDatas(int position, List<T> list) {
        if (mList == null)
            mList = new ArrayList<>();

        mList.addAll(position, list);
        notifyItemRangeInserted(position, list.size());
    }

    public void removeData(int position) {
        if (mList != null) {
            mList.remove(position);
            notifyItemRemoved(position);
        }
    }

    public void removeData(T t) {
        removeData(mList.indexOf(t));
    }

    public void removeData(List<T> t) {
        if (mList != null) {
            mList.removeAll(t);
            notifyDataSetChanged();
        }
    }


    public void clearDatas() {
        if (mList != null) {
            mList.clear();
            mList = null;
            notifyDataSetChanged();
        }
    }

    public abstract VH createCustomViewHolder(ViewGroup parent, int viewType);

    public abstract void bindCustomViewHolder(VH holder, T t, int position);

}
