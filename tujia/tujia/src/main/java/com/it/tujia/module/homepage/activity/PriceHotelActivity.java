package com.it.tujia.module.homepage.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.it.tujia.R;
import com.it.tujia.constant.ConstantType;
import com.it.tujia.entity.AllConditions;
import com.it.tujia.entity.CityHotelItems;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;

@ContentView(R.layout.activity_price_hotel)
public class PriceHotelActivity extends Activity {
    @ViewInject(R.id.rg_price_city_hotel)
    LinearLayout mRadioGroup;
    private List<CityHotelItems> list=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewUtils.inject(this);
        Intent intent = getIntent();
        AllConditions conditions = (AllConditions) intent.getSerializableExtra("conditions");
        int type = conditions.getgType();
        switch (type) {
            case ConstantType.CITY_HOTEL_PRICE :
                list.addAll(conditions.getItems());
                break;
        }
        initview();
    }

    private void initview() {
        RadioButton button=null;
        LinearLayout.LayoutParams params=new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT,1);

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
