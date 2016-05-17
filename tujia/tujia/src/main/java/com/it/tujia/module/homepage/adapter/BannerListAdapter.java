package com.it.tujia.module.homepage.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.it.tujia.application.App;
import com.it.tujia.constant.ConstantType;
import com.it.tujia.entity.HomeTitleVP;
import com.it.tujia.module.detail.activity.TuJiaDetailActivity;
import com.it.tujia.module.homepage.activity.BannerDetalActivity;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kkguo on 2015/11/15.
 */


import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;


import java.util.List;

/**
 * Created by jerry on 15/11/2.
 */
public class BannerListAdapter<T> extends PagerAdapter
{
    private List<T> mBanners;
    private Context mContext;
    private int unitID;


    public BannerListAdapter(Context mContext,List<T> mBanners)
    {

        this.mBanners = mBanners;
        this.mContext = mContext;
    }

    public BannerListAdapter(Context mContext,List<T> mBanners,int unitID)
    {
        this.unitID=unitID;
        this.mBanners = mBanners;
        this.mContext = mContext;
    }

    @Override
    public int getCount()
    {
        return mBanners.size();
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position)
    {
        ImageView imageView = null;
        // 首页
        if (mBanners.get(position) instanceof  HomeTitleVP)
        {
             HomeTitleVP bannerList
                    = (HomeTitleVP)mBanners.get(position);

            imageView = new ImageView(mContext);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            imageView.setTag(bannerList);

            App.getApp().getImagerLoader().displayImage(bannerList.getPic(),
                    imageView, App.getApp().getOptions());
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                     HomeTitleVP  tag = (HomeTitleVP) v.getTag();
                    Intent intent=new Intent(mContext,BannerDetalActivity.class);
                    Bundle bundle=new Bundle();
                    bundle.putSerializable("msg",tag);
                    bundle.putInt("displayType", ConstantType.DISPLAY_BANNER);
                    intent.putExtras(bundle);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    mContext.startActivity(intent);
                }
            });


        }else if(mBanners.get(position) instanceof  String){

            imageView = new ImageView(mContext);
            String url= (String) mBanners.get(position);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent();
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.putExtra("unitID",unitID);
                    intent.setClass(mContext, TuJiaDetailActivity.class);
                    mContext.startActivity(intent);
                }
            });
            App.getApp().getImagerLoader().displayImage(url,
                    imageView, App.getApp().getOptions());
        }
        // 详情页
       /* else if(mBanners.get(position) instanceof HotelDetail.ResponseDataEntity.HotelImage)
        {

        }*/

        container.addView(imageView);

        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object)
    {
        container.removeView((View) object);
    }

    @Override
    public boolean isViewFromObject(View view, Object object)
    {
        return view == object;
    }
}


