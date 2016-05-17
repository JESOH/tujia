package com.it.tujia.view;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.it.tujia.R;
import com.it.tujia.application.App;
import com.it.tujia.entity.Hotel;
import com.it.tujia.entity.Theme;
import com.it.tujia.module.find.activity.DiscoverThemeDetailActivity;
import com.makeramen.roundedimageview.RoundedImageView;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * Created by kkguo on 2015/11/15.
 */
public class DisplayView_two extends LinearLayout{
    public DisplayView_two(Context context, Theme theme) {
        super(context);
        init(context, null,theme);
    }

    public DisplayView_two(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs,null);
    }
    ImageLoader mImageLoader;
    private Button title;
    DisplayImageOptions options;
    LinearLayout mLinear;
    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,230);
    private void init(Context context, AttributeSet attrs,Theme theme) {
        mImageLoader = App.getApp().getImagerLoader();
        options = App.getApp().getOptions();
        LayoutInflater inflater = LayoutInflater.from(context);
         inflater.inflate(R.layout.display_two, this);
        title = (Button) findViewById(R.id.btn_title_display);

        mLinear = (LinearLayout) findViewById(R.id.ll_display_two);

        setRes(context, theme);



        //RoundedImageView
    }



    public  void setRes(final Context context,Theme theme){
        title.setText(theme.getGroupName());
        List<Hotel> list = theme.getList();
        for (int i=0;i<list.size();i++){
            RoundedImageView imageView=new RoundedImageView(context);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            imageView.setCornerRadius(5);
            params.setMargins(0,0,0,20);
            imageView.setLayoutParams(params);
            mImageLoader.displayImage(list.get(i).getPic(), imageView, options);
            imageView.setTag(list.get(i).getRefId());
            imageView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    int  tag = (int) v.getTag();
                    Intent intent=new Intent();
                    intent.putExtra("id",tag);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.setClass(context, DiscoverThemeDetailActivity.class);
                    context.startActivity(intent);
                }
            });
            mLinear.addView(imageView);

        }
    }

}
