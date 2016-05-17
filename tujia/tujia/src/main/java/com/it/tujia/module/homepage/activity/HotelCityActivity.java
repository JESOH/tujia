package com.it.tujia.module.homepage.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.it.tujia.R;
import com.it.tujia.application.App;
import com.it.tujia.constant.ConstantType;
import com.it.tujia.constant.URL;
import com.it.tujia.entity.AllConditions;
import com.it.tujia.entity.CityHotelItems;
import com.it.tujia.entity.CityTheme;
import com.it.tujia.entity.SubGroups;
import com.it.tujia.impl.DataCallBackListner;
import com.it.tujia.module.homepage.adapter.CityHotelAdapter;
import com.it.tujia.utils.JsonDataUtils;
import com.it.tujia.utils.JsonUtils;
import com.it.tujia.utils.NetUtils;

import java.util.ArrayList;
import java.util.List;

import de.greenrobot.event.EventBus;

public class HotelCityActivity extends Activity implements View.OnClickListener{
    private NetUtils mNetUtils;
    private Gson mGson;
    private RadioGroup radioGroup;
    private RelativeLayout relativeLayout;
    private ListView listView;
    private  NetUtils netUtils;
    private TextView title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel);
        mGson=App.getApp().getGson();
         Intent intent = getIntent();
        id = intent.getIntExtra("id", 23);
        EventBus.getDefault().register(this);
        initView();
        initdata();
    }
    CityHotelAdapter adapter;
    private void initView() {
        radioGroup = (RadioGroup) findViewById(R.id.rg_city_details);
        relativeLayout = (RelativeLayout) findViewById(R.id.search_city_detail);
        listView = (ListView) findViewById(R.id.lv_city_hotel_detail);
        netUtils = NetUtils.getInstance();
        title = (TextView) findViewById(R.id.tv_title_city_hotel);
        adapter = new CityHotelAdapter(this,cityHotels);
        listView.setAdapter(adapter);
    }

    boolean downContinue = true;
    boolean upContinue=false;
    int visibleItem = 0;
    private void initdata() {
        downloadMsg();
        downloadSelect();
      //正在下滑
        setAnim();
        initListener();

    }
    List<SubGroups> selectList = new ArrayList<>();
    List<SubGroups> locationList = new ArrayList<>();
    List<CityHotelItems> priceList = new ArrayList<>();
    List<CityHotelItems> sortList = new ArrayList<>();
    private void downloadSelect() {
         String selcetData = JsonDataUtils.getCitySelcetData(id);
        netUtils.sentPost(this, URL.URL, selcetData, new DataCallBackListner() {
            @Override
            public void onSuccess(String response) {

                List<AllConditions> select = JsonUtils.getSelect(response);
                for (int i = 0; i < select.size(); i++) {
                    int type = select.get(i).getgType();
                    List<SubGroups> list = select.get(i).getList();
                    switch (type) {
                        case ConstantType.CITY_HOTEL_SELECT :

                            selectList.addAll(list);
                            break;
                        case ConstantType.CITY_HOTEL_LOCATION :

                            locationList.addAll(list);
                            break;
                        case ConstantType.CITY_HOTEL_PRICE :
                             List<CityHotelItems> items = select.get(i).getItems();
                            priceList.addAll(items);
                            break;
                        case ConstantType.CITY_HOTEL_SORT :
                            List<CityHotelItems> item = select.get(i).getItems();
                           sortList.addAll(item);
                            break;
                    }

                }

            }

            @Override
            public void onFailure(String error) {

            }
        });
    }

    RadioButton select;
    RadioButton location;
    RadioButton price;
    RadioButton sort;
    private void initListener() {
        select = (RadioButton) findViewById(R.id.select_city_hotel);
        select.setOnClickListener(this);
        location = (RadioButton) findViewById(R.id.location_city_hotel);
       location.setOnClickListener(this);
        price = (RadioButton) findViewById(R.id.price_city_hotel);
        price.setOnClickListener(this);
        sort = (RadioButton) findViewById(R.id.sort_city_hotel);
        sort.setOnClickListener(this);
    }


    private void setAnim() {
        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if (downContinue) {
                    if (firstVisibleItem > visibleItem) {
                        downContinue = false;

                        Animation transalte = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.city_transalte);
                        Animation transalteTitle = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.city_transalte_title);
                        //调用ImageView的启动动画的方法：把动画对象传入
                        radioGroup.startAnimation(transalte);
                        relativeLayout.startAnimation(transalteTitle);
                        relativeLayout.setVisibility(View.GONE);
                        radioGroup.setVisibility(View.GONE);
                    }
                } else if (firstVisibleItem < visibleItem) { //向上滑动给
                    downContinue = true;
                    Animation transalte = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.city_transalte_back);
                    Animation transalteTitle = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.city_transalte_back_title);
                    radioGroup.setVisibility(View.VISIBLE);
                    relativeLayout.setVisibility(View.VISIBLE);
                    radioGroup.startAnimation(transalte);
                    relativeLayout.startAnimation(transalteTitle);
                }

                visibleItem = firstVisibleItem; //向下滑动
            }
        });
    }
    int id=23;
    List<CityTheme.Content.ListEn> cityHotels=new ArrayList<>();
    private void downloadMsg() {
         String cityHotelData = JsonDataUtils.getCityHotelData(id);
        netUtils.sentPost(getApplicationContext(), URL.URL, cityHotelData, new DataCallBackListner() {
            @Override
            public void onSuccess(String response) {
                Log.e("response",response);

                String msg = response;
                CityTheme json = mGson.fromJson(response, CityTheme.class);
                 CityTheme.Content content = json.getContent();
                title.setText(content.getDestName());//设置城市名字
                 List<CityTheme.Content.ListEn> hotels = content.getList();

                EventBus.getDefault().post(response );

                cityHotels.addAll(hotels);
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onFailure(String error) {

            }
        });
    }




    @Override
    public void onClick(View v) {
        Intent intent=new Intent();
        AllConditions condition=new AllConditions();

        switch (v.getId()){
            case R.id.select_city_hotel:
                intent.setClass(this, SelectHotelActivity.class);
                condition.setList(selectList);
                condition.setgType(ConstantType.CITY_HOTEL_SELECT);
               intent.putExtra("conditions", condition);

                break;
            case R.id.location_city_hotel:
                intent.setClass(this, SelectHotelActivity.class);
                condition.setList(locationList);
                condition.setgType(ConstantType.CITY_HOTEL_LOCATION);
                intent.putExtra("conditions",condition);
                break;
            case R.id.price_city_hotel:
                intent.setClass(this, PriceHotelActivity.class);
                condition.setItems(priceList);
                condition.setgType(ConstantType.CITY_HOTEL_PRICE);
                intent.putExtra("conditions",condition);
                break;
            case R.id.sort_city_hotel:
                intent.setClass(this, SortDailogActivity.class);
                condition.setItems(sortList);
                condition.setgType(ConstantType.CITY_HOTEL_SORT);
                intent.putExtra("conditions",condition);
                break;
        }
        startActivity(intent);
    }

    public  void onEvent( List<CityHotelItems> selectResutl ){
        for (int i=0;i<selectResutl.size();i++){
             boolean selected = selectResutl.get(i).isIsSelected();
            if(selected){
                //加入参数
            }
        }
        //得到一个最终的参数最后进行，网络下载新的数据显示
    }

    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();

    }
}
