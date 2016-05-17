package com.it.tujia.module.find.adapter;

import android.content.Context;
import android.media.Image;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.it.tujia.R;
import com.it.tujia.application.App;
import com.it.tujia.module.find.activity.DiscoverThemeDetailActivity;
import com.it.tujia.module.find.constant.NodeType;
import com.it.tujia.module.find.entity.StrategyDetail;
import com.it.tujia.module.find.entity.ThemeDetail;
import com.it.tujia.view.TuJiaDiscoverDetailGridView;
import com.nostra13.universalimageloader.core.DisplayImageOptions;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/11/18.
 */
public class DiscoverThemeDetailAdapter extends BaseAdapter{

    public static final int MAX_TYPE = 5;

    private Context mContext;
    private List<ThemeDetail.ContentEntity> mContentList;
//    private List<ThemeDetail.ContentEntity.ElementsEntity.UnitListEntity> mUnitList;
    private LayoutInflater mInflater;
    private final DisplayImageOptions options;


    public DiscoverThemeDetailAdapter(
            Context mContext,
            List<ThemeDetail.ContentEntity> mContentList,
            List<ThemeDetail.ContentEntity.ElementsEntity.UnitListEntity> mUnitList) {
        this.mContext = mContext;
        this.mContentList = mContentList;
//        this.mUnitList = mUnitList;
        mInflater = LayoutInflater.from(mContext);
        options = App.getApp().getOptions();
    }

    @Override
    public int getCount() {
        Log.e("getCount()...", mContentList.size() + "");
        return mContentList == null ? 0 : mContentList.size();
    }

    @Override
    public ThemeDetail.ContentEntity getItem(int position) {
        return mContentList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        int displayType = mContentList.get(position).getElements().getDisplayType();

        switch (mContentList.get(position).getElements().getNodeType()){
            case NodeType.NODETYPE1:
                return NodeType.NODETYPE1;
            case NodeType.NODETYPE2:
                return NodeType.NODETYPE2;
            case NodeType.NODETYPE3:
                if(displayType == 1){
                    displayType = NodeType.NODETYPE3;
                    return NodeType.NODETYPE3;
                }if(displayType == 2){
                    displayType = NodeType.NODETYPE4;
                    return NodeType.NODETYPE4;
                }
            default:
                return displayType;
                //return NodeType.NODETYPE0;
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
        INodeTypeFourViewHolder fourViewHolder = null;

        Log.e("mContentList..",mContentList.toString());
        Log.e("getElements()",mContentList.get(position).getElements().toString());

//        int displayType = mContentEntity.getElements().getDisplayType();

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
                case NodeType.NODETYPE4:
                    convertView = mInflater.inflate(R.layout.activity_discover_detail_element_layout3,null);
                    fourViewHolder = new INodeTypeFourViewHolder();
                    fourViewHolder.ivImage = (ImageView) convertView.findViewById(R.id.iv_theme_layout3_img);
                    fourViewHolder.ivCollect = (ImageView) convertView.findViewById(R.id.iv_theme_layout3_collect);
                    fourViewHolder.tvUnitName = (TextView) convertView.findViewById(R.id.tv_theme_layout3_unitName);
                    fourViewHolder.tvSummary = (TextView) convertView.findViewById(R.id.tv_theme_layout3_summary);
                    convertView.setTag(fourViewHolder);

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
                case NodeType.NODETYPE4:
                    fourViewHolder = (INodeTypeFourViewHolder) convertView.getTag();
                    break;
            }
        }

        ThemeDetail.ContentEntity mContentEntity = mContentList.get(position);//????为什么数据只有最后一种
        switch (nodeType){

            case NodeType.NODETYPE1:
                oneViewHolder.tvOneName.setText(mContentEntity.getElements().getName());
                break;
            case NodeType.NODETYPE2:

                if(!mContentEntity.getElements().getName().equals("null")){
                    twoViewHolder.tvTwoName.setText(mContentEntity.getElements().getName());
                }else{
                    twoViewHolder.tvTwoName.setVisibility(View.GONE);
                }

                if(mContentEntity.getElements().getIntroduction() != null){
                    twoViewHolder.tvTwoIntroduction.setText(mContentEntity.getElements().getIntroduction());
                }else{
                    twoViewHolder.tvTwoIntroduction.setVisibility(View.GONE);
                }

                App.getApp().getImagerLoader().displayImage(
                        mContentEntity.getElements().getPictureUrl(),
                        twoViewHolder.ivTwoImage,
                        options);
                break;
            case NodeType.NODETYPE3:


                Log.e("mContentList..",mContentList.toString());
                Log.e("getElements()",mContentList.get(position).getElements().toString());
                Log.e("getUnitList()",mContentList.get(position).getElements().getUnitList().toString());
                Log.e("getUnitList().get(0)",mContentList.get(position).getElements().getUnitList().get(0).toString());
                Log.e("getThemeUnits()",mContentList.get(position).getElements().getThemeUnits().toString());


                int size1 = mContentEntity.getElements().getUnitList().size();//获取Unitlist对象的个数

                //ThemeDetail.ContentEntity.ElementsEntity mElementsEntity = mContentEntity.getElements();

                List<ThemeDetail.ContentEntity.ElementsEntity.UnitListEntity> unitList = new ArrayList<>();

                Log.e("unitSize", size1 + "   "+unitList.size());

                /******************************************************************************/

                for (int i = 0; i < mContentEntity.getElements().getUnitList().size(); i++) {

                    ThemeDetail.ContentEntity.ElementsEntity.UnitListEntity unitListEntity = mContentEntity.getElements().getUnitList().get(i);

                    unitList.add(unitListEntity);

                    threeViewHolder.tvRecommended.setText(mContentEntity.getElements().getName());
                    threeViewHolder.tvSearchMore.setText(mContentEntity.getElements().getNavigation().getName());

                }

                Log.e("unitSize2", size1 + "   "+unitList.size());

                Log.e("unitList",unitList.toString());

                if(threeViewHolder.mGridView != null){

                    DiscoverThemeGridViewAdapter mGridViewAdapter = new DiscoverThemeGridViewAdapter(mContext,unitList);
                    threeViewHolder.mGridView.setAdapter(mGridViewAdapter);
                }

                break;
            case NodeType.NODETYPE4:

                int size = mContentEntity.getElements().getUnitList().size();//获取Unitlist对象的个数
                Log.e("size...", size + "");

                //ThemeDetail.ContentEntity.ElementsEntity.UnitListEntity unitListEntity = mContentEntity.getElements().getUnitList().get(position);

                //Log.e("unitListEntity..",unitListEntity.toString());

                for (int i = 0; i < size; i++) {
                    ThemeDetail.ContentEntity.ElementsEntity.UnitListEntity unitListEntity = mContentEntity.getElements().getUnitList().get(i);
                    if(unitListEntity.getUnitName() != null || !unitListEntity.getUnitName().equals("")){
                        fourViewHolder.tvUnitName.setText(unitListEntity.getUnitName());
                    }
//                    threeViewHolder.tvUnitName.setText(mContentEntity.getElements().getThemeUnits().get(position).getUnitName());
                    fourViewHolder.tvSummary.setText((String) unitListEntity.getSummary());
                    fourViewHolder.ivCollect.setVisibility(View.VISIBLE);

                    App.getApp().getImagerLoader().displayImage(
                            unitListEntity.getPictureURL(),
                            fourViewHolder.ivImage,
                            options
                    );
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

    }

    class INodeTypeFourViewHolder{
        private ImageView ivImage;
        private ImageView ivCollect;
        private TextView tvUnitName;
        private TextView tvSummary;
    }
}
