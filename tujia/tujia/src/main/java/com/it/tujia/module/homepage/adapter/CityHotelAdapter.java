package com.it.tujia.module.homepage.adapter;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.it.tujia.R;
import com.it.tujia.entity.CityTheme;
import com.it.tujia.module.detail.activity.TuJiaDetailActivity;
import com.it.tujia.module.homepage.activity.HotelCityActivity;
import com.it.tujia.view.PopItem;

import java.util.List;

/**
 * Created by kkguo on 2015/11/23.
 */
public class CityHotelAdapter extends BaseAdapter{
    Activity activity;
    List<CityTheme.Content.ListEn> cityHotels;
    public CityHotelAdapter(HotelCityActivity hotelCityActivity, List<CityTheme.Content.ListEn> cityHotels) {
        this.activity=hotelCityActivity;
        this.cityHotels=cityHotels;
    }

    @Override
    public int getCount() {
        return cityHotels.size();
    }

    @Override
    public Object getItem(int position) {
        return cityHotels.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
            if(convertView==null){
                viewHolder=new ViewHolder();
                convertView=activity.getLayoutInflater().inflate(R.layout.city_hotel_item,parent,false);
                viewHolder.viewPager = (ViewPager) convertView.findViewById(R.id.vp_city_hotel);
                viewHolder.roomName = (TextView) convertView.findViewById(R.id.tv_title_display_hotel);
                viewHolder.promotion = (TextView) convertView.findViewById(R.id.tv_display_hotel_promotion);
                viewHolder.scores = (TextView) convertView.findViewById(R.id.tv_display_hotel_scores);
                viewHolder.roomCount = (TextView) convertView.findViewById(R.id.tv_hotel_city_roomcount);
                viewHolder. guest = (TextView) convertView.findViewById(R.id.tv_hotel_city_guest);
                viewHolder.price = (TextView) convertView.findViewById(R.id.tv_display_hotel_price);
                viewHolder.position=position;
                viewHolder.viewPager.setOffscreenPageLimit(1);
                convertView.setTag(viewHolder);
            }
         List<String> pictureList = cityHotels.get(position).getPictureList();
        viewHolder= (ViewHolder) convertView.getTag();
        BannerListAdapter listAdapter=new BannerListAdapter(activity,pictureList,cityHotels.get(position).getUnitID());
        viewHolder.viewPager.setAdapter(listAdapter);
        viewHolder.roomName.setText(cityHotels.get(position).getUnitName());
        if(cityHotels.get(position).isHasPromotion()){
            viewHolder.promotion.setVisibility(View.VISIBLE);
        }
        String scores=cityHotels.get(position).getCommentScore()+"分";
        viewHolder.scores.setText(cityHotels.get(position).getCommentScore()+"分");
        String roomcount=cityHotels.get(position).getRoomCountSummary();
        viewHolder.roomCount.setText(cityHotels.get(position).getRoomCountSummary());
        int guest=cityHotels.get(position).getRecommendedGuests();
        viewHolder.guest.setText("宜住"+cityHotels.get(position).getRecommendedGuests()+"人");
        viewHolder.price.setText("￥"+cityHotels.get(position).getFinalPrice());
        viewHolder.position=position;;
        convertView.setOnClickListener(clickListener);
        return convertView;
    }

    View.OnClickListener clickListener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            ViewHolder tag = (ViewHolder)v.getTag();
            Log.e("viewpage","点击了");
            CityTheme.Content.ListEn listEn = cityHotels.get(tag.position);
            int unitID = listEn.getUnitID();
            Intent intent=new Intent();
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtra("unitID",unitID);
            intent.setClass(activity, TuJiaDetailActivity.class);
            activity.startActivity(intent);
        }
    };
    class ViewHolder{
        ViewPager viewPager;
        TextView roomName;
        TextView promotion;
        TextView scores;
        TextView roomCount;
        TextView guest;
        TextView price;
        int position;
    }
}
