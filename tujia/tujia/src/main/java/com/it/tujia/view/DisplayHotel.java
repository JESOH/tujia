package com.it.tujia.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.it.tujia.R;
import com.it.tujia.application.App;
import com.it.tujia.entity.Hotel;
import com.it.tujia.entity.Theme;
import com.makeramen.roundedimageview.RoundedImageView;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * Created by kkguo on 2015/11/15.
 */
public class DisplayHotel extends LinearLayout{
    public DisplayHotel(Context context, Hotel hotel) {
        super(context);
        init(context, null,hotel);
    }

    public DisplayHotel(Context context, AttributeSet attrs) {
        super(context, attrs);
       // init(context, attrs,null);
    }
    ImageLoader mImageLoader;
    private Button title;
    DisplayImageOptions options;
    private void init(Context context, AttributeSet attrs,Hotel hotel) {
        if(hotel==null){
            return;
        }
        mImageLoader = App.getApp().getImagerLoader();
        options = App.getApp().getOptions();
        LayoutInflater inflater = LayoutInflater.from(context);
        inflater.inflate(R.layout.display_hotel, this);

        if(hotel.isHasPromotion()){
             TextView promotion = (TextView) findViewById(R.id.tv_display_hotel_promotion);
            promotion.setVisibility(VISIBLE);
        }
        RoundedImageView imageView = (RoundedImageView) findViewById(R.id.iv_frag_home_display_hotel);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
         TextView title = (TextView) findViewById(R.id.tv_title_display_hotel);
        TextView scores = (TextView) findViewById(R.id.tv_display_hotel_scores);
        TextView comment = (TextView) findViewById(R.id.tv_display_hotel_comment);
        TextView price = (TextView) findViewById(R.id.tv_display_hotel_price);
        mImageLoader.displayImage(hotel.getPic(), imageView, options);
        title.setText(hotel.getUnitName());
         int score = hotel.getCommentScore();
        if(score==0){
            scores.setVisibility(GONE);
        }
        scores.setText(score+"分");
       int commentCount= hotel.getCommentCount();
        if(commentCount==0){
            comment.setVisibility(GONE);
        }
        comment.setText(commentCount+"点评");
        price.setText("￥"+hotel.getDisplayPrice());
        //RoundedImageView
    }

}
