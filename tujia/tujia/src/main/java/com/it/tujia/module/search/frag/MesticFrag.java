package com.it.tujia.module.search.frag;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.it.tujia.R;
import com.it.tujia.constant.ConstantType;
import com.it.tujia.db.DbCity;
import com.it.tujia.entity.InIsland;
import com.it.tujia.module.search.acitivity.CityActivity;
import com.it.tujia.module.search.acitivity.DateSelect;
import com.it.tujia.module.search.acitivity.TimeSquare;
import com.it.tujia.view.SearchDate;
import com.it.tujia.module.search.acitivity.AreaActivity;
import com.it.tujia.view.SearchItem;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import java.text.SimpleDateFormat;
import java.util.Date;

import de.greenrobot.event.EventBus;

/**
 * Created by kkguo on 2015/11/17.
 */
public class MesticFrag extends Fragment {
    @ViewInject(R.id.si_frag_search)
    private SearchItem mCitySearch;

    @ViewInject(R.id.location_frag_search)
    private SearchItem mAreaSearch;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_mestic, container, false);
        ViewUtils.inject(this, view);
        return view;
    }
    @ViewInject(R.id.search_date)
    SearchDate mCalendar;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    InIsland inIsland;

    public void onEvent(DbCity event) {
        if (event.getSearchType() == ConstantType.MESTIC_CITY) {
            mCitySearch.setCity(event.getCityName());
        }else if (event.getSearchType() == ConstantType.MESTIC_AREA){

        }else {
            Log.e("MesticFrag", "(event.getSearchType() == ConstantType.MESTIC)");

        }

    };
    public void onEvent(DateSelect event) {
        Log.e("DateSelect","onevent");
        if(event.getSearchType()== ConstantType.MESTIC){
            Log.e("DateSelect","onevent");
             Date first = event.getFirst();
             Date last = event.getLast();
            Date third=new Date();
            if(first.getTime()>last.getTime()){
                third=first;
                first=last;
                last=third;
            }
            SimpleDateFormat dateFormat=new SimpleDateFormat("MM月dd日");
            SimpleDateFormat weekFormat=new SimpleDateFormat("E");
            mCalendar.setCheckInDate(dateFormat.format(first));
            mCalendar.setCheckOutDate(dateFormat.format(last));
            mCalendar.setCheckInWeek(weekFormat.format(first));
            mCalendar.setCheckOutWeek(weekFormat.format(last));
        }
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
            mCitySearch.setOnClickListener(onClickListener);
            mCalendar.setOnClickListener(onClickListener);
            mAreaSearch.setOnClickListener(onClickListener);
    }



    View.OnClickListener onClickListener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent=new Intent();
            switch (v.getId()){
                case R.id.search_date:
                    intent.setClass(getActivity(),TimeSquare.class);
                    break;
                case R.id.si_frag_search:
                    intent.setClass(getActivity(),CityActivity.class);
                    intent.putExtra("searchType", ConstantType.MESTIC_CITY);
                    break;
                case R.id.location_frag_search:
                    intent.setClass(getActivity(),AreaActivity.class);
                    intent.putExtra("searchType", ConstantType.OVERSEA_AREA);
                    break;
            }
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            getActivity().startActivity(intent);
        }
    };


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        if(isVisibleToUser){//可见开始加载信息

        }

    }
}
