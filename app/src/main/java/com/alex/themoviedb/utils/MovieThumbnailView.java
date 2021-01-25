package com.alex.themoviedb.utils;

import android.content.Context;
import android.util.AttributeSet;

public class MovieThumbnailView extends androidx.appcompat.widget.AppCompatImageView {

    public MovieThumbnailView(Context context) {
        super(context);
    }

    public MovieThumbnailView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MovieThumbnailView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int width = getMeasuredWidth();
        setMeasuredDimension(width, (int) (width/1.3));
    }

}