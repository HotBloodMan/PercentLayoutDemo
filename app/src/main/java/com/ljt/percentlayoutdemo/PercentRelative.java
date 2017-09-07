package com.ljt.percentlayoutdemo;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.MenuRes;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

/**
 * Created by 1 on 2017/9/7.
 */

public class PercentRelative extends RelativeLayout {

    public PercentRelative(Context context) {
        super(context);
    }

    public PercentRelative(Context context, AttributeSet attrs) {
        super(context, attrs);

    }

    public PercentRelative(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int sizeWidth = View.MeasureSpec.getSize(widthMeasureSpec);
        int sizeHeight = View.MeasureSpec.getSize(heightMeasureSpec);
        int childCount = getChildCount();
        for(int i=0;i<childCount;i++){
            ViewGroup.LayoutParams params = getChildAt(i).getLayoutParams();
            float widthPercent=0;
            float heightPercent=0;
            if(params instanceof PercentRelative.LayoutParams){
                widthPercent=((PercentRelative.LayoutParams) params).widthPercent;
                heightPercent=((PercentRelative.LayoutParams) params).heightPercent;
            }
            if(widthPercent!=0){
                params.width= (int) (sizeWidth*widthPercent);
            }
            if(heightPercent!=0){
                params.height= (int) (sizeHeight*heightPercent);
            }

        }
        setMeasuredDimension(sizeWidth,sizeHeight);

    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new LayoutParams(getContext(),attrs);
    }
    public static  class LayoutParams extends RelativeLayout.LayoutParams{
        private float widthPercent;
        private float heightPercent;

        public LayoutParams(Context c, AttributeSet attrs) {
            super(c, attrs);
            TypedArray typedArray = c.obtainStyledAttributes(attrs, R.styleable.percentrelative);
            widthPercent= typedArray.getFloat(R.styleable.percentrelative_percentwidth,widthPercent);
            heightPercent = typedArray.getFloat(R.styleable.percentrelative_percentheight,heightPercent);
            typedArray.recycle();
        }

    }
}
