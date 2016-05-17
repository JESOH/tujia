package com.it.tujia.module.homepage.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.it.tujia.R;
import com.it.tujia.entity.HotCity;
import com.it.tujia.module.MainActivity;
import com.it.tujia.module.find.activity.DiscoverThemeDetailActivity;
import com.it.tujia.module.homepage.activity.HotelCityActivity;
import com.it.tujia.view.DisplayHotCity;

import java.util.List;

/**
 * Created by kkguo on 2015/11/16.
 */
public class HotCityAdapter extends PagerAdapter{
    private Context mContext;
    private List<HotCity>hotCities;
    public HotCityAdapter(Context context, List<HotCity> hotCities) {
        this.mContext=context;
        this.hotCities=hotCities;
    }

    @Override
    public int getCount() {
        int count=0;
        if(hotCities.size()>0){
           count= hotCities.size()/8+1;
        }
        return count;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.frag_home_hotcity_item, container, false);
        LinearLayout layout= (LinearLayout) view.findViewById(R.id.ll_hotcity_item);
        int currentItem=0;
        if(hotCities.size()/8==position){
           currentItem =hotCities.size()-8*(position);  //12-8=4
            Log.e("currentItem","第二次进入"+currentItem);
        }else {
            currentItem=8;
        }
        DisplayMetrics metrics = new DisplayMetrics();
         MainActivity activity = (MainActivity) mContext;
        LinearLayout.LayoutParams params=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        activity.getWindowManager().getDefaultDisplay().getMetrics(metrics);
        params.setMargins(10,10,10,0);
        // 动态设置ViewPager高度
        params.width = metrics.widthPixels / 8;
        DisplayHotCity hotCity=null;

        for (int i=0;i<currentItem;i++){
            int item=(position)*8+i;
            hotCity=new DisplayHotCity(this.mContext,hotCities.get(item));
            hotCity.setTag(hotCities.get(item).getId());
            hotCity.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int id = (int) v.getTag();
                    Intent intent=new Intent();
                    intent.putExtra("id",id);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.setClass(mContext, HotelCityActivity.class);
                    mContext.startActivity(intent);
                }
            });
            hotCity.setLayoutParams(params);
            layout.addView(hotCity);
        }

        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
       container.removeView((View)object);
    }
}
