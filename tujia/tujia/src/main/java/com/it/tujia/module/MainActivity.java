package com.it.tujia.module;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.it.tujia.R;
import com.it.tujia.constant.ConstantType;
import com.it.tujia.constant.URL;
import com.it.tujia.db.MyDbUtils;
import com.it.tujia.entity.City;
import com.it.tujia.entity.InIsland;
import com.it.tujia.impl.DataCallBackListner;
import com.it.tujia.module.feature.frag.FeatureMainFragment;
import com.it.tujia.module.find.frag.DiscoverFragment;
import com.it.tujia.module.homepage.frag.HomeFragment;
import com.it.tujia.module.mine.frag.MineFragment;
import com.it.tujia.module.search.frag.SearchFragment;
import com.it.tujia.utils.JsonDataUtils;
import com.it.tujia.utils.JsonUtils;
import com.it.tujia.utils.NetUtils;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;

import java.util.List;

import de.greenrobot.event.EventBus;


@ContentView(R.layout.activity_main)
public class MainActivity extends FragmentActivity implements RadioGroup.OnCheckedChangeListener {
    @ViewInject(R.id.rg_home_activity)
    private RadioGroup mRadioGroup;
    private FragmentManager manager;
    private int oldCheaked = 0;
    private NetUtils mNetUtils;
    private boolean isfirst = false;
    private SharedPreferences sharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewUtils.inject(this);
        EventBus.getDefault().register(this);
        mNetUtils = NetUtils.getInstance();

        RadioButton childAt = (RadioButton) mRadioGroup.getChildAt(0);
        childAt.setChecked(true);
        mRadioGroup.setOnCheckedChangeListener(this);
        manager = getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.fl_home_activity, new HomeFragment()).commit();

        new Thread(new Runnable() {
            @Override
            public void run() {
                sharedPreferences = getSharedPreferences("tujia_isfirstrun", Context.MODE_PRIVATE);
                isfirst = sharedPreferences.getBoolean("IsFirstRun",false);
                if (isfirst){
                    initCityData();
                    initOverSeaCityData();
                }
            }
        }).start();

    }

    public void onEvent(Integer searchType) {
        if (searchType == ConstantType.OVERSEA) {
            this.searchType = searchType;
            RadioButton childAt = (RadioButton) mRadioGroup.getChildAt(1);
            childAt.setChecked(true);
        } else if (searchType == ConstantType.MESTIC) {
            this.searchType = searchType;
            RadioButton childAt = (RadioButton) mRadioGroup.getChildAt(1);
            childAt.setChecked(true);
        }


    }

    private boolean cityFinish;

    private void initCityData() {
        //开始下载城市定位并且开始
        String cityData = JsonDataUtils.getHomePageData(URL.CITY_PARAMS_ID);
        mNetUtils.sentPost(getApplicationContext(), URL.URL, cityData, new DataCallBackListner() {
            @Override
            public void onSuccess(String response) {
                InIsland island = JsonUtils.getCityLists(response);
                //可以根据经纬度位置进行判断
//                Log.e("城市数据mainactivity", island.getList().size() + "");
                List<City> list = island.getList();
                MyDbUtils myDbUtils = MyDbUtils.getInstance();
                myDbUtils.insertCityData(list, ConstantType.MESTIC);
//                EventBus.getDefault().post(island);

                //数据库操作
//                cityFinish = DbAsyncTask.newInstance(list, ConstantType.MESTIC);
            }
            @Override
            public void onFailure(String error) {

            }
        });
    }

    private void initOverSeaCityData() {
        String cityData = JsonDataUtils.getHomePageData(URL.OVERSEARS_CITY_PARAMS_ID);
        mNetUtils.sentPost(getApplicationContext(), URL.URL, cityData, new DataCallBackListner() {
            @Override
            public void onSuccess(String response) {
                InIsland island = JsonUtils.getCityLists(response);
                //可以根据经纬度位置进行判断,也可以根据
//                Log.e("城市数据mainactivity", island.getList().size() + "");
                List<City> list = island.getList();
               MyDbUtils myDbUtils = MyDbUtils.getInstance();
                myDbUtils.insertCityData(list, ConstantType.OVERSEA);
//               EventBus.getDefault().post(island);

                //数据库操作
//                cityFinish = DbAsyncTask.newInstance(list,ConstantType.OVERSEA);

                EventBus.getDefault().post(ConstantType.INSERT_CITY_FINISH);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean("IsFirstRun", true);
                editor.commit();
            }

            @Override
            public void onFailure(String error) {

            }
        });
    }

    int searchType = ConstantType.MESTIC;

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        // RadioButton oldChild = (RadioButton) rg_home_tab.getChildAt(oldCheaked);
        switch (checkedId) {
            case R.id.rb_homepage:
                manager.beginTransaction().replace(R.id.fl_home_activity, new HomeFragment()).commit();
                break;
            case R.id.rb_search:
                manager.beginTransaction().replace(R.id.fl_home_activity, SearchFragment.getInstance(searchType)).commit();
                break;
            case R.id.rb_feature:
                manager.beginTransaction().replace(R.id.fl_home_activity, new FeatureMainFragment()).commit();
                break;
            case R.id.rb_find:
                manager.beginTransaction().replace(R.id.fl_home_activity, DiscoverFragment.getFragment()).commit();
                break;
            case R.id.rb_mine:
                manager.beginTransaction().replace(R.id.fl_home_activity, new MineFragment()).commit();
                break;
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        /*try {
            App.getApp().getDbUtils().dropTable(DbCity.class);
        } catch (DbException e) {
            e.printStackTrace();
        }*/
    }
}
