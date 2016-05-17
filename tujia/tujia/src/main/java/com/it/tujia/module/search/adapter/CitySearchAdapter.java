package com.it.tujia.module.search.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.it.tujia.R;
import com.it.tujia.db.DbCity;
import com.it.tujia.entity.City;
import com.it.tujia.module.search.acitivity.CityActivity;

import java.util.List;

import de.greenrobot.event.EventBus;

/**
 * Created by kkguo on 2015/11/18.
 */
public class CitySearchAdapter extends BaseAdapter {
    List<DbCity> list;
    Context mContext;

    public CitySearchAdapter(List<DbCity> list, Context mContext) {
        this.list = list;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public DbCity getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.city_listview_item, parent, false);
            viewHolder.firstLetter = (TextView) convertView.findViewById(R.id.tv_first_letter);
            viewHolder.cityName = (TextView) convertView.findViewById(R.id.tv_city_item);
            convertView.setTag(viewHolder);
        }
          viewHolder = (ViewHolder) convertView.getTag();

        DbCity city =  getItem(position);
        String firstLetter = city.getFirstLetter();

        int hitIndex=-1;
        for (int i = 0; i < list.size(); i++)
        {

            if (firstLetter.equals(String.valueOf(list.get(i).getPinyin().charAt(0))))
            {
                hitIndex = i;
                break; //第一个首字母相等的，所有位置
            }
        }

        if(hitIndex==position){
            //位置相等就证明是第一个
            viewHolder.firstLetter.setText(firstLetter);
            viewHolder.firstLetter.setVisibility(View.VISIBLE);
        }else {
            viewHolder.firstLetter.setVisibility(View.GONE);
        }

        viewHolder.cityName.setText(list.get(position).getCityName());
        viewHolder.dbCity=list.get(position);
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //结束这个activity，传回给fragment
                 ViewHolder tag = (ViewHolder) v.getTag();
                 EventBus.getDefault().post(tag.dbCity);
                 CityActivity activity = (CityActivity) mContext;
                activity.finish();
            }
        });
        return convertView;
    }

    public int getWordsPosition(String words)
    {
        for (int i = 0; i < list.size(); i++)
        {

            if (words.equalsIgnoreCase(list.get(i).getFirstLetter()))
            {
                return i;
            }
        }
        return -1;
    }


    class ViewHolder {
        public TextView firstLetter;
        public TextView cityName;
        public  DbCity dbCity;
    }
}
