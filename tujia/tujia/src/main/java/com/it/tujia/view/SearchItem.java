package com.it.tujia.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.it.tujia.R;

/**
 * Created by kkguo on 2015/11/17.
 */
public class SearchItem extends RelativeLayout{
    public SearchItem(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }
    ImageView img;
    TextView title;
    private void init(Context context, AttributeSet attrs) {
        LayoutInflater.from(context).inflate(R.layout.frag_search_item, this);
         TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.SearchItem);
        img = (ImageView) findViewById(R.id.iv_frag_search_item);
         Drawable drawable = array.getDrawable(R.styleable.SearchItem_item_icon);
        img.setImageDrawable(drawable);
        title = (TextView) findViewById(R.id.city_frag_search_item);
         String str = array.getString(R.styleable.SearchItem_item_city);
        title.setText(str);
         int color = array.getInteger(R.styleable.SearchItem_item_color, Color.BLACK);
        title.setTextColor(color);
        int line=array.getInteger(R.styleable.SearchItem_item_line, Color.GRAY);
        ImageView IvLine = (ImageView) findViewById(R.id.iv_search_item_line);
        IvLine.setBackgroundColor(line);
         ImageView backIcon = (ImageView) findViewById(R.id.iv_back);
         Drawable backImg = array.getDrawable(R.styleable.SearchItem_item_back);
        backIcon.setImageDrawable(backImg);
         TextView more = (TextView) findViewById(R.id.more_frag_search_item);
        boolean showMore = array.getBoolean(R.styleable.SearchItem_item_more, false);
        if(showMore){
            more.setVisibility(VISIBLE);
        }

    }

    public SearchItem(Context context) {
        super(context);
      init(context, null);
    }

    public  void setData(String titles,int drawable,int colors){
        title.setText(titles);
        img.setImageResource(drawable);
        title.setTextColor(colors);
    }

    public String getCity(){
       return String.valueOf(title.getText());
    }
    public void setCity(String city){
        title.setText(city);
    };
}
