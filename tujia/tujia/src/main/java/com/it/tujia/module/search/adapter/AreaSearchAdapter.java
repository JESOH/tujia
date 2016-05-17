package com.it.tujia.module.search.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.it.tujia.R;
import com.it.tujia.module.search.entity.LocationOrArea;

import java.util.List;

import de.greenrobot.event.EventBus;

/**
 * Created by kkguo on 2015/11/18.
 */
public class AreaSearchAdapter extends BaseAdapter {
    List<LocationOrArea.Content.SubGroups> list;
    Context mContext;

    public AreaSearchAdapter(List<LocationOrArea.Content.SubGroups> list, Context mContext) {
        this.list = list;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public LocationOrArea.Content.SubGroups getItem(int position) {
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

            convertView = LayoutInflater.from(mContext).inflate(R.layout.activity_location_area_item, parent, false);
            viewHolder.subgroup = (TextView) convertView.findViewById(R.id.tv_area_subgroup);

            convertView.setTag(viewHolder);
        }
          viewHolder = (ViewHolder) convertView.getTag();

        LocationOrArea.Content.SubGroups area =  getItem(position);

        viewHolder.subgroup.setText(list.get(position).getLabel());

        viewHolder.area =list.get(position);

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //结束这个activity，传回给fragment
                 ViewHolder tag = (ViewHolder) v.getTag();
                 EventBus.getDefault().post(tag.area);

            }
        });
        return convertView;
    }

    public int getWordsPosition(String words)
    {
        for (int i = 0; i < list.size(); i++)
        {

            if (words.equalsIgnoreCase(list.get(i).getLabel()))
            {
                return i;
            }
        }
        return -1;
    }


    class ViewHolder {
        public TextView subgroup;
        public LocationOrArea.Content.SubGroups area;
    }
}
