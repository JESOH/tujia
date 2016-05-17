package com.it.tujia.module.homepage.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.it.tujia.R;
import com.it.tujia.entity.CityHotelItems;

import java.util.List;

import de.greenrobot.event.EventBus;

/**
 * Created by kkguo on 2015/11/23.
 */
public class SelectHotleAdapter extends BaseAdapter{
    private Context mContext;
    private List<CityHotelItems>list;
    public SelectHotleAdapter(Context context, List<CityHotelItems> list) {
        this.mContext=context;
        this.list=list;

    }

    @Override
    public int getCount() {
        return list.size()>0?list.size():0;
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder=new ViewHolder();
        if(convertView==null){
            convertView= LayoutInflater.from(mContext).inflate(R.layout.check_item,parent,false);
         viewHolder.title= (TextView) convertView.findViewById(R.id.tv_title);
           viewHolder.selected= (ImageView) convertView.findViewById(R.id.iv_selected);
            convertView.setTag(viewHolder);
        }
        viewHolder = (ViewHolder) convertView.getTag();
        viewHolder.title.setText(list.get(position).getLabel());
        viewHolder.selected.setVisibility(View.GONE);
        viewHolder.position=position;
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 ViewHolder tag = (ViewHolder) v.getTag();

                if(tag.flag){
                    tag.selected.setVisibility(View.GONE);
                    list.get(tag.position).setIsSelected(false);
                    tag.flag=false;
                }else {
                    list.get(tag.position).setIsSelected(true);
                    tag.selected.setVisibility(View.VISIBLE);
                    tag.flag=true;
                }
            }
        });
        return convertView;
    }

    class ViewHolder{
        TextView title;
        ImageView selected;
        boolean flag;
        int position;
    }
    public List<CityHotelItems> getSelectResutl(){
        return  list;
    }

}
