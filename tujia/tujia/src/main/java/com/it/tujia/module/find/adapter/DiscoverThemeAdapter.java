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
import com.it.tujia.module.find.activity.DiscoverThemeDetailActivity;
import com.it.tujia.module.find.entity.Theme;
import com.nostra13.universalimageloader.core.DisplayImageOptions;

import java.util.ArrayList;
import java.util.List;

public class DiscoverThemeAdapter extends BaseAdapter {

    private List<Theme> mDiscoverList = new ArrayList<>();
    private Context mContext;
    private LayoutInflater layoutInflater;

    public DiscoverThemeAdapter(Context mContext, List<Theme> mDiscoverList) {
        this.mContext = mContext;
        this.mDiscoverList = mDiscoverList;
        this.layoutInflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return mDiscoverList.size();
    }

    @Override
    public Theme getItem(int position) {
        return mDiscoverList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.frag_discover_gridview_list_item, null);

            viewHolder = new ViewHolder();

            viewHolder.tvtitle  = (TextView) convertView.findViewById(R.id.tv_gridview_title);
            viewHolder.ivDiscoverGw = (ImageView) convertView.findViewById(R.id.iv_discover_gw);
            viewHolder.tvDiscoverGw = (TextView) convertView.findViewById(R.id.tv_discover_gw);

            convertView.setTag(viewHolder);
        }

        viewHolder = (ViewHolder) convertView.getTag();

        Theme theme = mDiscoverList.get(position);

        final Theme.DetailListEntity detailList = theme.getDetailList();

        //Log.e("detailList...",content.toString());

        //获取position位置

        int index = -1;

        for (int i = 0; i < mDiscoverList.size(); i++) {
            if(mDiscoverList.get(i).getName().equals(theme.getName())){
                index = i;
                break;
            }
        }

        int isFirst = 0;

        if(index == position){
            viewHolder.tvtitle.setVisibility(View.VISIBLE);//第一次显示
            viewHolder.tvtitle.setText((theme.getName()));
            isFirst++;
        }else{
            if(isFirst == 1){
                viewHolder.tvtitle.setText("     ----    ");
            }else {
                viewHolder.tvtitle.setVisibility(View.GONE);//隐藏
                isFirst = 0;
            }
        }

        viewHolder.tvDiscoverGw.setText(detailList.getName());

        DisplayImageOptions options = App.getApp().getOptions();

        App.getApp().getImagerLoader().displayImage(detailList.getSmallPictureURL(),viewHolder.ivDiscoverGw,options);

        viewHolder.position = position;

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ViewHolder holder = (ViewHolder)v.getTag();
                Theme themeEntity = getItem(holder.position);

                Intent intent = new Intent();
                intent.setClass(mContext, DiscoverThemeDetailActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                intent.putExtra("id", themeEntity.getDetailList().getId());

                mContext.startActivity(intent);
            }
        });

        return convertView;
    }

    protected class ViewHolder {
        private ImageView ivDiscoverGw;
        private TextView tvDiscoverGw;
        private TextView tvtitle;

        private int position;
    }
}
