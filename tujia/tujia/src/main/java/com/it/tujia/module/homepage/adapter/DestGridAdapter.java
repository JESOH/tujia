package com.it.tujia.module.homepage.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.it.tujia.R;
import com.it.tujia.db.DbCity;

import java.util.List;

/**
 * Created by kkguo on 2015/11/19.
 */
public class DestGridAdapter extends BaseAdapter{
    private Context mContext;
    private List<DbCity> inChina;
    public DestGridAdapter(Context applicationContext, List<DbCity> inChina) {
        this.mContext=applicationContext;
        this.inChina=inChina;
    }

    @Override
    public int getCount() {
        return 8;
    }

    @Override
    public Object getItem(int position) {
        return inChina.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if(convertView==null){
            viewHolder=new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.array_item, parent, false);
              viewHolder.textView = (TextView) convertView.findViewById(R.id.tv_array_item);
            convertView.setTag(viewHolder);
        }
        viewHolder= (ViewHolder) convertView.getTag();
        viewHolder.textView.setText(inChina.get(position).getCityName());
        return convertView;
    }
    class ViewHolder {
        TextView textView;
    }
}
