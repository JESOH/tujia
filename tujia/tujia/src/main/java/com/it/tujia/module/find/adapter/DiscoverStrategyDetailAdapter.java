package com.it.tujia.module.find.adapter;

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
import com.it.tujia.module.find.constant.NodeType;
import com.it.tujia.module.find.entity.StrategyDetail;
import com.it.tujia.module.find.entity.Theme;
import com.it.tujia.module.homepage.activity.HotelCityActivity;
import com.it.tujia.view.TuJiaDiscoverDetailGridView;
import com.nostra13.universalimageloader.core.DisplayImageOptions;

import java.util.List;

/**
 * Created by Administrator on 2015/11/17.
 */
public class DiscoverStrategyDetailAdapter extends BaseAdapter{

    private final int MAX_TYPE = 4;
    private Context mContext;
    private List<StrategyDetail.ContentEntity> mContentList;
    private LayoutInflater mInflater;
    private DisplayImageOptions options;
    private List<StrategyDetail.ContentEntity.ElementsEntity.UnitListEntity> mUnitsList;

    public DiscoverStrategyDetailAdapter(
            Context mContext,
            List<StrategyDetail.ContentEntity> mContentList,
            List<StrategyDetail.ContentEntity.ElementsEntity.UnitListEntity> mUnitsList) {
        this.mContext = mContext;
        this.mContentList = mContentList;
        this.mUnitsList = mUnitsList;
        mInflater = LayoutInflater.from(mContext);
        options = App.getApp().getOptions();
    }

    @Override
    public int getCount() {
        Log.e("getCount()...",mContentList.size()+"");
        return mContentList == null ? 0 : mContentList.size();
    }

    @Override
    public StrategyDetail.ContentEntity getItem(int position) {
        return mContentList == null ? null : mContentList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {

        switch (mContentList.get(position).getElement().getNodeType()){
            case NodeType.NODETYPE1:
                return NodeType.NODETYPE1;
            case NodeType.NODETYPE2:
                return NodeType.NODETYPE2;
            case NodeType.NODETYPE3:
                return NodeType.NODETYPE3;
            default:
                return NodeType.NODETYPE0;
        }
    }

    @Override
    public int getViewTypeCount() {
        return MAX_TYPE;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        INodeTypeOneViewHolder oneViewHolder = null;
        INodeTypeTwoViewHolder twoViewHolder = null;
        INodeTypeThreeViewHolder threeViewHolder = null;

        final StrategyDetail.ContentEntity mContentEntity = mContentList.get(position);
        //获取判断的布局类型
//        int nodeType = mContentEntity.getElement().getNodeType();
        int nodeType=getItemViewType(position);
        if(convertView == null){
            switch (nodeType){
                case NodeType.NODETYPE1:
                    convertView = mInflater.inflate(R.layout.activity_discover_detail_element_layout1,null);
                    oneViewHolder = new INodeTypeOneViewHolder();
                    oneViewHolder.tvOneName = (TextView) convertView.findViewById(R.id.tv_theme_layout1_name);
                    convertView.setTag(oneViewHolder);
                    break;
                case NodeType.NODETYPE2:
                    convertView = mInflater.inflate(R.layout.activity_discover_detail_element_layout2,null);
                    twoViewHolder = new INodeTypeTwoViewHolder();
                    twoViewHolder.ivTwoImage = (ImageView) convertView.findViewById(R.id.iv_theme_layout2_img);
                    twoViewHolder.tvTwoName = (TextView) convertView.findViewById(R.id.tv_theme_layout2_name);
                    twoViewHolder.tvTwoIntroduction = (TextView) convertView.findViewById(R.id.tv_theme_layout2_introduction);
                    convertView.setTag(twoViewHolder);
                    break;
                case NodeType.NODETYPE3:
                    convertView = mInflater.inflate(R.layout.activity_discover_detail_element_layout4,null);
                    threeViewHolder = new INodeTypeThreeViewHolder();

                    threeViewHolder.tvRecommended = (TextView) convertView.findViewById(R.id.tv_theme_recommended);
                    threeViewHolder.tvSearchMore = (TextView) convertView.findViewById(R.id.tv_theme_home_search_more);
                    threeViewHolder.mGridView = (TuJiaDiscoverDetailGridView) convertView.findViewById(R.id.gv_theme_gridview);

                    convertView.setTag(threeViewHolder);
                    break;
            }
        }else{
            switch (nodeType){
                case NodeType.NODETYPE1:
                    oneViewHolder = (INodeTypeOneViewHolder) convertView.getTag();
                    break;
                case NodeType.NODETYPE2:
                    twoViewHolder = (INodeTypeTwoViewHolder) convertView.getTag();
                    break;
                case NodeType.NODETYPE3:
                    threeViewHolder = (INodeTypeThreeViewHolder) convertView.getTag();
                    break;
            }
        }

        switch (nodeType){
            case NodeType.NODETYPE1:
                oneViewHolder.tvOneName.setText(mContentEntity.getElement().getName());
                break;
            case NodeType.NODETYPE2:
                twoViewHolder.tvTwoName.setText(mContentEntity.getElement().getName());
                twoViewHolder.tvTwoIntroduction.setText(mContentEntity.getElement().getIntroduction());

                App.getApp().getImagerLoader().displayImage(
                        mContentEntity.getElement().getPictureUrl(),
                        twoViewHolder.ivTwoImage,
                        options);
                break;
            case NodeType.NODETYPE3:
                threeViewHolder.position = position;
                threeViewHolder.tvRecommended.setText(mContentEntity.getElement().getName());
                threeViewHolder.tvSearchMore.setText(mContentEntity.getElement().getNavigation().getName());

                threeViewHolder.tvSearchMore.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

//                        INodeTypeThreeViewHolder holder = (INodeTypeThreeViewHolder)v.getTag();
//                        StrategyDetail.ContentEntity item = getItem(holder.position);

                        Intent intent = new Intent();
                        intent.setClass(mContext, HotelCityActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent.putExtra("id",mContentEntity.getElement().getNavigation().getCityId());

                        mContext.startActivity(intent);
                    }
                });

                if(threeViewHolder.mGridView != null){

                    DiscoverStrategyGridViewAdapter mGridViewAdapter = new DiscoverStrategyGridViewAdapter(mContext,mUnitsList,null);
                    threeViewHolder.mGridView.setAdapter(mGridViewAdapter);
                }
                break;
        }

        return convertView;
    }

    class INodeTypeOneViewHolder{
        private TextView tvOneName;
    }

    class INodeTypeTwoViewHolder{
        private ImageView ivTwoImage;
        private TextView tvTwoName;
        private TextView tvTwoIntroduction;
    }

    class INodeTypeThreeViewHolder{
        private TextView tvRecommended;
        private TextView tvSearchMore;
        private TuJiaDiscoverDetailGridView mGridView;
        private int position;
    }
}
