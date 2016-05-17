package com.it.tujia.module.find.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.it.tujia.R;
import com.it.tujia.application.App;
import com.it.tujia.module.detail.activity.TuJiaDetailActivity;
import com.it.tujia.module.find.entity.StrategyDetail;
import com.it.tujia.module.find.entity.ThemeDetail;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.nostra13.universalimageloader.core.DisplayImageOptions;

import java.util.List;

/**
 * Created by Administrator on 2015/11/17.
 */
public class DiscoverStrategyGridViewAdapter extends BaseAdapter{

    private List<ThemeDetail.ContentEntity.ElementsEntity.UnitListEntity> mUnitList;
    private Context mContext;
    private List<StrategyDetail.ContentEntity.ElementsEntity.UnitListEntity> mUnitsList;
    private LayoutInflater mInflater;
    private final DisplayImageOptions options;

    public DiscoverStrategyGridViewAdapter(
            Context mContext,
            List<StrategyDetail.ContentEntity.ElementsEntity.UnitListEntity> mUnitsList,
            List<ThemeDetail.ContentEntity.ElementsEntity.UnitListEntity> mUnitList) {
        this.mContext = mContext;
        this.mUnitsList = mUnitsList;
        this.mUnitList = mUnitList;
        mInflater = LayoutInflater.from(mContext);
        options = App.getApp().getOptions();
    }

    @Override
    public int getCount() {
        return mUnitsList.size();
    }

    @Override
    public StrategyDetail.ContentEntity.ElementsEntity.UnitListEntity getItem(int position) {
        return mUnitsList.get(position);
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

        mViewHolder.tvUnitName.setText(mUnitsList.get(position).getUnitName());
        mViewHolder.tvSummary.setText("￥"+(int) mUnitsList.get(position).getFinalPrice());
        mViewHolder.tvSummary.setTextColor(0xffff9900);

        App.getApp().getImagerLoader().displayImage(
                mUnitsList.get(position).getPictureURL(),
                mViewHolder.ivImage,
                options
        );

        mViewHolder.position = position;
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ViewHolder mHolder = (ViewHolder) v.getTag();
                StrategyDetail.ContentEntity.ElementsEntity.UnitListEntity itemUnit =
                        getItem(mHolder.position);

                Intent intent = new Intent();
                intent.setClass(mContext, TuJiaDetailActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("unitID",itemUnit.getUnitID());

                mContext.startActivity(intent);
            }
        });

        return convertView;
    }

    class ViewHolder{
        @ViewInject(R.id.iv_theme_layout3_img)
        private ImageView ivImage;
        @ViewInject(R.id.tv_theme_layout3_unitName)
        private TextView tvUnitName;
        @ViewInject(R.id.tv_theme_layout3_summary)
        private TextView tvSummary;

        private int position;
    }
}
