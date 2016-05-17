package com.it.tujia.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.it.tujia.R;
import com.it.tujia.application.App;
import com.it.tujia.entity.HotCity;
import com.makeramen.roundedimageview.RoundedImageView;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

/**
 * Created by kkguo on 2015/11/16.
 */
public class DisplayHotCity extends LinearLayout {

    public DisplayHotCity(Context context,HotCity hotCity) {
        super(context);
        init(context,null,hotCity);
    }



    public DisplayHotCity(Context context, AttributeSet attrs,HotCity hotCity) {
        super(context, attrs);
        init(context, attrs,hotCity);
    }
    RoundedImageView cityPhoto;
    TextView cityName;
    ImageLoader mImageLoader;
    DisplayImageOptions options;
    private void init(Context context, AttributeSet attrs,HotCity hotCity) {
        mImageLoader = App.getApp().getImagerLoader();
        options = App.getApp().getOptions();
        LayoutInflater.from(context).inflate(R.layout.display_hotcity, this);
        cityPhoto = (RoundedImageView) findViewById(R.id.iv_display_hotcity);
        cityName = (TextView) findViewById(R.id.tv_display_hotcity);
        setRes(hotCity.getPic(),hotCity.getName());
    }

    private void setRes(String url,String cityname){
        mImageLoader.displayImage(url,cityPhoto,options);
        cityName.setText(cityname);
    }
}
