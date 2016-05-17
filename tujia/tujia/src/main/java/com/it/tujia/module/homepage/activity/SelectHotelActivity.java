package com.it.tujia.module.homepage.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.it.tujia.R;
import com.it.tujia.constant.ConstantType;
import com.it.tujia.entity.AllConditions;
import com.it.tujia.entity.CityHotelItems;
import com.it.tujia.entity.SubGroups;
import com.it.tujia.module.homepage.adapter.SelectHotleAdapter;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;

import java.util.ArrayList;
import java.util.List;

import de.greenrobot.event.EventBus;


@ContentView(R.layout.activity_select_hotel)
public class SelectHotelActivity extends Activity implements RadioGroup.OnCheckedChangeListener{
    private List<SubGroups>list=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewUtils.inject(this);
         Intent intent = getIntent();
         AllConditions conditions = (AllConditions) intent.getSerializableExtra("conditions");
        int type = conditions.getgType();
        switch (type) {
            case ConstantType.CITY_HOTEL_SELECT :
                list.addAll(conditions.getList());
                break;
            case ConstantType.CITY_HOTEL_LOCATION :
                list.addAll(conditions.getList());
                break;

    }
    initView();
}
    @ViewInject(R.id.rg_select_city_detal)
    private RadioGroup mRadioGroup;
    @ViewInject(R.id.lv_select_city_hotel)
    private ListView mListView;
    private void initView() {
        ViewGroup.LayoutParams params=new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 60);
        RadioButton radioButton;
        for (int i=0;i<list.size();i++){
             String label = list.get(i).getLabel(); //名字label
             radioButton=new RadioButton(getApplicationContext());
            radioButton.setText(label);
            radioButton.setTextColor(Color.BLACK);
            radioButton.setBackgroundResource(R.drawable.rb_select_city_hotel);
            radioButton.setLayoutParams(params);
            radioButton.setGravity(Gravity.CENTER);
            radioButton.setId(i);
            radioButton.setButtonDrawable(getResources().getDrawable(android.R.color.transparent));
            mRadioGroup.addView(radioButton);
        }
        mRadioGroup.setOnCheckedChangeListener(this);

        adapter = new SelectHotleAdapter(getApplicationContext(),itemsList);
        mListView.setAdapter(adapter);
         RadioButton childAt = (RadioButton) mRadioGroup.getChildAt(0);
        childAt.setChecked(true);
    }
    SelectHotleAdapter adapter;
    List<CityHotelItems> itemsList=new ArrayList<>();
    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


    @OnClick({R.id.press_back,R.id.click_city_hotel})
    public void clicked(View view){
        switch (view.getId()){
            case R.id.press_back:
                 List<CityHotelItems> selectResutl = adapter.getSelectResutl();
                EventBus.getDefault().post(selectResutl);
                this.finish();
                break;
            case R.id.click_city_hotel:
                //收集信息
                this.finish();
                break;
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        Log.e("checkedid",checkedId+"");
         List<CityHotelItems> lists = this.list.get(checkedId).getList();
        itemsList.clear();
        itemsList.addAll(lists);
        adapter.notifyDataSetChanged();
    }
}
