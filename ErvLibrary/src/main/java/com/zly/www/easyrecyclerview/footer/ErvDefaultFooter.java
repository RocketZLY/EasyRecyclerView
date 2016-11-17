package com.zly.www.easyrecyclerview.footer;

import android.content.Context;
import android.graphics.Color;
import android.text.SpannableStringBuilder;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.zly.www.easyrecyclerview.R;
import com.zly.www.easyrecyclerview.listener.OnLoadListener;

import java.lang.ref.WeakReference;

import static android.text.Spanned.SPAN_INCLUSIVE_INCLUSIVE;

/**
 * 默认底部实现
 * Created by zly on 2016/10/25 0025.
 */

public class ErvDefaultFooter extends FrameLayout implements ErvLoadUIHandle {

    private TextView footer;
    private int mState = LOAD;

    public ErvDefaultFooter(Context context) {
        this(context, null);
    }

    public ErvDefaultFooter(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ErvDefaultFooter(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT));
        View view = LayoutInflater.from(getContext()).inflate(R.layout.erv_default_footer, this);
        footer = (TextView) view.findViewById(R.id.footerTv);
        onLoading();
    }

    @Override
    public void onLoading() {
        mState = LOAD;
        footer.setText(getResources().getString(R.string.erv_loading));
    }

    @Override
    public void onNoMore() {
        mState = NOMORE;
        footer.setText(getResources().getString(R.string.erv_noMore));
    }

    @Override
    public void onLoadFail(OnLoadListener listener) {
        mState = LOADFAIL;
        String content = "加载失败,";
        String click = "点击重试";
        SpannableStringBuilder builder = new SpannableStringBuilder(content+click);
        builder.setSpan(new Click(listener),content.length(),content.length()+click.length(),SPAN_INCLUSIVE_INCLUSIVE);
        footer.setText(builder);
        footer.setMovementMethod(new LinkMovementMethod());
    }

    @Override
    public int getState() {
        return mState;
    }

    class Click extends ClickableSpan{

        private WeakReference<OnLoadListener> reference;

        public Click(OnLoadListener listener) {
            this.reference = new WeakReference<OnLoadListener>(listener);
        }

        @Override
        public void onClick(View v) {
            onLoading();
            OnLoadListener onLoadListener = reference.get();
            if(onLoadListener != null){
                onLoadListener.onLoadListener();
            }
        }

        @Override
        public void updateDrawState(TextPaint ds) {
            ds.setColor(Color.BLUE);
        }
    }
}
