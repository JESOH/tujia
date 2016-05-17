package com.it.tujia.view;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
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

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kkguo on 2015/11/15.
 */
public class DisplayView_one extends LinearLayout {
    public DisplayView_one(Context context, Theme theme) {
        super(context);
        init(context, null, theme);
    }

    public DisplayView_one(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, null);
    }

    ImageLoader mImageLoader;
    private Button title;
    DisplayImageOptions options;
    private List<LinearLayout> columns;
    LinearLayout.LayoutParams params;
    private void init(Context context, AttributeSet attrs, Theme theme) {
        mImageLoader = App.getApp().getImagerLoader();
        options = App.getApp().getOptions();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.display_one, this);
        title = (Button) findViewById(R.id.btn_title_display);
        LinearLayout llparents = (LinearLayout) findViewById(R.id.ll_display_two);
        LinearLayout.LayoutParams params1 =
                new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1);
        params = new LayoutParams( LayoutParams.MATCH_PARENT,180);
        params.setMargins(5,0,5,5);
        columns = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            LinearLayout layout1 = new LinearLayout(context);
            layout1.setOrientation(LinearLayout.VERTICAL);
            layout1.setLayoutParams(params1);
            // 给外出的水平的线性布局添加控件
            llparents.addView(layout1);
            columns.add(layout1);
        }
      setRes(context,theme);
        //RoundedImageView
    }


    public void setRes(final Context context,Theme theme) {
        List<Hotel> list = theme.getList();
        title.setText(theme.getGroupName());
        Log.e("11111", theme.getGroupName());

        for (int i = 0; i < list.size(); i++) {
            RoundedImageView imageView = new RoundedImageView(context);
            imageView.setLayoutParams(params);
            imageView.setCornerRadius(5);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
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
            columns.get(i%2).addView(imageView);
        }
    }

}
