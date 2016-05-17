package com.it.tujia.module.detail.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.it.tujia.R;
import com.it.tujia.application.App;
import com.it.tujia.module.detail.activity.TuJiaDetailActivity;
import com.it.tujia.module.detail.entity.TuJiaDetail;
import com.it.tujia.module.find.entity.StrategyDetail;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * Created by Administrator on 2015/11/20.
 */
public class TuJiaDetailAdapter extends BaseAdapter{

    private Context mContext;
    private List<TuJiaDetail.ContentEntity.UnitDetailEntity.SimilarUnitsEntity> mSimilarList;
    private LayoutInflater mInflater;
    private final DisplayImageOptions options;
    private final ImageLoader imageLoader;

    public TuJiaDetailAdapter(Context mContext, List<TuJiaDetail.ContentEntity.UnitDetailEntity.SimilarUnitsEntity> mSimilarList) {
        this.mContext = mContext;
        this.mSimilarList = mSimilarList;
        mInflater = LayoutInflater.from(mContext);
        imageLoader = App.getApp().getImagerLoader();
        options = App.getApp().getOptions();
    }

    @Override
    public int getCount() {
        Log.e("getCount()",mSimilarList.size()+"");
        return mSimilarList.size();
    }

    @Override
    public TuJiaDetail.ContentEntity.UnitDetailEntity.SimilarUnitsEntity getItem(int position) {
        return mSimilarList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder mViewHolder = null;
        if(convertView == null){
            convertView = mInflater.inflate(R.layout.detail_list_item_layout,parent,false);

            mViewHolder = new ViewHolder();

            ViewUtils.inject(mViewHolder,convertView);

            convertView.setTag(mViewHolder);
        }else{
            mViewHolder = (ViewHolder) convertView.getTag();
        }

        TuJiaDetail.ContentEntity.UnitDetailEntity.SimilarUnitsEntity itemSimilar = getItem(position);

        mViewHolder.tvUnitName.setText(itemSimilar.getUnitName());

        mViewHolder.tvRoomCountSummary.setText(
                itemSimilar.getRoomCountSummary()+"  "+
                "宜住"+itemSimilar.getRecommendedGuests()+"人");

        mViewHolder.tvDisplayPrice.setText("￥"+itemSimilar.getDisplayPrice());

        mViewHolder.tvDistance.setText("距离"+itemSimilar.getDistance()+"米");

        mViewHolder.position = position;

        imageLoader.displayImage(
                itemSimilar.getDefaultPictureURL(),
                mViewHolder.ivPicture,
                options
        );

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ViewHolder holder = (ViewHolder) v.getTag();
                TuJiaDetail.ContentEntity.UnitDetailEntity.SimilarUnitsEntity item = getItem(holder.position);

                Intent intent = new Intent();
                intent.setClass(mContext,TuJiaDetailActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("unitID",item.getUnitID());

                mContext.startActivity(intent);

            }
        });

        return convertView;
    }

    class ViewHolder{
        @ViewInject(R.id.iv_detail_item_defaultPictureURL)
        private ImageView ivPicture;
        @ViewInject(R.id.tv_detail_item_unitName)
        private TextView tvUnitName;
        @ViewInject(R.id.tv_detail_item_roomCountSummary)
        private TextView tvRoomCountSummary;
        @ViewInject(R.id.tv_detail_item_displayPrice)
        private TextView tvDisplayPrice;
        @ViewInject(R.id.tv_detail_item_distance)
        private TextView tvDistance;

        private int position;
    }
}
