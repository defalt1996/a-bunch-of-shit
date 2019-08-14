package com.defalt.a_bunch_of_shit.base.baseview;
/*
 *  author: Derrick
 *  Time: 2019/7/18 23:21
 */

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.defalt.a_bunch_of_shit.R;


public class ButtonImageText extends LinearLayout {

    private ImageView imageView;
    private TextView textView;
    private LinearLayout.LayoutParams params;

    public ButtonImageText(Context context) {
        this(context,null);
    }

    public ButtonImageText(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public ButtonImageText(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LayoutInflater.from(context).inflate(R.layout.btn_img_text, this, true);
        this.imageView = findViewById(R.id.btn_image);
        this.textView = findViewById(R.id.btn_text);
        this.setClickable(true);
        this.setFocusable(true);
        params = new LayoutParams(100, 100);
    }

    public void setText(String text){
        textView.setText(text);
    }

    public void setTextSize(int size){
        textView.setTextSize(size);
    }

    public void setImage(int resourceId){
        imageView.setImageResource(resourceId);
    }
    public void setImageSize(int width, int height){
        params.width = width;
        params.height = height;
        imageView.setLayoutParams(params);
    }

}
