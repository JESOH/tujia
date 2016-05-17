package com.it.tujia.module.homepage.frag;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.it.tujia.R;
import com.it.tujia.constant.ConstantType;
import com.it.tujia.constant.URL;
import com.it.tujia.entity.HomeTitleVP;
import com.it.tujia.entity.HotCity;
import com.it.tujia.entity.Theme;
import com.it.tujia.impl.DataCallBackListner;
import com.it.tujia.module.homepage.activity.BannerDetalActivity;
import com.it.tujia.module.homepage.activity.DestinationActivity;
import com.it.tujia.module.homepage.activity.HotelCityActivity;
import com.it.tujia.module.homepage.adapter.BannerListAdapter;
import com.it.tujia.module.homepage.adapter.HotCityAdapter;
import com.it.tujia.utils.JsonDataUtils;
import com.it.tujia.utils.JsonUtils;
import com.it.tujia.utils.NetUtils;
import com.it.tujia.view.DisplayView_one;
import com.it.tujia.view.DisplayView_three;
import com.it.tujia.view.DisplayView_two;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;

import java.util.ArrayList;
import java.util.List;

import de.greenrobot.event.EventBus;

/**
 * Created by kkguo on 2015/11/13.
 */
public class HomeFragment extends Fragment {
    private List<Theme> list = new ArrayList<>();
    @ViewInject(R.id.vp_frag_home_banner)
    private ViewPager mViewPage;
    private NetUtils netUtils;
    private List<HomeTitleVP> mBanners = new ArrayList<>();
    @ViewInject(R.id.iv_test)
    ImageView imTest;
    @ViewInject(R.id.ll_frag_home)
    private LinearLayout mLinearLayout;
    @ViewInject(R.id.ll_point)
    private LinearLayout mLLPoint;
    BannerListAdapter adapter;
    int currentPageNO = 0;
    @ViewInject(R.id.rl_main_banner)
    private RelativeLayout mRlMainBanner;
    @ViewInject(R.id.rl_frag_domestic)
    private RelativeLayout mDomestic;
    @ViewInject(R.id.rl_frag_oversea)
    private RelativeLayout mOverSea;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        netUtils = NetUtils.getInstance();

    }

    @OnClick({R.id.rl_frag_domestic,R.id.rl_frag_oversea})
    public  void clickSearch(View view){
        switch (view.getId()){
            case R.id.rl_frag_domestic:
                //切换fragment,
                Log.e("点击事件","国内信息");
                EventBus.getDefault().post(ConstantType.MESTIC);
                break;
            case R.id.rl_frag_oversea:
                EventBus.getDefault().post(ConstantType.OVERSEA);
                break;
        }
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_homepage, container, false);
        ViewUtils.inject(this, view);
        return view;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        //判断数据，进行加载类型设置
        initBanner();
        initBannerData();
        initHotCity();
        initHotCityData();
        initThemeData();

    }

    private void initThemeData() {
        netUtils.sentPost(getContext(), URL.URL, JsonDataUtils.getHomePageData(8 + ""), new DataCallBackListner() {
            @Override
            public void onSuccess(String response) {
                List<Theme> homePageJson = JsonUtils.getThemeLists(response);
                list.clear();
                list.addAll(homePageJson);
                initTheme();
                //动态加载视图
            }

            @Override
            public void onFailure(String error) {

            }
        });
    }

    /**
     * 初始化数据
     */
    List<HotCity> hotCities = new ArrayList<>();

    private void initHotCityData() {
        String hotCityData = JsonDataUtils.getHomePageData(7 + "");
        netUtils.sentPost(getContext(), URL.URL, hotCityData, new DataCallBackListner() {
            @Override
            public void onSuccess(String response) {
                List<HotCity> hotCityData = JsonUtils.getHotCityLists(response);
                Log.e("hotCityData", hotCityData.size() + "");
                hotCities.addAll(hotCityData);
                Log.e("hotlist长度", hotCities.size() + "");
                mHotCityadapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(String error) {

            }
        });
    }

    @ViewInject(R.id.vp_frag_home_hotcity)
    private ViewPager mHotCityVP;
    @ViewInject(R.id.rl_frag_home_hotcity)
    RelativeLayout mRlHotcity;
    HotCityAdapter mHotCityadapter;

    private void initHotCity() {
        DisplayMetrics metrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(metrics);

        // 动态设置ViewPager高度
        ViewGroup.LayoutParams layoutParams = mRlMainBanner.getLayoutParams();
        layoutParams.height = metrics.heightPixels /7;
//        layoutParams.height= ViewGroup.LayoutParams.WRAP_CONTENT;
        mRlHotcity.setLayoutParams(layoutParams);

        mHotCityadapter = new HotCityAdapter(getContext(), hotCities);
        mHotCityVP.setAdapter(mHotCityadapter);
    }

    private void initBannerData() {
        String themeData = JsonDataUtils.getHomePageData(5 + "");
        netUtils.sentPost(getContext(), URL.URL, themeData, new DataCallBackListner() {
            @Override
            public void onSuccess(String response) {
                List<HomeTitleVP> titleVPs = JsonUtils.getBannerLists(response);
                mBanners.clear();
                mBanners.addAll(titleVPs);
                adapter.notifyDataSetChanged();
                addPoints();
                timerChangeBanner();
            }

            @Override
            public void onFailure(String error) {

            }
        });
    }

    /**
     * 初始化设置banner视图
     */
    private void initBanner() {
        // 获取屏幕显示尺寸对象
        DisplayMetrics metrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(metrics);

        // 动态设置ViewPager高度
        ViewGroup.LayoutParams layoutParams = mRlMainBanner.getLayoutParams();
        layoutParams.height = metrics.heightPixels / 10;
        mRlMainBanner.setLayoutParams(layoutParams);

        adapter = new BannerListAdapter(getContext(), mBanners);
        mViewPage.setAdapter(adapter);
        mViewPage.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
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
                mCurrentImageView = mImageViews.get(position);
                mCurrentImageView.setSelected(true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    /**
     * 根君得到的信息，动态加载Theme布局
     */
    private void initTheme() {
        for (int i = 0; i < list.size(); i++) {
            Theme theme = list.get(i);
            switch (theme.getDisplayType()) {

                case 1: //如果是1，进行加载自定义布局
                    mLinearLayout.addView(new DisplayView_one(getContext(), theme));
                    break;
                case 2:
                    mLinearLayout.addView(new DisplayView_two(getContext(), theme));
                    break;
                case 3:
                    mLinearLayout.addView(new DisplayView_three(getContext(), theme));
                    break;

            }
        }
    }

    private ImageView mCurrentImageView;
    private List<ImageView> mImageViews = new ArrayList<>();

    /**
     * 增加圆点
     */
    private void addPoints() {
        if (mBanners == null || mBanners.isEmpty()) {
            return;
        }

        LinearLayout.LayoutParams layoutParams
                = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);

        layoutParams.rightMargin = 15;

        int bannerSize = mBanners.size();
        for (int i = 0; i < bannerSize; i++) {
            mCurrentImageView = new ImageView(getActivity());
            mCurrentImageView.setLayoutParams(layoutParams);

            mCurrentImageView.setSelected(false);

            mCurrentImageView.setImageResource(R.drawable.selector_nav_points);

            mImageViews.add(mCurrentImageView);

            mLLPoint.addView(mCurrentImageView, i);
        }

        mCurrentImageView = mImageViews.get(0);
        mCurrentImageView.setSelected(true);
    }

    private void timerChangeBanner() {
        // mVpBanner选择对应页

        // 圆点切换

        mHander.sendEmptyMessageDelayed(1, 5000);
    }

    private Handler mHander = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if(mImageViews.size()!= 0){
                currentPageNO = ++currentPageNO % mImageViews.size();

                mCurrentImageView.setSelected(false);
                mCurrentImageView = mImageViews.get(currentPageNO);
                mCurrentImageView.setSelected(true);

                // mVpBanner选择对应页
                // 圆点切换
                mViewPage.setCurrentItem(currentPageNO);

                mHander.sendEmptyMessageDelayed(1, 5000);
            }


        }
    };

    /**
     * 附近公寓，特惠专区，我的收藏，周租月租
     *
     * @param view
     */
    @OnClick({R.id.btn_frag_home_nearby, R.id.btn_frag_home_tehui,
            R.id.btn_frag_home_week_rent, R.id.btn_frag_home_collect,
            R.id.btn_frag_home_title_search})
    public void titleOnClick(View view) {
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        switch (view.getId()) {
            case R.id.btn_frag_home_nearby:
                intent.setClass(getActivity(), HotelCityActivity.class);
                break;
            case R.id.btn_frag_home_tehui:
                intent.setClass(getActivity(), BannerDetalActivity.class);
                bundle.putInt("displayType", ConstantType.DISPLAY_TEHUI);
                intent.putExtras(bundle);
                break;
            case R.id.btn_frag_home_week_rent:
                intent.setClass(getActivity(), BannerDetalActivity.class);
                bundle.putInt("displayType", ConstantType.DISPLAY_WEEK_RENT);
                intent.putExtras(bundle);
                break;
            case R.id.btn_frag_home_collect:
                break;
            case R.id.btn_frag_home_title_search:
                intent.setClass(getActivity(), DestinationActivity.class);
                break;
        }
        getActivity().startActivity(intent);
    }
}
