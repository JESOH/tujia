package com.it.tujia.module.find.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.it.tujia.R;
import com.it.tujia.application.App;
import com.it.tujia.module.find.entity.ThemeDetail;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.nostra13.universalimageloader.core.DisplayImageOptions;

import java.util.List;

/**
 * Created by Administrator on 2015/11/18.
 */
public class DiscoverThemeGridViewAdapter extends BaseAdapter {

    private Context mContext;
    List<ThemeDetail.ContentEntity.ElementsEntity.UnitListEntity> mUnitList;
    private LayoutInflater mInflater;
    private final DisplayImageOptions options;


    public DiscoverThemeGridViewAdapter(Context mContext, List<ThemeDetail.ContentEntity.ElementsEntity.UnitListEntity> mUnitList) {
        this.mContext = mContext;
        this.mUnitList = mUnitList;
        mInflater = LayoutInflater.from(mContext);
        options = App.getApp().getOptions();
    }

    @Override
    public int getCount() {
        return mUnitList == null ? 0 : mUnitList.size();
    }

    @Override
    public ThemeDetail.ContentEntity.ElementsEntity.UnitListEntity getItem(int position) {
        return mUnitList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder mViewHolder = null;
        if(convertView == null){
            convertView = mInflater.inflate(R.layout.activity_discover_detail_element_layout3,null);

            mViewHolder = new ViewHolder();
            ViewUtils.inject(mViewHolder,convertView);
            convertView.setTag(mViewHolder);
        }else{
            mViewHolder = (ViewHolder) convertView.getTag();
        }

        ThemeDetail.ContentEntity.ElementsEntity.UnitListEntity unitItem = getItem(position);

        mViewHolder.tvUnitName.setText(unitItem.getUnitName());
        mViewHolder.tvSummary.setText("￥" + (int) unitItem.getFinalPrice());
        mViewHolder.tvSummary.setTextColor(0xffff9900);

        App.getApp().getImagerLoader().displayImage(
                unitItem.getPictureURL(),
                mViewHolder.ivImage,
                options
        );
        return convertView;
    }

    class ViewHolder{
        @ViewInject(R.id.iv_theme_layout3_img)
        private ImageView ivImage;
        @ViewInject(R.id.tv_theme_layout3_unitName)
        private TextView tvUnitName;
        @ViewInject(R.id.tv_theme_layout3_summary)
        private TextView tvSummary;
    }
}
