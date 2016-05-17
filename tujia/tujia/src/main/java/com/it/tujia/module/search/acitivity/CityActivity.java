package com.it.tujia.module.search.acitivity;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.it.tujia.R;
import com.it.tujia.constant.ConstantType;
import com.it.tujia.db.DbCity;
import com.it.tujia.db.MyDbUtils;
import com.it.tujia.module.search.adapter.CitySearchAdapter;
import com.it.tujia.view.SlideBar;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;

import java.util.ArrayList;
import java.util.List;

@ContentView(R.layout.activity_city)
public class CityActivity extends Activity implements SlideBar.OnSlideBarItemClickListener,TextWatcher{

    @ViewInject(R.id.sb_city)
    private SlideBar mSlidieBar;
    @ViewInject(R.id.tv_centre_city)
    private TextView centreChar;

    @ViewInject(R.id.lv_city_show)
    ListView mListView;
    List<DbCity> list=new ArrayList<>();
    @ViewInject(R.id.et_search_city)
    EditText mEtCity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_city);
        ViewUtils.inject(this);
         int searchType = getIntent().getIntExtra("searchType", ConstantType.MESTIC_CITY);
        switch (searchType){
            case ConstantType.MESTIC_CITY:
                List<DbCity> cities = MyDbUtils.getInstance().queryAllCityData(ConstantType.MESTIC);
                list.addAll(cities);
                break;
            case ConstantType.OVERSEA_CITY:
                List<DbCity> overSeas = MyDbUtils.getInstance().queryAllCityData(ConstantType.OVERSEA);
                list.addAll(overSeas);
                break;
        }
        mEtCity.addTextChangedListener(this);
        //EventBus.getDefault().register(this);
        mSlidieBar.setOnSlideBarItemClickListener(this);
        initView();
    }

    CitySearchAdapter adapter;
    private void initView() {
        adapter = new CitySearchAdapter(list,this);
        mListView.setAdapter(adapter);
    }

    @OnClick(R.id.press_back)
    public void pressBack(View view){
        this.finish();
    }

    @Override
    public void onItemClicked(String word) {
            centreChar.setVisibility(View.VISIBLE);
            centreChar.setText(word);
         int wordsPosition = adapter.getWordsPosition(word);
        Log.e("wordsPositon", wordsPosition + "" + word);
        mListView.setSelection(wordsPosition);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
       // EventBus.getDefault().unregister(this);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
       switch (event.getAction()){
           case  MotionEvent.ACTION_UP:
               centreChar.setVisibility(View.GONE);
               break;
       }
        return true;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
         String s1 = s.toString();
         List<DbCity> cities = MyDbUtils.getInstance().querySearch(ConstantType.MESTIC, s1);
        Log.e("条件查询",cities.size()+"");
        list.clear();
        list.addAll(cities);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}
