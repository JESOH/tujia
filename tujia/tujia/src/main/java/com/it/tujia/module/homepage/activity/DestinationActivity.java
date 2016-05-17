package com.it.tujia.module.homepage.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.it.tujia.R;
import com.it.tujia.constant.ConstantType;
import com.it.tujia.db.DbCity;
import com.it.tujia.db.MyDbUtils;
import com.it.tujia.module.homepage.adapter.DestGridAdapter;
import com.it.tujia.module.search.acitivity.CityActivity;
import com.it.tujia.view.MyGridView;
import com.it.tujia.view.SearchItem;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;

@ContentView(R.layout.activity_destination)
public class DestinationActivity extends Activity implements View.OnClickListener{
    @ViewInject(R.id.si_dest_search_hot)
    private SearchItem hotInLand;
    @ViewInject(R.id.si_dest_search_oversea)
    private SearchItem hotOberSea;
    @ViewInject(R.id.gv_dest_search)
    private MyGridView mHotCity;
    @ViewInject(R.id.gv_dest_search_oversea)
    private MyGridView mCityOversea;
    private MyDbUtils mDbUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewUtils.inject(this);
        initdata();
        initview();
    }

    private void initview() {
        DestGridAdapter adapter=new DestGridAdapter(getApplicationContext(),inChina);
        mHotCity.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        DestGridAdapter overAdapter=new DestGridAdapter(getApplicationContext(),overSea);
        mCityOversea.setAdapter(overAdapter);
        overAdapter.notifyDataSetChanged();
        hotInLand.setOnClickListener(this);
        hotOberSea.setOnClickListener(this);
    }
    List<DbCity> inChina=new ArrayList<>();
    List<DbCity> overSea=new ArrayList<>();

    private void initdata() {
        mDbUtils=MyDbUtils.getInstance();
         List<DbCity> cities = mDbUtils.queryHotCity(ConstantType.MESTIC);
        inChina.addAll(cities);
         List<DbCity> overCitys = mDbUtils.queryHotCity(ConstantType.OVERSEA);
        overSea.addAll(overCitys);

    }


    @Override
    public void onClick(View v) {
        Intent intent=new Intent();
        switch (v.getId()){
            case R.id.si_dest_search_oversea:
                intent.setClass(this, CityActivity.class);
                intent.putExtra("searchType", ConstantType.OVERSEA);
                break;
            case R.id.si_dest_search_hot:
                intent.setClass(this, CityActivity.class);
                intent.putExtra("searchType", ConstantType.MESTIC);
                break;
            case R.id.si_dest_search_nearby:
                break;
        }
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}
