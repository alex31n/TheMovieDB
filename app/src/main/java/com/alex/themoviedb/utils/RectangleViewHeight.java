package com.alex.themoviedb.utils;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;

public class RectangleViewHeight extends FrameLayout {

    public RectangleViewHeight(Context context) {
        super(context);
    }

    public RectangleViewHeight(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public RectangleViewHeight(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int height = getMeasuredHeight();
        setMeasuredDimension((int) (height/1.5), height);
    }

}