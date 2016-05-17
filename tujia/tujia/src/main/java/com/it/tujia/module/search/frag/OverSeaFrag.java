package com.it.tujia.module.search.frag;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.it.tujia.R;
import com.it.tujia.constant.ConstantType;
import com.it.tujia.db.DbCity;
import com.it.tujia.module.search.acitivity.AreaActivity;
import com.it.tujia.module.search.acitivity.CityActivity;
import com.it.tujia.view.SearchItem;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;

import de.greenrobot.event.EventBus;

/**
 * Created by kkguo on 2015/11/17.
 */
public class OverSeaFrag extends Fragment{

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_oversea, container, false);
        ViewUtils.inject(this, view);
        return view;
    }
    @ViewInject(R.id.si_frag_search)
    private SearchItem mCitySearch;
    @ViewInject(R.id.iv_frag_search_plus)
    private ImageView plus;
    @ViewInject(R.id.tv_frag_search_people)
    TextView countText;
    int num=0;
    @OnClick({R.id.iv_frag_search_add,R.id.iv_frag_search_plus,R.id.si_frag_search,R.id.location_frag_search})
    public  void  disCount(View view){
        switch (view.getId()){
            case R.id.iv_frag_search_add:
                num++;
                countText.setText(num+"人");
                plus.setImageResource(R.drawable.ic_search_plus_en);
                break;
            case R.id.iv_frag_search_plus:
                if(num>0){
                    num--;
                    countText.setText(num+"人");
                }else if (num==0){
                    plus.setImageResource(R.drawable.ic_search_plus_un);
                    countText.setText("不限");
                }
                break;
            case R.id.si_frag_search:
                Intent intentCity=new Intent(getActivity(),CityActivity.class);
                intentCity.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intentCity.putExtra("searchType", ConstantType.OVERSEA_CITY);
                getActivity().startActivity(intentCity);
                break;
            case R.id.location_frag_search:
                Intent intentArea=new Intent(getActivity(),AreaActivity.class);
                intentArea.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intentArea.putExtra("searchType", ConstantType.OVERSEA_AREA);
                getActivity().startActivity(intentArea);
                break;

        }
    }

    public void onEvent(DbCity event) {

        if (event.getSearchType() == ConstantType.MESTIC_CITY) {
            mCitySearch.setCity(event.getCityName());
        }else if (event.getSearchType() == ConstantType.MESTIC_AREA){

        }else {
            Log.e("MesticFrag", "(event.getSearchType() == ConstantType.MESTIC)");

        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);
    }
}
