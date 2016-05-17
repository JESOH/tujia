package com.it.tujia.module.feature.frag;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.it.tujia.R;
import com.it.tujia.common.SlidingTabLayout;
import com.it.tujia.constant.URL;
import com.it.tujia.impl.DataCallBackListener2;
import com.it.tujia.module.feature.adapter.FeatureMainPagerAdapter;
import com.it.tujia.module.feature.constant.FeatureCategoryID;
import com.it.tujia.module.feature.entity.FeatureCategories;
import com.it.tujia.module.feature.entity.FeatureConditions;
import com.it.tujia.module.feature.entity.FeatureHotUnits;
import com.it.tujia.module.feature.entity.FeatureListEntity;
import com.it.tujia.module.feature.entity.FeatureUnits;
import com.it.tujia.module.feature.view.CircleProgressBar;
import com.it.tujia.utils.JSONFeatureUtils;
import com.it.tujia.utils.JsonDataUtils;
import com.it.tujia.utils.NetUtils;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by JunShen
 */
public class FeatureMainFragment extends Fragment {


    @ViewInject(R.id.sliding_tab_feature)
    private SlidingTabLayout mSlidingTabLayout;

    @ViewInject(R.id.progress_bar)
    private CircleProgressBar circleProgressBar;

    @ViewInject(R.id.vp_units_content)
    private ViewPager mViewPager;

    @ViewInject(R.id.iv_filter)
    private ImageView btnFilter;

    @ViewInject(R.id.btn_feature_retry)
    private Button btnRetry;

    @ViewInject(R.id.ll_feature_no_result)
    private LinearLayout noResultLayout;


    private FeatureMainPagerAdapter mFeatureMainPagerAdapter;

    private NetUtils netUtils;

    private static boolean isFirstRun = true;
    ;

    private static boolean isFailLoadData;

    private int labelId;   //ID

    private int pageIndex;

    private FeatureConditions conditions = null;

    private List<FeatureUnits> unitsList = null;

    private List<FeatureHotUnits> hotUnitsList = null;

    private List<FeatureCategories> categoriesList = null;

    private List<FeatureListEntity> mfeatureListEntities = new ArrayList<>();

    private List<FeatureListEntity> temp = new ArrayList<>();


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        netUtils = NetUtils.getInstance();

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_feature, container, false);
        ViewUtils.inject(this, view);
        initData();
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        doLoadingData();
    }

    private int index = 0;

    /**
     * 初始化变量
     */
    private void initData() {

        isFailLoadData = false;
        pageIndex = 0;

        FeatureListEntity featureListEntity0 = new FeatureListEntity();
        FeatureListEntity featureListEntity1 = new FeatureListEntity();
        FeatureListEntity featureListEntity2 = new FeatureListEntity();
        FeatureListEntity featureListEntity3 = new FeatureListEntity();

        temp.add(featureListEntity0);
        temp.add(featureListEntity1);
        temp.add(featureListEntity2);
        temp.add(featureListEntity3);

    }

    /**
     * 加载数据
     */
    private void doLoadingData() {
        circleProgressBar.setVisibility(View.VISIBLE);
        for (int i = 0; i < 4; i++) {
            if (i == 0) {
                labelId = FeatureCategoryID.FEATURE_ACCOMMODATION_ID;
            } else if (i == 1) {
                labelId = FeatureCategoryID.FEATURE_ROMANTIC_VACATION_ID;

            } else if (i == 2) {
                labelId = FeatureCategoryID.FEATURE_PARENT_TRAVEL_ID;

            } else if (i == 3) {
                labelId = FeatureCategoryID.FEATURE_CITY_WEEKEND_ID;
            }
            loadData();

        }
    }

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 999) {
                circleProgressBar.doDefaultProgress();
            }else if (msg.what == 888){
                initSlidingTabView();
            }
        }
    };

    /**
     * 获取JSON中的数据
     */
    private void loadData() {

        netUtils.sentPostWithProgress(getContext(), URL.URL, JsonDataUtils.getFeatureUnitsData(labelId, pageIndex),
                new DataCallBackListener2() {
                    @Override
                    public void onSuccess(String response) {

                        categoriesList = JSONFeatureUtils.getFeatureCategoriesData(response);

                        conditions = JSONFeatureUtils.getFeatureConditionsData(response);

                        hotUnitsList = JSONFeatureUtils.getFeatureHotUnitsData(response);

                        unitsList = JSONFeatureUtils.getFeatureUnits(response);

                        temp.get(index).setCategories(categoriesList);
                        temp.get(index).setConditions(conditions);
                        temp.get(index).setHotUnits(hotUnitsList);
                        temp.get(index).setUnits(unitsList);

                        if (!isFailLoadData && index == 3) {
                            mfeatureListEntities.addAll(temp);
                            mHandler.sendEmptyMessage(888);
                        }
                        index+=1;
                    }

                    @Override
                    public void onFailure(int statusCode, String response, Throwable throwable) {
                        circleProgressBar.setVisibility(View.GONE);
//                        Log.e("FeatureMainFragment", "网络请求失败:" + response);
                        isFailLoadData = true;
                        initFailView();
                    }

                    @Override
                    public void onProgress(long bytesWritten, long totalSize) {
                        mHandler.sendEmptyMessage(999);
                    }

                    @Override
                    public void onUserException(Throwable error) {
                        circleProgressBar.setVisibility(View.GONE);
//                        Log.e("FeatureMainFragment", "网络异常:" + error);
                        isFailLoadData = true;
                        initFailView();
                    }
                }
        );

    }

    /**
     * SlidingTabLayout初始化
     */
    private void initSlidingTabView() {
        mHandler.removeMessages(888);
        mHandler.removeMessages(999);

        Log.e("initSlidingTabView: ", "-----");
        // 创建ViewPager适配器
        mFeatureMainPagerAdapter = new FeatureMainPagerAdapter(
                getChildFragmentManager(), mfeatureListEntities);

        mViewPager.setAdapter(mFeatureMainPagerAdapter);

        //设置滚动条颜色
        mSlidingTabLayout.setSelectedIndicatorColors(Color.argb(255, 0x59, 0xA9, 0xFF));

        //自定义展示样式
        mSlidingTabLayout.setCustomTabView(R.layout.tab_view, R.id.tabText);

        // 建立SlidingTabLayout和ViewPager关联
        mSlidingTabLayout.setViewPager(mViewPager);


        mSlidingTabLayout.setVisibility(View.VISIBLE);
        btnFilter.setVisibility(View.VISIBLE);
        mViewPager.setVisibility(View.VISIBLE);
        mFeatureMainPagerAdapter.notifyDataSetChanged();

        if (circleProgressBar != null) {
            circleProgressBar.setVisibility(View.GONE);
            circleProgressBar = null;
        }


    }

    /**
     * 连接失败页面初始化
     */
    private void initFailView() {
        noResultLayout.setVisibility(View.VISIBLE);
        if (isFirstRun) {
            btnRetry.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    noResultLayout.setVisibility(View.GONE);
                    mfeatureListEntities.clear();
                    initData();
                    doLoadingData();
                }
            });
        }
    }


}
