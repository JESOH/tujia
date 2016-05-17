package com.it.tujia.module.find.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.it.tujia.R;
import com.it.tujia.application.App;
import com.it.tujia.module.find.activity.DiscoverStrategyDetailActivity;
import com.it.tujia.module.find.entity.Strategy;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.nostra13.universalimageloader.core.DisplayImageOptions;

import java.util.List;

/**
 * Created by Administrator on 2015/11/15.
 */
public class DiscoverStrategyAdapter extends BaseAdapter{

    private Context mContext;
    private List<Strategy> mStrategyList;
    private LayoutInflater mInflater;

    public DiscoverStrategyAdapter(Context mContext, List<Strategy> mStrategyList) {
        this.mContext = mContext;
        this.mStrategyList = mStrategyList;
        mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return mStrategyList.size();
    }

    @Override
    public Object getItem(int position) {
        return mStrategyList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if(convertView == null){
            convertView = mInflater.inflate(R.layout.frag_discover_listview_list_item,parent,false);

            viewHolder = new ViewHolder();

            ViewUtils.inject(viewHolder,convertView);

            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }

        final Strategy mStrategy = (Strategy) getItem(position);//获取对应position的对象

        int index = -1;

        for (int i = 0; i < mStrategyList.size(); i++) {
            if(mStrategy.getName().equals(mStrategyList.get(position).getName())){
                index = i;
                break;
            }
        }

        if(index == position){
            viewHolder.tvDisCoverLv.setVisibility(View.VISIBLE);
            viewHolder.tvDisCoverLv.setText(mStrategy.getName());
        }else{
            viewHolder.tvDisCoverLv.setVisibility(View.GONE);
        }

        DisplayImageOptions options = App.getApp().getOptions();

        App.getApp().getImagerLoader().displayImage(
                mStrategy.getList().getLargePictureURL(),
                viewHolder.ivDisCoverLv,
                options);

        viewHolder.position = position;

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ViewHolder holder = (ViewHolder) v.getTag();
                Strategy strategyItem = (Strategy) getItem(holder.position);//获取定位

                Intent intent = new Intent();
                intent.setClass(mContext,DiscoverStrategyDetailActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("id",strategyItem.getList().getId());

                mContext.startActivity(intent);
            }
        });

        return convertView;
    }

    private class ViewHolder{
        @ViewInject(R.id.tv_discover_lv)
        private TextView tvDisCoverLv;
        @ViewInject(R.id.iv_discover_lv)
        private ImageView ivDisCoverLv;

        private int position;
    }
}
