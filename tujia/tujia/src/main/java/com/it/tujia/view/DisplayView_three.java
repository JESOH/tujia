package com.it.tujia.view;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.it.tujia.R;
import com.it.tujia.application.App;
import com.it.tujia.entity.Hotel;
import com.it.tujia.entity.Theme;
import com.it.tujia.module.detail.activity.TuJiaDetailActivity;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * Created by kkguo on 2015/11/15.
 */
public class DisplayView_three extends LinearLayout{
    public DisplayView_three(Context context, Theme theme) {
        super(context);
        init(context, null,theme);
    }

    public DisplayView_three(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs,null);
    }
    ImageLoader mImageLoader;
    private Button title;
    DisplayImageOptions options;
    LinearLayout mLL;
    private void init(Context context, AttributeSet attrs,Theme theme) {
        mImageLoader = App.getApp().getImagerLoader();
        options = App.getApp().getOptions();
        LayoutInflater inflater = LayoutInflater.from(context);
         View view = inflater.inflate(R.layout.display_two, this);
        title = (Button) findViewById(R.id.btn_title_display);
        mLL = (LinearLayout) findViewById(R.id.ll_display_two);
        //RoundedImageView
        setRes(context,theme);
    }



    public  void setRes(final Context context,Theme theme){
        List<Hotel> list = theme.getList();
        title.setText(theme.getGroupName());
        for (int i=0;i<list.size();i++){//展示自定义的控件
             final Hotel hotel = list.get(i);
            DisplayHotel hotelView=new DisplayHotel(context,hotel);
            hotelView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(context, TuJiaDetailActivity.class);
                    intent.putExtra("unitID",hotel.getItem().getUnitID());
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                }
            });
            mLL.addView(hotelView);
        }
    }
}
