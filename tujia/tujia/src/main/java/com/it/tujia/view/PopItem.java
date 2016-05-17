package com.it.tujia.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.it.tujia.R;

/**
 * Created by kkguo on 2015/11/18.
 */
public class PopItem extends LinearLayout{
    public PopItem(Context context) {
        super(context);
        init(context, null);
    }

    public PopItem(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }
    TextView textView;
    ImageView imageView;
    private void init(Context context, AttributeSet attrs) {
        LayoutInflater.from(context).inflate(R.layout.popup_window_item,this);
        textView = (TextView) findViewById(R.id.tv_pop_item);
        imageView = (ImageView) findViewById(R.id.iv_pop_item);
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.pop);
        String text = array.getString(R.styleable.pop_item_text);
        textView.setText(text);
         Drawable drawable = array.getDrawable(R.styleable.pop_item_img);
        imageView.setImageDrawable(drawable);
    }
    public void setData(String text,int drawble){
        textView.setText(text);
        imageView.setImageResource(drawble);
    }
}
