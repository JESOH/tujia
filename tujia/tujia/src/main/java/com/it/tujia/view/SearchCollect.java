package com.it.tujia.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.it.tujia.R;

/**
 * Created by kkguo on 2015/11/17.
 */
public class SearchCollect extends RelativeLayout{
    public SearchCollect(Context context) {
        super(context);
    }

    public SearchCollect(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }
    ImageView img;
    TextView title;
    TextView desp;
    private void init(Context context, AttributeSet attrs) {
        LayoutInflater.from(context).inflate(R.layout.frag_search_collect,this);

        img = (ImageView) findViewById(R.id.iv_frag_search_collect);

        title = (TextView) findViewById(R.id.tv2_frag_search_collect);

        desp = (TextView) findViewById(R.id.tv2_frag_search_collect);
        TypedArray array = context.obtainStyledAttributes(attrs,R.styleable.Search);

        // 获取图片
        Drawable icon =
                array.getDrawable(R.styleable.Search_collect_icon);

        img.setImageDrawable(icon);


        // 获取文本
        String titles =
                array.getString(R.styleable.Search_collect_title);

        title.setText(titles);
        String desps =
                array.getString(R.styleable.Search_collect_desp);
        desp.setText(desps);

        array.recycle();
    }
    public void setData(int drawable,String title,String desp){
        img.setImageResource(drawable);
        this.title.setText(title);
        this.desp.setText(desp);
    }
}
