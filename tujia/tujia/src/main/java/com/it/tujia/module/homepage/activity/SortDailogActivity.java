package com.it.tujia.module.homepage.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.it.tujia.R;
import com.it.tujia.constant.ConstantType;
import com.it.tujia.constant.URL;
import com.it.tujia.entity.AllConditions;
import com.it.tujia.entity.CityHotelItems;
import com.it.tujia.entity.SubGroups;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;

@ContentView(R.layout.activity_sort_dailog)
public class SortDailogActivity extends Activity {
    private List<CityHotelItems> list=new ArrayList<>();
    @ViewInject(R.id.rg_srot_city_hotel)
    RadioGroup mRadioGroup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewUtils.inject(this);
        Intent intent = getIntent();
        AllConditions conditions = (AllConditions) intent.getSerializableExtra("conditions");
        int type = conditions.getgType();
        switch (type) {
            case ConstantType.CITY_HOTEL_SORT :
                list.addAll(conditions.getItems());
                break;
        }
        initview();
    }

    private void initview() {
        RadioButton button=null;
        ViewGroup.LayoutParams params=new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 50);
        for (int i=0;i<list.size();i++){
            button=new RadioButton(this);
            button.setBackgroundColor(Color.WHITE);
            button.setGravity(Gravity.CENTER);
            button.setTextColor(Color.BLACK);
            button.setLayoutParams(params);
            button.setButtonDrawable(getResources().getDrawable(android.R.color.transparent));
            button.setText(list.get(i).getLabel());
            mRadioGroup.addView(button);
        }

    }
}
