package com.it.tujia.module.feature.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.it.tujia.R;
import com.it.tujia.application.App;
import com.it.tujia.module.feature.entity.FeatureHotUnits;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * Created by JunShen
 */
public class FeatureHotUnitsPagerAdapter extends PagerAdapter {

    private Context mContext;
    private List<FeatureHotUnits> hotUnitsList;


    private ImageLoader imageLoader;
    private DisplayImageOptions options;

    public FeatureHotUnitsPagerAdapter(Context mContext, List<FeatureHotUnits> hotUnitsList) {
        this.mContext = mContext;
        this.hotUnitsList = hotUnitsList;


        imageLoader = App.getApp().getImagerLoader();
        options = App.getApp().getOptions();
    }

    @Override
    public int getCount() {
        return hotUnitsList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        ViewHolder  viewHolder = new ViewHolder();

        View  view = LayoutInflater.from(mContext).inflate(R.layout.frag_feature_unit_banner, container, false);

        ViewUtils.inject(viewHolder, view);


        FeatureHotUnits hotUnits = hotUnitsList.get(position);

        viewHolder.hot_unitName.setText(hotUnits.getUnitName());

        viewHolder.hot_unit_defaultPicture.setScaleType(ImageView.ScaleType.FIT_XY);

        imageLoader.displayImage(hotUnits.getDefaultPicture(), viewHolder.hot_unit_defaultPicture, options);

        container.addView(view);

        return view;

    }

    private static class ViewHolder {

        @ViewInject(R.id.iv_feature_hot_unit_defaultPicture)
        private ImageView hot_unit_defaultPicture;

        @ViewInject(R.id.tv_feature_hot_unitName)
        private TextView hot_unitName;
    }
}
