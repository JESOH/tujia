package com.it.tujia.module.find.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.it.tujia.R;
import com.it.tujia.application.App;
import com.it.tujia.constant.URL;
import com.it.tujia.impl.DataCallBackListner;
import com.it.tujia.module.find.adapter.DiscoverStrategyDetailAdapter;
import com.it.tujia.module.find.entity.Strategy;
import com.it.tujia.module.find.entity.StrategyDetail;
import com.it.tujia.utils.JsonDataUtils;
import com.it.tujia.utils.NetUtils;
import com.it.tujia.view.TuJiaDiscoverDetailListView;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.nostra13.universalimageloader.core.DisplayImageOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/11/15.
 */
@ContentView(R.layout.activity_discover_strategy_detail)
public class DiscoverStrategyDetailActivity extends Activity{

    @ViewInject(R.id.iv_theme_detail_img)
    private ImageView mImageView;
    @ViewInject(R.id.tv_theme_detail_name)
    private TextView mTvName;
    @ViewInject(R.id.tv_theme_detail_summary)
    private TextView mTvSummary;
    @ViewInject(R.id.tv_theme_detail_introduction)
    private TextView mTvIntroduction;
    @ViewInject(R.id.lv_theme_detail_listview)
    private TuJiaDiscoverDetailListView mListView;

    private List<StrategyDetail.ContentEntity> mContentEntityList = new ArrayList<>();
    private int id;
    private DiscoverStrategyDetailAdapter mAdapter;
    private List<StrategyDetail.ContentEntity.ElementsEntity.UnitListEntity> mUnitsList = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewUtils.inject(this);

        Intent intent = getIntent();
        id = intent.getIntExtra("id", 16);
        //Log.e("id...", id + "");

        mAdapter = new DiscoverStrategyDetailAdapter(this,mContentEntityList,mUnitsList);

        mListView.setAdapter(mAdapter);

        mAdapter.notifyDataSetChanged();

        loadingData();
    }

    public void loadingData(){
        String mDetailData = JsonDataUtils.getDisCoverDetailData(id);
        //Log.e("mDetailData...",mDetailData);

        NetUtils.getInstance().sentPost(
                getApplicationContext(),
                URL.URL,
                mDetailData,
                new DataCallBackListner() {
                    @Override
                    public void onSuccess(String response) {
                        Log.e("DisDetailActivity...", response);
                        //StrategyDetail mStrategyDetail = App.getApp().getGson().fromJson(response, StrategyDetail.class);
                        //StrategyDetail mStrategyDetail2 = null;
                        StrategyDetail.ContentEntity content = null;
                        StrategyDetail.ContentEntity.ElementsEntity elementsEntity = null;
                        StrategyDetail.ContentEntity.ElementsEntity.UnitListEntity mUnitListEntity = null;
                        StrategyDetail.ContentEntity.ElementsEntity.NavigationEntity mNavigationEntity = null;
                        StrategyDetail.ContentEntity.ElementsEntity.ThemeUnitsEntity mThemeUnitsEntity = null;

                        try {
                            JSONObject jsonObject1 = new JSONObject(response);
                            JSONObject contentJSON = jsonObject1.getJSONObject("content");

//                            content = new StrategyDetail.ContentEntity();
//                            content.setId(contentJSON.getInt("id"));
//                            content.setName(contentJSON.getString("name"));
//                            Log.e("name...", contentJSON.getString("name"));
//                            content.setIsActive(contentJSON.getBoolean("isActive"));
//                            content.setIntroduction(contentJSON.getString("introduction"));
//                            content.setSummary(contentJSON.getString("summary"));
//                            content.setLargePictureURL(contentJSON.getString("largePictureURL"));

                            JSONArray elementsArray = contentJSON.getJSONArray("elements");

                            //elementsArray的个数？？？？
                            Log.e("elementsArray..", elementsArray.length() + "");

                            for (int i = 0; i < elementsArray.length(); i++) {

                                content = new StrategyDetail.ContentEntity();

                                content.setId(contentJSON.getInt("id"));
                                Log.e("id...", contentJSON.getInt("id") + "");
                                content.setName(contentJSON.getString("name"));
                                Log.e("name...", contentJSON.getString("name"));
                                content.setIsActive(contentJSON.getBoolean("isActive"));
                                content.setIntroduction(contentJSON.getString("introduction"));
                                content.setSummary(contentJSON.getString("summary"));
                                content.setLargePictureURL(contentJSON.getString("largePictureURL"));

                                elementsEntity = new StrategyDetail.ContentEntity.ElementsEntity();

                                if (i == 0) {
                                    JSONObject elementsObject = elementsArray.getJSONObject(i);
                                    elementsEntity.setNodeType(elementsObject.getInt("nodeType"));
                                    //Log.e("nodeType...", elementsObject.getInt("nodeType") + "");
                                    elementsEntity.setName(elementsObject.getString("name"));
                                    //Log.e("name...", elementsObject.getString("name"));
                                } else if (i == elementsArray.length()-1) {
                                    JSONObject elementsObject = elementsArray.getJSONObject(i);
                                    elementsEntity.setName(elementsObject.getString("name"));
                                    //Log.e("name...", elementsObject.getString("name"));
                                    elementsEntity.setNodeType(elementsObject.getInt("nodeType"));
                                    //Log.e("nodeType...", elementsObject.getInt("nodeType") + "");
                                    elementsEntity.setDisplayType(elementsObject.getInt("displayType"));
                                    JSONArray unitListArray = elementsObject.getJSONArray("unitList");

                                    for (int j = 0; j < unitListArray.length(); j++) {
                                        mUnitListEntity = new StrategyDetail.ContentEntity.ElementsEntity.UnitListEntity();
                                        JSONObject unitObject = unitListArray.getJSONObject(j);

                                        mUnitListEntity.setUnitID(unitObject.getInt("unitID"));
                                        //Log.e("unitID...", unitObject.getInt("unitID") + "");

                                        mUnitListEntity.setUnitName(unitObject.getString("unitName"));
                                        mUnitListEntity.setPictureURL(unitObject.getString("pictureURL"));
                                        mUnitListEntity.setCityID(unitObject.getInt("cityID"));
                                        mUnitListEntity.setCityName(unitObject.getString("cityName"));
                                        mUnitListEntity.setScenicSpotID(unitObject.getInt("scenicSpotID"));
                                        mUnitListEntity.setSeoScenicSpotId(unitObject.getInt("seoScenicSpotId"));
                                        mUnitListEntity.setScenicSpotName(unitObject.getString("scenicSpotName"));
                                        mUnitListEntity.setHouseTypeLabel(unitObject.getString("houseTypeLabel"));
                                        mUnitListEntity.setLongitude(unitObject.getDouble("longitude"));

                                        mUnitListEntity.setLatitude(unitObject.getDouble("latitude"));
                                        mUnitListEntity.setIsFavorite(unitObject.getBoolean("isFavorite"));
                                        mUnitListEntity.setIsWorldwide(unitObject.getBoolean("isWorldwide"));
                                        mUnitListEntity.setRoomCountSummary(unitObject.getString("roomCountSummary"));
                                        mUnitListEntity.setLowestPrice(unitObject.getInt("lowestPrice"));
                                        mUnitListEntity.setHighestPrice(unitObject.getInt("highestPrice"));
                                        mUnitListEntity.setFinalPrice(unitObject.getInt("finalPrice"));
                                        mUnitListEntity.setPriceUnitLabel(unitObject.getString("priceUnitLabel"));
                                        mUnitListEntity.setRecommendPeopleCount(unitObject.getInt("recommendPeopleCount"));
                                        mUnitListEntity.setUnitInstanceCount(unitObject.getInt("unitInstanceCount"));

                                        mUnitListEntity.setEnumCooperationMode(unitObject.getInt("enumCooperationMode"));
                                        mUnitListEntity.setSubDistrict(unitObject.getString("subDistrict"));
                                        mUnitListEntity.setIsHidePrice(unitObject.getBoolean("isHidePrice"));

                                        elementsEntity.setUnitList(mUnitListEntity);

                                        mUnitsList.add(mUnitListEntity);

                                        //Log.e("mUnitListEntity...", mUnitListEntity.toString());

                                    }

                                    JSONObject navigationObject = elementsObject.getJSONObject("navigation");

                                    mNavigationEntity = new StrategyDetail.ContentEntity.ElementsEntity.NavigationEntity();

                                    mNavigationEntity.setIsWorldwide(navigationObject.getBoolean("isWorldwide"));
                                    mNavigationEntity.setCityId(navigationObject.getInt("cityId"));
                                   // Log.e("cityId...", navigationObject.getInt("cityId") + "");

                                    mNavigationEntity.setCityName(navigationObject.getString("cityName"));
                                    mNavigationEntity.setDistrictId(navigationObject.getInt("districtId"));
                                    mNavigationEntity.setAreaId(navigationObject.getInt("areaId"));

                                    mNavigationEntity.setId(navigationObject.getString("id"));
                                    mNavigationEntity.setName(navigationObject.getString("name"));
                                    mNavigationEntity.setNodeType(navigationObject.getInt("nodeType"));

                                    elementsEntity.setNavigation(mNavigationEntity);

                                    //Log.e("mNavigationEntity...", mNavigationEntity.toString());

                                    JSONArray themeUnitsArray = elementsObject.getJSONArray("themeUnits");
                                    for (int n = 0; n < themeUnitsArray.length(); n++) {
                                        mThemeUnitsEntity = new StrategyDetail.ContentEntity.ElementsEntity.ThemeUnitsEntity();

                                        JSONObject themeUnitsObject = themeUnitsArray.getJSONObject(n);

                                        mThemeUnitsEntity.setUnitID(themeUnitsObject.getInt("unitID"));
                                        mThemeUnitsEntity.setUnitName(themeUnitsObject.getString("unitName"));
                                        mThemeUnitsEntity.setPictureUrl(themeUnitsObject.getString("pictureUrl"));
                                        mThemeUnitsEntity.setSummary(themeUnitsObject.getString("summary"));
                                        mThemeUnitsEntity.setIntroduction(themeUnitsObject.getString("introduction"));
                                        mThemeUnitsEntity.setIsHidePrice(themeUnitsObject.getBoolean("isHidePrice"));

                                        elementsEntity.setThemeUnits(mThemeUnitsEntity);

                                       // Log.e("mThemeUnitsEntity...", mThemeUnitsEntity.toString());
                                    }
                                } else {
                                    JSONObject elementsObject = elementsArray.getJSONObject(i);
                                    elementsEntity.setName(elementsObject.getString("name"));
                                    //Log.e("name...", elementsObject.getString("name"));
                                    elementsEntity.setNodeType(elementsObject.getInt("nodeType"));
                                    //Log.e("nodeType...", elementsObject.getInt("nodeType") + "");
                                    elementsEntity.setIntroduction(elementsObject.getString("introduction"));
                                   // Log.e("introduction...", elementsObject.getString("introduction"));
                                    elementsEntity.setPictureUrl(elementsObject.getString("pictureUrl"));
                                   // Log.e("pictureUrl...", elementsObject.getString("pictureUrl"));

                                }
                                content.setElement(elementsEntity);
                                //Log.e("content......", content.toString());

                                mContentEntityList.add(content);
                                Log.e("mContentEntityList...", mContentEntityList.size() + "...." + mContentEntityList.toString());
                            }
                            mAdapter.notifyDataSetChanged();

                           // Log.e("......", mContentEntityList.get(5).getElement().getUnitList().toString());//????最后一个对象？？
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        DisplayImageOptions options = App.getApp().getOptions();
                        App.getApp().getImagerLoader().displayImage(content.getLargePictureURL(), mImageView, options);
                        mTvName.setText(content.getName());
                        mTvSummary.setText(content.getSummary());
                        mTvIntroduction.setText(content.getIntroduction());

                    }

                    @Override
                    public void onFailure(String error) {

                    }
                });
    }

}
