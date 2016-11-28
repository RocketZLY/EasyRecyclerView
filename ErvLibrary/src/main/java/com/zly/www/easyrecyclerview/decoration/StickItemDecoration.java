package com.zly.www.easyrecyclerview.decoration;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

/**
 * 顶部吸附ItemDecoration
 * Created by zly on 2016/11/28 0028.
 */

public abstract class StickItemDecoration<T> extends RecyclerView.ItemDecoration {

    private Paint mPaint;
    private List<T> mList;

    public StickItemDecoration(List<T> mList) {
        this.mList = mList;
        this.mPaint = new Paint();
        mPaint.setTextSize(getStickTextSize());
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
            if (position == 0) {
                drawStickDecoration(left, view.getTop() - getStickHeight(), right, view.getTop(), parent.getPaddingTop(), getTag(position), c);
            } else {
                if (position < mList.size() && !getTag(position).equals(getTag(position - 1))) {
                    drawStickDecoration(left, view.getTop() - getStickHeight(), right, view.getTop(), parent.getPaddingTop(), getTag(position), c);
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

        drawStickDecoration(left, top, right, top + getStickHeight(), parent.getPaddingTop(), getTag(position), c);
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        int position = parent.getChildAdapterPosition(view);
        if (position == 0) {
            outRect.set(0, getStickHeight(), 0, 0);
        } else {
            if (position < mList.size() && !getTag(position).equals(getTag(position - 1))) {
                outRect.set(0, getStickHeight(), 0, 0);
            } else {
                outRect.set(0, 0, 0, 0);
            }
        }
    }

    /**
     * 绘制stick
     */
    private void drawStickDecoration(int left, int top, int right, int bottom, int parentTopPadding, String text, Canvas c) {
        if (top >= parentTopPadding) {
            Rect rect = new Rect(left, top, right, bottom);
            mPaint.setColor(getStickBackgroundColor());
            c.drawRect(rect, mPaint);

            mPaint.setColor(getStickTextColor());
            Paint.FontMetrics fontMetrics = mPaint.getFontMetrics();
            float textTop = fontMetrics.top;
            float textBottom = fontMetrics.bottom;
            int textY = (int) (rect.centerY() - textTop / 2 - textBottom / 2);
            c.drawText(text, left + getStickTextoffset(), textY, mPaint);
        }
    }


    public abstract String getTag(int position);

    public abstract int getStickHeight();

    public abstract int getStickTextSize();

    public abstract int getStickBackgroundColor();

    public abstract int getStickTextColor();

    public abstract int getStickTextoffset();
}
