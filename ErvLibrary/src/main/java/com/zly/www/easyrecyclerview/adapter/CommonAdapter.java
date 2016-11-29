package com.zly.www.easyrecyclerview.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zly.www.easyrecyclerview.adapter.viewholder.BaseViewHolder;
import com.zly.www.easyrecyclerview.adapter.viewholder.FooterViewHolder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * 通用adapter
 * Created by zly on 2016/10/27 0027.
 */

public abstract class CommonAdapter<T, VH extends BaseViewHolder> extends RecyclerView.Adapter<BaseViewHolder> {

    public static final int TYPE_FOOTER = 233333;
    private final String TAG = "CommonAdapter";
    private final Object mLock = new Object();
    private Context mContext;

    private View mFooter;
    private RecyclerView mRecyclerView;
    private List<T> mDatas = new ArrayList<>();

    @Override
    public int getItemCount() {
        return mDatas == null || mDatas.size() == 0 ? 0 : mFooter == null ? mDatas.size() : mDatas.size() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (getFooter() != null && getItemCount() - 1 == position) {
            return TYPE_FOOTER;
        } else {
            return super.getItemViewType(position);
        }
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mContext == null)
            mContext = parent.getContext();
        if (mRecyclerView == null)
            mRecyclerView = (RecyclerView) parent;

        if (viewType == TYPE_FOOTER) {
            return new FooterViewHolder(mFooter);
        }
        return createCustomViewHolder(parent, viewType);
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        if (getItemViewType(position) != TYPE_FOOTER)
            bindCustomViewHolder((VH) holder, mDatas.get(position), position);
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

    public void add(@NonNull T object) {
        synchronized (mLock) {
            if (null != mDatas) {
                mDatas.add(object);
                notifyItemInserted(mDatas.size() - 1);
            }
        }
    }

    public void addAll(@NonNull Collection<? extends T> collection) {
        synchronized (mLock) {
            if (null != mDatas) {
                mDatas.addAll(collection);

                if (mDatas.size() - collection.size() != 0) {
                    notifyItemRangeInserted(mDatas.size() - collection.size(), collection.size());
                } else {
                    notifyDataSetChanged();
                }
            }
        }

    }

    @SafeVarargs
    public final void addAll(@NonNull T... items) {
        synchronized (mLock) {
            if (null != mDatas) {
                Collections.addAll(mDatas, items);
                if (mDatas.size() - items.length != 0) {
                    notifyItemRangeInserted(mDatas.size() - items.length, items.length);
                } else {
                    notifyDataSetChanged();
                }
            }
        }
    }

    public void insert(@NonNull T object, int index) {
        if (mDatas == null || index < 0 || index > mDatas.size()) {
            Log.i(TAG, "insert: index error");
            return;
        }
        synchronized (mLock) {
            if (null != mDatas) {
                mDatas.add(index, object);
                notifyItemInserted(index);

                if (index == 0)
                    scrollTop();
            }
        }
    }

    public void insertAll(@NonNull Collection<? extends T> collection, int index) {
        if (mDatas == null || index < 0 || index > mDatas.size()) {
            Log.i(TAG, "insertAll: index error");
            return;
        }
        synchronized (mLock) {
            if (null != mDatas) {
                mDatas.addAll(index, collection);
                notifyItemRangeInserted(index, collection.size());

                if (index == 0)
                    scrollTop();
            }
        }
    }

    public void remove(int index) {
        if (mDatas == null || index < 0 || index > mDatas.size() - 1) {
            Log.i(TAG, "remove: index error");
            return;
        }
        synchronized (mLock) {
            mDatas.remove(index);
            notifyItemRemoved(index);
        }
    }

    public boolean remove(@NonNull T object) {
        int removeIndex = -1;
        boolean removeSuccess = false;

        if (mDatas == null || mDatas.size() == 0) {
            Log.i(TAG, "remove fail datas emply");
            return false;
        }

        synchronized (mLock) {
            removeIndex = mDatas.indexOf(object);
            removeSuccess = mDatas.remove(object);
        }

        if (removeSuccess) {
            notifyItemRemoved(removeIndex);
            return true;
        }
        return false;
    }

    public void clear() {
        synchronized (mLock) {
            if (mDatas != null) {
                mDatas.clear();
            }
        }
        notifyDataSetChanged();
    }

    public void sort(Comparator<? super T> comparator) {
        synchronized (mLock) {
            if (mDatas != null) {
                Collections.sort(mDatas, comparator);
            }
        }
        notifyDataSetChanged();
    }

    public void update(@NonNull List<T> mDatas) {
        synchronized (mLock) {
            this.mDatas = mDatas;
        }
        notifyDataSetChanged();
    }

    public T getItem(int position) {
        return mDatas.get(position);
    }

    public int getPosition(T item) {
        return mDatas.indexOf(item);
    }

    public List<T> getData() {
        if (null == mDatas)
            mDatas = new ArrayList<>();
        return mDatas;
    }

    /**
     * 处理insert index为0时新数据未显示问题
     */
    private void scrollTop() {
        if (null != mRecyclerView)
            mRecyclerView.scrollToPosition(0);
    }

    public abstract VH createCustomViewHolder(ViewGroup parent, int viewType);

    public abstract void bindCustomViewHolder(VH holder, T t, int position);

}
