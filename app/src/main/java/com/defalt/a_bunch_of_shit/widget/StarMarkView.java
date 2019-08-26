package com.defalt.a_bunch_of_shit.widget;
/*
 *  author: Derrick
 *  Time: 2019/8/26 0:50
 */

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.DrawableRes;
import androidx.annotation.Nullable;

import com.defalt.a_bunch_of_shit.R;

public class StarMarkView extends View {

    private static final String TAG = "StarMarkView";
    private int mark;
    private int size;
    private int fullIconNum;
    private int halfIconNum;
    private Context context;
    private int mIconFull;
    private int mIconHalf;
    private int mIconNone;

    public StarMarkView(Context context) {
        super(context);
    }

    public StarMarkView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }

    public StarMarkView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

//    public StarMarkView(Context context, double marks, @DrawableRes int iconFull, @DrawableRes int iconHalf) {
//        this(context, null, marks, iconFull, iconHalf);
//    }
//
//    public StarMarkView(Context context, @Nullable AttributeSet attrs,double marks, @DrawableRes int iconFull, @DrawableRes int iconHalf) {
//        this(context, attrs, 0, marks, iconFull, iconHalf);
//    }
//
//
//
//    public StarMarkView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, double marks, @DrawableRes int iconFull, @DrawableRes int iconHalf) {
//        super(context, attrs, defStyleAttr);
//        this.context = context;
//        init(marks, iconFull, iconHalf);
//    }

    public void setParams(double marks, int iconFull, int iconHalf, int iconNone) {
        int roundedMarks = (int) Math.round(marks);
        fullIconNum = roundedMarks / 2;
        halfIconNum = roundedMarks % 2;
        mIconFull = iconFull;
        mIconHalf = iconHalf;
        mIconNone = iconNone;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = (int) (width / 5.0);
        size = height;
        Log.d(TAG, "width = "+ width + "  height = "+ height);
        setMeasuredDimension(width, height);
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

    public int getMark() {
        return mark;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();
        Bitmap bitmapIconFull = BitmapFactory.decodeResource(context.getResources(), mIconFull);
        Bitmap bitmapIconHalf = BitmapFactory.decodeResource(context.getResources(), mIconHalf);
        Bitmap bitmapIconNone = BitmapFactory.decodeResource(context.getResources(), mIconNone);
        int x = 0;
        int y = 0;
        int num = 0;
        for (int i=0; i < fullIconNum; i++){
            Rect dst = new Rect();
            dst.left = x;
            dst.top = y;
            dst.right = x + size;
            dst.bottom = y + size;
//            canvas.drawBitmap(bitmapIconFull, x, 0, paint);
            canvas.drawBitmap(bitmapIconFull, null, dst, null);
            x = x + size;
            num ++;
        }
        if (halfIconNum != 0){
            Rect dst = new Rect();
            dst.left = x;
            dst.top = y;
            dst.right = x + size;
            dst.bottom = y + size;
//            canvas.drawBitmap(bitmapIconFull, x, 0, paint);
            canvas.drawBitmap(bitmapIconHalf, null, dst, null);
            num ++;
            x = x + size;
        }
        int startNoneNum = 5 - num;
        for (int i=0; i < startNoneNum; i++){
            Rect dst = new Rect();
            dst.left = x;
            dst.top = y;
            dst.right = x + size;
            dst.bottom = y + size;
//            canvas.drawBitmap(bitmapIconFull, x, 0, paint);
            canvas.drawBitmap(bitmapIconNone, null, dst, null);
            x = x + size;
        }

    }
}
