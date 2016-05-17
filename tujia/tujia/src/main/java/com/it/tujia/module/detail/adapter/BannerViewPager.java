package com.it.tujia.module.detail.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.it.tujia.application.App;
import com.it.tujia.entity.HotelDetal;
import com.it.tujia.module.detail.entity.TuJiaDetail;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * Created by Administrator on 2015/11/20.
 */
public class BannerViewPager extends PagerAdapter{

    private Context mContext;
    private List<TuJiaDetail.ContentEntity.UnitDetailEntity.PictureListEntity> mPictureList;
    private ImageLoader imageLoader;
    private DisplayImageOptions options;

    public BannerViewPager(Context mContext, List<TuJiaDetail.ContentEntity.UnitDetailEntity.PictureListEntity> mPictureList) {
        this.mContext = mContext;
        this.mPictureList = mPictureList;

        options = App.getApp().getOptions();
    }

    @Override
    public int getCount() {
        return mPictureList == null ? 0 : mPictureList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView view = new ImageView(mContext);

        view.setScaleType(ImageView.ScaleType.FIT_XY);

        imageLoader = App.getApp().getImagerLoader();

        imageLoader.displayImage(mPictureList.get(position).getUrl(),view,options);

        container.addView(view);

        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

//    @Override
//    public CharSequence getPageTitle(int position) {
//        return super.getPageTitle(position);
//    }
}
