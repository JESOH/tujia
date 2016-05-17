package com.it.tujia.module.feature.frag;

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
import android.widget.RelativeLayout;
import android.widget.ScrollView;

import com.handmark.pulltorefresh.library.ILoadingLayout;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshScrollView;
import com.it.tujia.R;
import com.it.tujia.constant.URL;
import com.it.tujia.impl.DataCallBackListner;
import com.it.tujia.module.feature.adapter.FeatureHotUnitsPagerAdapter;
import com.it.tujia.module.feature.constant.FeatureCategoryID;
import com.it.tujia.module.feature.entity.FeatureConditions;
import com.it.tujia.module.feature.entity.FeatureHotUnits;
import com.it.tujia.module.feature.entity.FeatureListEntity;
import com.it.tujia.module.feature.entity.FeatureUnits;
import com.it.tujia.module.feature.view.HouseStyleSubView;
import com.it.tujia.module.feature.view.HouseUnitsView;
import com.it.tujia.utils.JSONFeatureUtils;
import com.it.tujia.utils.JsonDataUtils;
import com.it.tujia.utils.NetUtils;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;

/**
 * ----------Dragon be here!----------/
 * 　　　┏┓　　　┏┓
 * 　　┏┛┻━━━┛┻┓
 * 　　┃　　　　　　　┃
 * 　　┃　　　━　　　┃
 * 　　┃　┳┛　┗┳　┃
 * 　　┃　　　　　　　┃
 * 　　┃　　　┻　　　┃
 * 　　┃　　　　　　　┃
 * 　　┗━┓　　　┏━┛
 * 　　　　┃　　　┃神兽保佑
 * 　　　　┃　　　┃代码无BUG！
 * 　　　　┃　　　┗━━━┓
 * 　　　　┃　　　　　　　┣┓
 * 　　　　┃　　　　　　　┏┛
 * 　　　　┗┓┓┏━┳┓┏┛
 * 　　　　　┃┫┫　┃┫┫
 * 　　　　　┗┻┛　┗┻┛
 * ━━━━━━神兽出没━━━━━━by:coder-shen
 * <p/>
 * Created by JunShen
 */
public class FeatureContentFragment extends Fragment {

    @ViewInject(R.id.sc_feature_content_detail)
    private PullToRefreshScrollView mPullToRefreshScrollView;

    @ViewInject(R.id.rl_scroll_content)
    private RelativeLayout rlScrollContent;

    @ViewInject(R.id.btn_feature_retry)
    private Button btnRetry;

    @ViewInject(R.id.ll_feature_no_result)
    private LinearLayout noResultLayout;

    @ViewInject(R.id.ll_content_detail)
    private RelativeLayout content_detail;

    @ViewInject(R.id.iv_toTop)
    private ImageView btnToTop;

    @ViewInject(R.id.ll_feature_waterfall_1)
    private LinearLayout childLayout_1;

    @ViewInject(R.id.ll_feature_waterfall_2)
    private LinearLayout childLayout_2;

    @ViewInject(R.id.vp_feature_banner)
    private ViewPager mHotUnitsViewPager;

    @ViewInject(R.id.ll_feature_hot_unit_point)
    private LinearLayout mLLPoint;


    private FeatureHotUnitsPagerAdapter mHotUnitsPaAdapter;


    private FeatureConditions mConditions = null;

    private List<FeatureUnits> mUnitsList = null;

    private List<FeatureHotUnits> mHotUnitsList = null;

    private static List<FeatureListEntity> mListEntities = new ArrayList<>();


    private List<ImageView> mPointsImageViews = new ArrayList<ImageView>();  //ViewPager小圆点

    private NetUtils netUtils;

    private int labelId;   //ID

    private int pageIndex;

    private int labelIndex;

    private boolean isRefresh;

    private boolean isFirstRun;

    private static FeatureContentFragment featureContentFragment = null;


    public static FeatureContentFragment newInstance(int position) {
        FeatureContentFragment featureContentFragment = new FeatureContentFragment();

        Bundle bundle = new Bundle();
        bundle.putInt("categories_index", position);
        featureContentFragment.setArguments(bundle);

        return featureContentFragment;
    }

    public static FeatureContentFragment newInstance(int position, List<FeatureListEntity> listEntities) {
        if (featureContentFragment == null){
            featureContentFragment  = new FeatureContentFragment();
        }

        Bundle bundle = new Bundle();
        bundle.putInt("categories_index", position);
        featureContentFragment.setArguments(bundle);
        if (mListEntities.isEmpty()){
            mListEntities.addAll(listEntities);
            Log.e("mListEntities.size()", mListEntities.size() + "");
        }
        return featureContentFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        labelIndex = getArguments() != null ? getArguments().getInt("categories_index") : 1;

        netUtils = NetUtils.getInstance();
        isRefresh = false;
        isFirstRun = true;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_feature_content, container, false);
        ViewUtils.inject(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        init();
    }

    /**
     * 获取传入对象中的数据
     */
    private void init() {
        switch (labelIndex) {
            case 1:
                labelId = FeatureCategoryID.FEATURE_ACCOMMODATION_ID;
                break;
            case 2:
                labelId = FeatureCategoryID.FEATURE_ROMANTIC_VACATION_ID;
                break;
            case 3:
                labelId = FeatureCategoryID.FEATURE_PARENT_TRAVEL_ID;
                break;
            case 4:
                labelId = FeatureCategoryID.FEATURE_CITY_WEEKEND_ID;
                break;
        }
        pageIndex = 0;
        mHandler.removeMessages(1);
        if (mListEntities.isEmpty()) {
            loadData();
        } else {
            mHotUnitsList = mListEntities.get(labelIndex).getHotUnits();
            mConditions = mListEntities.get(labelIndex).getConditions();
            mUnitsList = mListEntities.get(labelIndex).getUnits();
            initView();
        }
        initPullToRefresh();
        initToTopBtn();

    }


    int msgTag = 9;

    /**
     * 初始化View
     */
    private void initView() {
        initHotUnitsView();
        if (canAddPoints()) {
            timerChangeBanner();
        }
        initUnitsView();
    }


    private HouseUnitsView houseUnitsView = null;
    private List<FeatureConditions.HouseStyle> houseStyles = null;

    /**
     * 初始化item
     */
    private void initUnitsView() {
        for (int i = 0; i < mUnitsList.size(); i++) {//展示自定义的控件
            FeatureUnits units = mUnitsList.get(i);
            houseUnitsView = new HouseUnitsView(getContext(), units);
            if (i % 2 == 0) {
                if (isFirstRun) {
                    if (i == 0) {
                        for (int j = 0; j < houseStyles.size(); j++) {
                            HouseStyleSubView houseStyleSubView = new HouseStyleSubView(getContext(), houseStyles, j);
                            childLayout_1.addView(houseStyleSubView);
                        }
                        isFirstRun = false;
                    }
                    continue;
                }
                childLayout_1.addView(houseUnitsView);

            } else {
                childLayout_2.addView(houseUnitsView);
            }
        }
    }

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 1) {
                currentPageNO = ++currentPageNO % mPointsImageViews.size();

                mCurrentImageView.setSelected(false);
                mCurrentImageView = mPointsImageViews.get(currentPageNO);
                mCurrentImageView.setSelected(true);

                // 圆点切换
                mHotUnitsViewPager.setCurrentItem(currentPageNO);

                mHandler.sendEmptyMessageDelayed(1, 3000);
            } else if (msg.what == 9) {
                initView();
            } else if (msg.what == 8) {
                initFailView();
            }
            mHandler.removeMessages(msgTag);
        }
    };

    /**
     * 初始化banner
     */
    private void initHotUnitsView() {
        try {
            houseStyles = mConditions.getHouseStyles();
        } catch (Exception e) {
            Log.e("init", "mConditions数据有误");
            return;
        }
        mHotUnitsPaAdapter = new FeatureHotUnitsPagerAdapter(getContext(), mHotUnitsList);
        mHotUnitsViewPager.setAdapter(mHotUnitsPaAdapter);

        mHotUnitsPaAdapter.notifyDataSetChanged();

        mHotUnitsViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                if (mCurrentImageView == null) {
                    return;
                }
                currentPageNO = position;

                mCurrentImageView.setSelected(false);
                mCurrentImageView = mPointsImageViews.get(position);
                mCurrentImageView.setSelected(true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }


    /**
     * 初始化刷新
     */
    private void initPullToRefresh() {

        ILoadingLayout startLabels = mPullToRefreshScrollView.getLoadingLayoutProxy(true, false);
        startLabels.setPullLabel("下拉刷新");
        startLabels.setRefreshingLabel("正在刷新...");
        startLabels.setReleaseLabel("松开后刷新");
        startLabels.setLoadingDrawable(getResources().getDrawable(R.drawable.ic_pull_to_refresh_progress));

        // 上拉刷新文本
        ILoadingLayout endLabels = mPullToRefreshScrollView.getLoadingLayoutProxy(false, true);
        endLabels.setPullLabel("上拉加载更多");
        endLabels.setRefreshingLabel("加载中...");
        endLabels.setReleaseLabel("松开后加载");
        endLabels.setLoadingDrawable(getResources().getDrawable(R.drawable.ic_pull_to_refresh_progress));


        mPullToRefreshScrollView.setMode(PullToRefreshBase.Mode.BOTH);
        mPullToRefreshScrollView.setPullToRefreshOverScrollEnabled(true);
        mPullToRefreshScrollView.setOnRefreshListener(
                new PullToRefreshBase.OnRefreshListener2<ScrollView>() {
                    @Override
                    public void onPullDownToRefresh(PullToRefreshBase<ScrollView> refreshView) {
                        isRefresh = true;
                        pageIndex = 0;
                        loadData();
                    }

                    @Override
                    public void onPullUpToRefresh(PullToRefreshBase<ScrollView> refreshView) {
                        isRefresh = true;
                        pageIndex++;
                        loadData();

                    }
                });

        mPullToRefreshScrollView.setOnPullEventListener(new PullToRefreshBase.OnPullEventListener<ScrollView>() {
            @Override
            public void onPullEvent(PullToRefreshBase<ScrollView> refreshView, PullToRefreshBase.State state, PullToRefreshBase.Mode direction) {
                btnToTop.setVisibility(View.VISIBLE);
            }
        });
    }

    /**
     * 返回顶部按钮的监听
     */
    private void initToTopBtn() {
        btnToTop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scrollToBottom(rlScrollContent);
            }
        });
    }

    /**
     * 返回顶部的方法
     */
    public void scrollToBottom(final View scrollContent) {
        Handler mHandler = new Handler();
        mHandler.post(new Runnable() {
            public void run() {
                if (scrollContent == null) {
                    return;
                }
                btnToTop.setVisibility(View.GONE);
            }
        });
    }


    /**
     * 获取网络JSON中的数据
     */
    private void loadData() {

        netUtils.sentPost(getContext(), URL.URL, JsonDataUtils.getFeatureUnitsData(labelId, pageIndex),
                new DataCallBackListner() {
                    @Override
                    public void onSuccess(String response) {
//                        Log.e("loadData", "网络请求" + response);
                        if (isRefresh) {

                            currentPageNO = 0;

                            mLLPoint.removeAllViews();

                            mPointsImageViews.clear();

                            mHotUnitsList.clear();

                            mUnitsList.clear();

                            mHandler.removeMessages(1);

                            mPullToRefreshScrollView.onRefreshComplete();

                            isRefresh = false;
                        }

                        mHotUnitsList = JSONFeatureUtils.getFeatureHotUnitsData(response);

                        mConditions = JSONFeatureUtils.getFeatureConditionsData(response);

                        mUnitsList = JSONFeatureUtils.getFeatureUnits(response);


                        msgTag = 9;
                        mHandler.sendEmptyMessage(msgTag);

                        //此处可数据库操作
                    }

                    @Override
                    public void onFailure(String error) {
                        Log.e("FeatureContentFragment", "网络请求失败" + error);
                        msgTag = 8;
                        mHandler.sendEmptyMessage(msgTag);
                    }
                });
    }


    /**
     * 初始化加载数据失败的页面
     */
    private void initFailView() {

        noResultLayout.setVisibility(View.VISIBLE);
        btnRetry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                noResultLayout.setVisibility(View.GONE);
                isRefresh = true;
                pageIndex = 0;
                loadData();
            }
        });
        return;

    }


    /**
     * 圆点定时切换
     */
    private void timerChangeBanner() {

        // 通知圆点切换
        mHandler.sendEmptyMessageDelayed(1, 5000);
    }

    private int currentPageNO = 0;

    private ImageView mCurrentImageView;

    /**
     * 添加圆点
     */
    private boolean canAddPoints() {
        if (mHotUnitsList == null || mHotUnitsList.isEmpty()) {
            return false;
        }
        LinearLayout.LayoutParams layoutParams
                = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                10);

        layoutParams.setMargins(0, 0, 5, 0);

        int bannerSize = mHotUnitsList.size();

        for (int i = 0; i < bannerSize; i++) {
            mCurrentImageView = new ImageView(getContext());

            mCurrentImageView.setLayoutParams(layoutParams);

            mCurrentImageView.setSelected(false);

            mCurrentImageView.setImageResource(R.drawable.selector_nav_points);

            mPointsImageViews.add(mCurrentImageView);

            mLLPoint.addView(mCurrentImageView, i);
        }
        mCurrentImageView = mPointsImageViews.get(0);
        mCurrentImageView.setSelected(true);
        return true;
    }

}
