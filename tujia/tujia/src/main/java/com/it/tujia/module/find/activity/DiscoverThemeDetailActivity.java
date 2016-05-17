package com.it.tujia.module.find.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.internal.Utils;
import com.it.tujia.R;
import com.it.tujia.application.App;
import com.it.tujia.constant.URL;
import com.it.tujia.impl.DataCallBackListner;
import com.it.tujia.module.find.adapter.DiscoverStrategyAdapter;
import com.it.tujia.module.find.adapter.DiscoverThemeDetailAdapter;
import com.it.tujia.module.find.entity.StrategyDetail;
import com.it.tujia.module.find.entity.ThemeDetail;
import com.it.tujia.utils.JSONDiscoverUtils;
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
public class DiscoverThemeDetailActivity extends Activity {

    private int detailId;
    private List<ThemeDetail.ContentEntity.ElementsEntity.UnitListEntity> mUnitList = new ArrayList<>();
    private List<ThemeDetail.ContentEntity.ElementsEntity.ThemeUnitsEntity> mThemeList = new ArrayList<>();
    private List<ThemeDetail.ContentEntity> mContentList = new ArrayList<>();

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
    private DiscoverThemeDetailAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewUtils.inject(this);

        Intent intent = getIntent();
        detailId = intent.getIntExtra("id",170);
        Log.e("detailId...", detailId + "");

        mAdapter = new DiscoverThemeDetailAdapter(this,mContentList,mUnitList);
        mListView.setAdapter(mAdapter);

        mAdapter.notifyDataSetChanged();

        loadingData();

    }

    /**
     * 网络请求数据
     */
    private void loadingData() {
        String mThemeDetailData = JsonDataUtils.getDisCoverDetailData(detailId);

        NetUtils.getInstance().sentPost(
                getApplicationContext(),
                URL.URL,
                mThemeDetailData,
                new DataCallBackListner() {
                    @Override
                    public void onSuccess(String response) {
                        Log.e("response...",response);
                        //Gson gson = App.getApp().getGson();
                        //ThemeDetail.ContentEntity themeDetail = gson.fromJson(response, ThemeDetail.ContentEntity.class);

                        ThemeDetail.ContentEntity mContentEntity = null;
                        ThemeDetail.ContentEntity.ShareSettingEntity mShareEntity = null;
                        ThemeDetail.ContentEntity.ElementsEntity mElementsEntity = null;
                        ThemeDetail.ContentEntity.ElementsEntity.UnitListEntity mUnitEntity = null;
                        ThemeDetail.ContentEntity.ElementsEntity.NavigationEntity mNavigationEntity = null;
                        ThemeDetail.ContentEntity.ElementsEntity.ThemeUnitsEntity mThemeUnitsEntity = null;

                        try {
                            JSONObject jsonObject1 = new JSONObject(response);
                            JSONObject contentObj = jsonObject1.getJSONObject("content");

                            JSONArray elementsArray = contentObj.getJSONArray("elements");

                            for (int i = 0; i < elementsArray.length(); i++) {
                                Log.e("elementsArray...", i + 1 + "");

                                JSONObject elementsObj = elementsArray.getJSONObject(i);
                                //mContentEntity对象
                                mContentEntity = new ThemeDetail.ContentEntity();

                                mContentEntity.setId(contentObj.getInt("id"));
                                mContentEntity.setGroupId(contentObj.getInt("groupId"));
                                mContentEntity.setName(contentObj.getString("name"));
                                mContentEntity.setOrder(contentObj.getInt("order"));
                                mContentEntity.setIsActive(contentObj.getBoolean("isActive"));
                                mContentEntity.setIntroduction(contentObj.getString("introduction"));
                                mContentEntity.setSummary(contentObj.getString("summary"));
                                mContentEntity.setLargePictureURL(contentObj.getString("largePictureURL"));

                                Log.e("mContentEntity...id", contentObj.getInt("id") + "");
                                Log.e("mContentEntity...name", contentObj.getString("name"));

                                //mShareEntity对象
                                mShareEntity = new ThemeDetail.ContentEntity.ShareSettingEntity();
                                JSONObject shareObj = contentObj.getJSONObject("shareSetting");

                                mShareEntity.setShareTitle(shareObj.getString("shareTitle"));
                                mShareEntity.setShareImageUrl(shareObj.getString("shareImageUrl"));
                                mShareEntity.setShareDescription(shareObj.getString("shareDescription"));
                                mShareEntity.setShareUrl(shareObj.getString("shareUrl"));
                                mShareEntity.setShareMessage(shareObj.getString("shareMessage"));
                                mShareEntity.setEnumAppShareChannel(shareObj.getInt("enumAppShareChannel"));
                                mShareEntity.setIsReturnShareResult(shareObj.getBoolean("isReturnShareResult"));
                                mShareEntity.setIsAppendShareUser(shareObj.getBoolean("isAppendShareUser"));

                                Log.e("mShEntity...Title", shareObj.getString("shareTitle"));
                                Log.e("mShEntity...Url", shareObj.getString("shareImageUrl"));
                                Log.e("mShEntity...name", shareObj.getString("shareImageUrl"));

                                mContentEntity.setShareSetting(mShareEntity);
                                Log.e("mShareEntity...",mShareEntity.toString());

                                //mElementsEntity对象
                                mElementsEntity = new ThemeDetail.ContentEntity.ElementsEntity();

                                mElementsEntity.setId(elementsObj.getString("id"));
                                mElementsEntity.setName(elementsObj.getString("name"));
                                mElementsEntity.setNodeType(elementsObj.getInt("nodeType"));

                                //elementsObj.getString("introduction") != null && !elementsObj.getString("introduction").isEmpty()
                                if(elementsObj.has("introduction")){
                                    mElementsEntity.setIntroduction(elementsObj.getString("introduction"));
                                }

                                //"pictureUrl".equals(elementsObj.getString("pictureUrl"))
                                if(elementsObj.has("pictureUrl")){
                                    mElementsEntity.setPictureUrl(elementsObj.getString("pictureUrl"));
                                }

                                if(elementsObj.has("displayType")){
                                    mElementsEntity.setDisplayType(elementsObj.getInt("displayType"));
                                }

                                //"unitList".equals(elementsObj.getString("unitList"))
                                if(elementsObj.has("unitList")){

                                   // mUnitList.clear();

                                    JSONArray unitListArray = elementsObj.getJSONArray("unitList");
                                    for (int j = 0; j < unitListArray.length(); j++) {
                                        mUnitEntity = new ThemeDetail.ContentEntity.ElementsEntity.UnitListEntity();

                                        Log.e("unitListArray..",unitListArray.length()+"......."+j);

                                        JSONObject unitObject = unitListArray.getJSONObject(j);

                                        mUnitEntity.setUnitID(unitObject.getInt("unitID"));
                                        //Log.e("unitID...", unitObject.getInt("unitID") + "");
                                        mUnitEntity.setUnitName(unitObject.getString("unitName"));
                                        mUnitEntity.setPictureURL(unitObject.getString("pictureURL"));
                                        mUnitEntity.setCityID(unitObject.getInt("cityID"));
                                        mUnitEntity.setCityName(unitObject.getString("cityName"));
                                        mUnitEntity.setScenicSpotID(unitObject.getInt("scenicSpotID"));
                                        mUnitEntity.setSeoScenicSpotId(unitObject.getInt("seoScenicSpotId"));
                                        mUnitEntity.setScenicSpotName(unitObject.getString("scenicSpotName"));
                                        mUnitEntity.setHouseTypeLabel(unitObject.getString("houseTypeLabel"));
                                        mUnitEntity.setLongitude(unitObject.getDouble("longitude"));

                                        mUnitEntity.setLatitude(unitObject.getDouble("latitude"));
                                        mUnitEntity.setIsFavorite(unitObject.getBoolean("isFavorite"));
                                        mUnitEntity.setIsWorldwide(unitObject.getBoolean("isWorldwide"));
                                        mUnitEntity.setRoomCountSummary(unitObject.getString("roomCountSummary"));
                                        mUnitEntity.setLowestPrice(unitObject.getInt("lowestPrice"));
                                        mUnitEntity.setHighestPrice(unitObject.getInt("highestPrice"));
                                        mUnitEntity.setFinalPrice(unitObject.getInt("finalPrice"));
                                        mUnitEntity.setPriceUnitLabel(unitObject.getString("priceUnitLabel"));
                                        mUnitEntity.setRecommendPeopleCount(unitObject.getInt("recommendPeopleCount"));
                                        mUnitEntity.setUnitInstanceCount(unitObject.getInt("unitInstanceCount"));

                                        mUnitEntity.setEnumCooperationMode(unitObject.getInt("enumCooperationMode"));
                                        mUnitEntity.setSubDistrict(unitObject.getString("subDistrict"));
                                        mUnitEntity.setIsHidePrice(unitObject.getBoolean("isHidePrice"));

                                        mUnitList.add(mUnitEntity);
                                        Log.e("mUnitList...", mUnitList.toString());
                                    }
                                    mElementsEntity.setUnitList(mUnitList);

                                }
                                //"navigation".equals(elementsObj.getString("navigation"))
                                if(elementsObj.has("navigation")){
                                    JSONObject navigationObj = elementsObj.getJSONObject("navigation");
                                    mNavigationEntity = new ThemeDetail.ContentEntity.ElementsEntity.NavigationEntity();

                                    mNavigationEntity.setIsWorldwide(navigationObj.getBoolean("isWorldwide"));
                                    mNavigationEntity.setCityId(navigationObj.getInt("cityId"));
                                    mNavigationEntity.setCityName(navigationObj.getString("cityName"));
                                    mNavigationEntity.setDistrictId(navigationObj.getInt("districtId"));
                                    mNavigationEntity.setAreaId(navigationObj.getInt("areaId"));
                                    mNavigationEntity.setId(navigationObj.getString("id"));
                                    mNavigationEntity.setName(navigationObj.getString("name"));
                                    mNavigationEntity.setNodeType(navigationObj.getInt("nodeType"));

                                    Log.e("cityId...", navigationObj.getInt("cityId") + "");

                                    mElementsEntity.setNavigation(mNavigationEntity);
                                    Log.e("mNavigationEntity...", mNavigationEntity.toString());
                                }

                                if(elementsObj.has("themeUnits")){
                                    JSONArray themeUnitsArray = elementsObj.getJSONArray("themeUnits");

                                    //mThemeList.clear();//清空集合 -- 不能清空

                                    for (int k = 0; k < themeUnitsArray.length(); k++) {


                                        //最多创建15次
                                        mThemeUnitsEntity = new ThemeDetail.ContentEntity.ElementsEntity.ThemeUnitsEntity();//创建一开始对象错了
                                        JSONObject themeUnitsObj = themeUnitsArray.getJSONObject(k);

                                        mThemeUnitsEntity.setId(themeUnitsObj.getString("id"));
                                        mThemeUnitsEntity.setUnitID(themeUnitsObj.getInt("unitID"));
                                        mThemeUnitsEntity.setUnitName(themeUnitsObj.getString("unitName"));
                                        mThemeUnitsEntity.setPictureUrl(themeUnitsObj.getString("pictureUrl"));
                                        mThemeUnitsEntity.setSummary(themeUnitsObj.getString("summary"));
                                        mThemeUnitsEntity.setIntroduction(themeUnitsObj.getString("introduction"));
                                        mThemeUnitsEntity.setIsHidePrice(themeUnitsObj.getBoolean("isHidePrice"));

                                        mThemeList.add(mThemeUnitsEntity);
                                        Log.e("mThemeList...",mThemeList.toString());
                                    }

                                    mElementsEntity.setThemeUnits(mThemeList);
                                    Log.e("mElementsEntity...", mElementsEntity.toString());
                                }
                                mContentEntity.setElements(mElementsEntity);
                                Log.e("mContentEntity...", mContentEntity.toString());
                                mContentList.add(mContentEntity);

                            }
                            mAdapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        DisplayImageOptions options = App.getApp().getOptions();
                        App.getApp().getImagerLoader().displayImage(mContentEntity.getLargePictureURL(), mImageView, options);
                        mTvName.setText(mContentEntity.getName());
                        mTvSummary.setText(mContentEntity.getSummary());
                        mTvIntroduction.setText(mContentEntity.getIntroduction());
                    }

                    @Override
                    public void onFailure(String error) {

                    }
                }
        );
    }
}
