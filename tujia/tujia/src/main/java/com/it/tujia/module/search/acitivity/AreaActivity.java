package com.it.tujia.module.search.acitivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.it.tujia.R;
import com.it.tujia.constant.ConstantType;
import com.it.tujia.constant.URL;
import com.it.tujia.impl.DataCallBackListner;
import com.it.tujia.module.search.adapter.AreaSearchAdapter;
import com.it.tujia.module.search.entity.LocationOrArea;
import com.it.tujia.utils.JsonDataUtils;
import com.it.tujia.utils.NetUtils;
import com.it.tujia.view.SlideBar;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/11/23.
 */
@ContentView(R.layout.activity_location_area)
public class AreaActivity extends Activity implements SlideBar.OnSlideBarItemClickListener, TextWatcher, OnClickListener {

    @ViewInject(R.id.tv_search_area_unlimited)
    private TextView unlimited;

    @ViewInject(R.id.tv_select_city_hotel)
    private TextView searchTitle;

    @ViewInject(R.id.rl_search_area)
    private RelativeLayout rlSearchArea;

    @ViewInject(R.id.tv_search_cancel)
    private TextView tvSearchCancel;

    @ViewInject(R.id.lv_area_show)
    private ListView mListView;

    @ViewInject(R.id.et_search_area)
    private EditText mEtArea;

    private List<LocationOrArea.Content.SubGroups> areaList = new ArrayList<>();

    private NetUtils netUtils;

    private AreaSearchAdapter adapter;

    private int searchId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewUtils.inject(this);

        searchTitle.setText("位置区域");
        unlimited.setVisibility(View.VISIBLE);


        int searchType = getIntent().getIntExtra("searchType", ConstantType.MESTIC_AREA);

        switch (searchType) {
            case ConstantType.MESTIC_AREA:

                searchId = 1;
                loadData();
                Log.e("AreaActivity", "ConstantType.MESTIC_AREA:" + ConstantType.MESTIC_AREA);
                break;
            case ConstantType.OVERSEA_AREA:

                searchId = 1;
                loadData();
                Log.e("AreaActivity", "ConstantType.OVERSEA_AREA:" + ConstantType.OVERSEA_AREA);
                break;
        }

        initView();
    }

    private void initView() {
        adapter = new AreaSearchAdapter(areaList, getApplicationContext());
        mListView.setAdapter(adapter);
        mEtArea.addTextChangedListener(this);
        tvSearchCancel.setOnClickListener(this);
    }


    public void loadData() {
        netUtils = NetUtils.getInstance();
        netUtils.sentPost(getApplicationContext(), URL.URL, JsonDataUtils.getLocationAreaData(),
                new DataCallBackListner() {
                    @Override
                    public void onSuccess(String response) {

                        Gson gson = new Gson();
                        LocationOrArea locationOrArea = gson.fromJson(response, LocationOrArea.class);

                        areaList.addAll(locationOrArea.getContent().getSubGroups());

//                        Log.e("AreaActivity", locationOrArea.getContent().getSubGroups().get(0).getLabel());
                    }

                    @Override
                    public void onFailure(String error) {

                    }
                });
    }

    @OnClick(R.id.tv_search_area_unlimited)
    private void unLimited(View view) {

    }


    @OnClick(R.id.press_back)
    public void pressBack(View view) {
        this.finish();
    }

    private boolean isVisibilityFlag;
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (isVisibilityFlag) {
            RelativeLayout.LayoutParams params =
                    new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
            rlSearchArea.setLayoutParams(params);
            tvSearchCancel.setVisibility(View.GONE);
        }
    }

    @Override
    public void onItemClicked(String word) {

        int wordsPosition = adapter.getWordsPosition(word);
//        mListView.setSelection(wordsPosition);
        areaList.get(wordsPosition).getLabel();
        Intent intent = new Intent();
        intent.putExtra("area_label",wordsPosition);
        intent.setClass(getApplicationContext(), AreaContentActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // EventBus.getDefault().unregister(this);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_UP:

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
        for (int i = 0 ; i <areaList.size();i++){
            if (s1.equals(areaList.get(i).getLabel())){

            }
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    public void afterTextChanged(Editable s) {
        if (mEtArea.getText() != null){
            RelativeLayout.LayoutParams mParams =
                    new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
            rlSearchArea.setLayoutParams(mParams);

            tvSearchCancel.setVisibility(View.VISIBLE);
            isVisibilityFlag = true;
        }else {
            RelativeLayout.LayoutParams params =
                    new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
            rlSearchArea.setLayoutParams(params);
            tvSearchCancel.setVisibility(View.GONE);
            isVisibilityFlag = false;
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_search_cancel:
                RelativeLayout.LayoutParams params =
                        new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
                rlSearchArea.setLayoutParams(params);
                tvSearchCancel.setVisibility(View.GONE);
                isVisibilityFlag = true;
                break;
        }
    }
}
