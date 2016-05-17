package com.it.tujia.module.search.frag;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.it.tujia.R;
import com.it.tujia.constant.URL;
import com.it.tujia.impl.DataCallBackListner;
import com.it.tujia.module.search.entity.LocationOrArea;
import com.it.tujia.utils.JsonDataUtils;
import com.it.tujia.utils.NetUtils;

import java.util.List;

/**
 * Created by Administrator on 2015/11/17.
 */
// 发布者
public class ELeftFragment extends Fragment {
    private ListView leftLv;
    private List<LocationOrArea.Content.SubGroups.Items> areaList;
    private List<LocationOrArea.Content.SubGroups.Items> areaItemList;
    private int index;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_search_area_list_left, container, false);
        leftLv = (ListView) view.findViewById(R.id.left_lv);

        index = getActivity().getIntent().getIntExtra("area_label", 0);

        ELeftAdapter leftAdapter = new ELeftAdapter(areaItemList,getContext());

        leftLv.setAdapter(leftAdapter);

        leftLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

//                EventBus.getDefault().post(areaItemList.get(position));
//                EventBus.getDefault().post(new LeftEvent());
//                EventBus.getDefault().post(new RightEvent());
            }
        });
        return view;
    }

    private NetUtils netUtils;

    public void loadData() {
        netUtils = NetUtils.getInstance();
        netUtils.sentPost(getActivity().getApplicationContext(), URL.URL, JsonDataUtils.getLocationAreaData(),
                new DataCallBackListner() {
                    @Override
                    public void onSuccess(String response) {

                        Gson gson = new Gson();
                        LocationOrArea locationOrArea = gson.fromJson(response, LocationOrArea.class);

                        areaList = (List<LocationOrArea.Content.SubGroups.Items>) locationOrArea.getContent().getSubGroups().get(index).getSubGroups();

                        Log.e("areaList", areaList.get(index).getLabel());

                        areaItemList = locationOrArea.getContent().getSubGroups().get(index).getItems();

                        Log.e("areaItemList", areaItemList.get(index).getLabel());
//                        Log.e("AreaActivity", locationOrArea.getContent().getLabel());
                    }

                    @Override
                    public void onFailure(String error) {

                    }
                });
    }

    private class ELeftAdapter extends BaseAdapter {
        List<LocationOrArea.Content.SubGroups.Items> list;
        Context mContext;

        public ELeftAdapter(List<LocationOrArea.Content.SubGroups.Items> list, Context mContext) {
            this.list = list;
            this.mContext = mContext;
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public LocationOrArea.Content.SubGroups.Items getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }


        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder;
            if (convertView == null) {
                viewHolder = new ViewHolder();
                convertView = LayoutInflater.from(mContext).inflate(R.layout.activity_location_area_item, parent, false);
                viewHolder.subgroup = (TextView) convertView.findViewById(R.id.tv_area_subgroup);

                convertView.setTag(viewHolder);
            }
            viewHolder = (ViewHolder) convertView.getTag();

            LocationOrArea.Content.SubGroups.Items area = getItem(position);

            viewHolder.subgroup.setText(list.get(position).getLabel());

            return convertView;
        }

        private class ViewHolder {
            public TextView subgroup;
        }
    }

}
