package com.zly.www.easyrecyclerview.decoration;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.zly.www.easyrecyclerview.utils.DisplayUtil;

import java.util.List;

/**
 * 顶部吸附ItemDecoration
 * Created by zly on 2016/11/28 0028.
 */

public abstract class StickItemDecoration extends RecyclerView.ItemDecoration {

    private Context mContext;
    private Paint mPaint;
    private List mList;

    private int mStickHeight;
    private int mStickBackgroundColor;
    private int mStickTextColor;
    private int mStickTextSize;
    private int mStickTextoffset;

    public StickItemDecoration(Context context, List mList) {
        this.mList = mList;
        this.mContext = context;
        this.mPaint = new Paint();

        initProperty();
    }

    private void initProperty() {
        mStickHeight = DisplayUtil.dip2px(mContext, 30);
        mStickTextSize = DisplayUtil.sp2px(mContext, 16);
        mStickTextoffset = DisplayUtil.dip2px(mContext, 2);

        mStickTextColor = Color.WHITE;
        mStickBackgroundColor = 0xffA3A3A3;

        mPaint.setTextSize(mStickTextSize);
        mPaint.setTextAlign(Paint.Align.LEFT);
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
        int left = parent.getPaddingLeft();
        int right = parent.getWidth() - parent.getPaddingRight();

        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View view = parent.getChildAt(i);
            int position = parent.getChildAdapterPosition(view);
            if (position != RecyclerView.NO_POSITION) {
                if (position == 0) {
                    drawStickDecoration(left, view.getTop() - mStickHeight, right, view.getTop(), parent.getPaddingTop(), getTag(position), c);
                } else {
                    if (position < mList.size() && !getTag(position).equals(getTag(position - 1))) {
                        drawStickDecoration(left, view.getTop() - mStickHeight, right, view.getTop(), parent.getPaddingTop(), getTag(position), c);
                    }
                }
            }
        }
    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
        int position = ((LinearLayoutManager) parent.getLayoutManager()).findFirstVisibleItemPosition();
        int left = parent.getPaddingLeft();
        int top = parent.getPaddingTop();
        int right = parent.getWidth() - parent.getPaddingRight();

        /**
         * 增加顶部动画效果
         */
        View childView = parent.findViewHolderForLayoutPosition(position).itemView;
        if (position + 1 < mList.size()) {
            if (!getTag(position).equals(getTag(position + 1))) {
                if (childView.getHeight() + childView.getTop() < mStickHeight) {
                    c.translate(0, childView.getHeight() + childView.getTop() - mStickHeight);
                }
            }
        }

        drawStickDecoration(left, top, right, top + mStickHeight, parent.getPaddingTop(), getTag(position), c);
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        int position = parent.getChildAdapterPosition(view);
        if (position != RecyclerView.NO_POSITION) {
            if (position == 0) {
                outRect.set(0, mStickHeight, 0, 0);
            } else {
                if (position < mList.size() && !getTag(position).equals(getTag(position - 1))) {
                    outRect.set(0, mStickHeight, 0, 0);
                } else {
                    outRect.set(0, 0, 0, 0);
                }
            }
        }
    }

    /**
     * 绘制stick
     */
    private void drawStickDecoration(int left, int top, int right, int bottom, int parentTopPadding, String text, Canvas c) {
        if (top >= parentTopPadding) {
            Rect rect = new Rect(left, top, right, bottom);
            mPaint.setColor(mStickBackgroundColor);
            c.drawRect(rect, mPaint);

            mPaint.setColor(mStickTextColor);
            Paint.FontMetrics fontMetrics = mPaint.getFontMetrics();
            float textTop = fontMetrics.top;
            float textBottom = fontMetrics.bottom;
            int textY = (int) (rect.centerY() - textTop / 2 - textBottom / 2);
            c.drawText(text, left + mStickTextoffset, textY, mPaint);
        }
    }

    public abstract String getTag(int position);


    public int getStickHeight() {
        return mStickHeight;
    }

    public void setStickHeight(int mStickHeight) {
        this.mStickHeight = mStickHeight;
    }

    public int getStickBackgroundColor() {
        return mStickBackgroundColor;
    }

    public void setStickBackgroundColor(int mStickBackgroundColor) {
        this.mStickBackgroundColor = mStickBackgroundColor;
    }

    public int getStickTextColor() {
        return mStickTextColor;
    }

    public void setStickTextColor(int mStickTextColor) {
        this.mStickTextColor = mStickTextColor;
    }

    public int getStickTextSize() {
        return mStickTextSize;
    }

    public void setStickTextSize(int mStickTextSize) {
        this.mStickTextSize = mStickTextSize;
    }

    public int getStickTextoffset() {
        return mStickTextoffset;
    }

    public void setStickTextoffset(int mStickTextoffset) {
        this.mStickTextoffset = mStickTextoffset;
    }
}
