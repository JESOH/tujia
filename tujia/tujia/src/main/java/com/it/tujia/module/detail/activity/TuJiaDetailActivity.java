package com.it.tujia.module.detail.activity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.it.tujia.R;
import com.it.tujia.application.App;
import com.it.tujia.common.SlidingTabLayout;
import com.it.tujia.constant.URL;
import com.it.tujia.impl.DataCallBackListner;
import com.it.tujia.module.detail.adapter.BannerViewPager;
import com.it.tujia.module.detail.adapter.NearByViewPager;
import com.it.tujia.module.detail.adapter.TuJiaDetailAdapter;
import com.it.tujia.module.detail.entity.TuJiaDetail;
import com.it.tujia.module.detail.frag.CommunityFragment;
import com.it.tujia.module.detail.frag.IntroduceFragment;
import com.it.tujia.module.detail.frag.NearFragment;
import com.it.tujia.module.detail.frag.TuJiaDetailFragment;
import com.it.tujia.utils.JsonDataUtils;
import com.it.tujia.utils.NetUtils;
import com.it.tujia.view.TuJiaDiscoverDetailListView;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;

import java.util.ArrayList;
import java.util.List;

import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;

import static android.widget.LinearLayout.OnClickListener;

/**
 * Created by Administrator on 2015/11/19.
 */
@ContentView(R.layout.activity_detail_introduce)
public class TuJiaDetailActivity extends FragmentActivity{

    @ViewInject(R.id.sl_detail_scrollView)
    private ScrollView mScrollView;
    @ViewInject(R.id.lv_detail_listview_item)
    private TuJiaDiscoverDetailListView mListView;
    @ViewInject(R.id.vp_detail_viewPager)
    private ViewPager mViewPager;
    @ViewInject(R.id.fl_fg_baidumap)
    private FrameLayout mBaiduMap;
    @ViewInject(R.id.iv_detail_back)
    private ImageView back;
    @ViewInject(R.id.iv_detail_share)
    private ImageView share;

    @ViewInject(R.id.tv_detail_address)
    private TextView mAddress;
    @ViewInject(R.id.tv_detail_introduction)
    private TextView mIntroduce;
    @ViewInject(R.id.tv_detail_unitName)
    private TextView unitName;
    @ViewInject(R.id.iv_detail_isSweetomeHotel)
    private ImageView isToMeHotel;
    @ViewInject(R.id.tv_detail_highlight)
    private TextView highlight;
    @ViewInject(R.id.tv_detail_roomCountSummary)
    private TextView roomCountSummary;
    @ViewInject(R.id.tv_detail_featureTagList1)
    private TextView featureTagList1;
    @ViewInject(R.id.tv_detail_featureTagList2)
    private TextView featureTagList2;
    @ViewInject(R.id.tv_detail_featureTagList3)
    private TextView featureTagList3;
    @ViewInject(R.id.tv_detail_featureTagList4)
    private TextView featureTagList4;
    @ViewInject(R.id.tv_detail_commentSample)
    private TextView commentSample;
    @ViewInject(R.id.tv_detail_overall)
    private TextView overall;
    @ViewInject(R.id.tv_detail_totalCount)
    private TextView totalCount;
    @ViewInject(R.id.tv_detail_hotelRemarks)
    private TextView hotelRemarks;
    @ViewInject(R.id.tv_detail_unitID)
    private TextView unitId;

    private int unitID;
    private Gson gson;
    private List<TuJiaDetail.ContentEntity.UnitDetailEntity.SimilarUnitsEntity> mSimilarEntity = new ArrayList<>();
    private List<TuJiaDetail.ContentEntity.UnitDetailEntity.PictureListEntity> mPictureList = new ArrayList<>();
    private TuJiaDetailAdapter mAdapter;
    private FragmentManager manager;
    private BannerViewPager mVpBanner;
    private static TuJiaDetail.ContentEntity.UnitDetailEntity unitDetail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewUtils.inject(this);

        manager = getSupportFragmentManager();

        initView();
        loadingData();

        mScrollView.fullScroll(ScrollView.FOCUS_UP);
    }

    private void initView(){
        Intent intent = getIntent();
        unitID = intent.getIntExtra("unitID", 22155);
        Log.e("unitID...", unitID + "");

        gson = App.getApp().getGson();

        back.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mAdapter = new TuJiaDetailAdapter(getApplicationContext(),mSimilarEntity);

        mListView.setAdapter(mAdapter);

        mAdapter.notifyDataSetChanged();

    }

    /**
     * 网络请求数据
     */
    private void loadingData() {
        String detailIntroduceData = JsonDataUtils.getDetailIntroduceData(unitID);

        NetUtils.getInstance().sentPost(
                getApplicationContext(),
                URL.URL,
                detailIntroduceData,
                new DataCallBackListner() {
                    @Override
                    public void onSuccess(String response) {
                        Log.e("response..", response);
                        TuJiaDetail tuJiaDetail = gson.fromJson(response, TuJiaDetail.class);

                        unitDetail = tuJiaDetail.getContent().getUnitDetail();

                        mSimilarEntity.addAll(unitDetail.getSimilarUnits());

                        mPictureList.addAll(unitDetail.getPictureList());


                        mVpBanner = new BannerViewPager(getApplicationContext(), mPictureList);

                        mViewPager.setAdapter(mVpBanner);


                        mAdapter.notifyDataSetChanged();


                        data();

//                        if(onTextListener != null){
//                            onTextListener.setTextView(unitDetail.getIntroduction());
//                        }

                        manager.beginTransaction().add(R.id.fl_fg_baidumap,TuJiaDetailFragment.getFragment()).commit();

                        if(unitDetail.getIntroduction() != null){
                            IntroduceFragment introduceFragment = IntroduceFragment.getFragment();
//                            introduceFragment.setIntroduce(unitDetail.getIntroduction());//应该用接口回调

                            Bundle bundle = new Bundle();
                            bundle.putSerializable("unitDetail", unitDetail);
                            introduceFragment.setArguments(bundle);

                            mFragList.add(introduceFragment);
                        }

                        if(unitDetail.getSurrounding().getIntroduction() != null){
                            NearFragment nearFragment = NearFragment.getFragment();
//                          nearFragment.setNearby(unitDetail.getSurrounding().getIntroduction());
                            mFragList.add(nearFragment);
                        }
//                      if(unitDetail.getRq().getIntroduction() != null) {
                          CommunityFragment mFragment = CommunityFragment.getFragment();
//                         mFragment.setCommunity(unitDetail.getRq().getIntroduction());
                          mFragList.add(mFragment);
//                      }
                }

                    @Override
                    public void onFailure(String error) {

                    }
                }
        );
    }

    @OnClick(R.id.tv_detail_nearby)
    public void doNearby(View view){
        showNearbyDialog();
    }

    @SuppressLint("NewApi")
    private void showNearbyDialog() {
        android.support.v4.app.FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        android.support.v4.app.Fragment nearbyFragment = getSupportFragmentManager().findFragmentByTag("nearby");

        if(null != nearbyFragment){
            ft.remove(nearbyFragment);
        }
        NearbyViewDialogFragment nearbyDialogFragment = new NearbyViewDialogFragment();

        //ft.add(R.id.vp_health,mFragList.get(0));

        nearbyDialogFragment.show(ft, "nearby");

        //nearbyDialogFragment.getFragmentManager().beginTransaction().replace(R.id.vp_health,new IntroduceFragment()).commit();

    }

    private OnTextCallBackListener onTextListener;

    public interface OnTextCallBackListener{
        void setTextView(String str);
    }

    public void setOnTextListener(OnTextCallBackListener onTextListener){
        this.onTextListener = onTextListener;
    }

    private static List<android.support.v4.app.Fragment> mFragList = new ArrayList<>();

    private static SlidingTabLayout mSlidingTab;
    private static ViewPager mVPNearby;
    public static class NearbyViewDialogFragment extends android.support.v4.app.DialogFragment{
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {

            View view = getActivity().getLayoutInflater().inflate(R.layout.nearby_view_layout,null);
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

            String[] nearbyName = {"介绍","小区","周边"};

            image = (ImageView) view.findViewById(R.id.iv_alert_close);
            mSlidingTab = (SlidingTabLayout) view.findViewById(R.id.sliding_tab);
            mVPNearby = (ViewPager) view.findViewById(R.id.vp_health);

            NearByViewPager mNearbyAdapter = new NearByViewPager(getActivity(), mFragList, nearbyName, unitDetail);
            mVPNearby.setAdapter(mNearbyAdapter);
//            NearByPagerAdapter mNearbyAdapter = new NearByPagerAdapter(getFragmentManager(),unitDetail,nearbyName);
//            mVPNearby.setAdapter(mNearbyAdapter);

            mSlidingTab.setSelectedIndicatorColors(Color.RED);


            mSlidingTab.setViewPager(mVPNearby);

            builder.setView(view);

            image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dismiss();
                }
            });

            return builder.create();
        }


    }

    @OnClick(R.id.tv_hold_rule)
    private void doDate(View view) {
        showAlertDialog();
    }

    private void showAlertDialog() {
        FragmentTransaction ft  = getFragmentManager().beginTransaction();
        Fragment fragment = getFragmentManager().findFragmentByTag("rule");
        if (null != fragment) {
            ft.remove(fragment);
        }

        RuleViewDialogFragment ruleViewDialogFragment = new RuleViewDialogFragment();
        ruleViewDialogFragment.show(ft, "rule");
//        ft.commit();
    }

    private static TextView ruleMsg;
    private static ImageView image;
    /**
     * 使用DialogFragment选择
     */
    public static class RuleViewDialogFragment extends DialogFragment {
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {

            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

            View view = getActivity().getLayoutInflater().inflate(R.layout.rule_view_layout,null);

            ruleMsg = (TextView) view.findViewById(R.id.tv_detail_rule_msg);
            image = (ImageView) view.findViewById(R.id.iv_alert_close);

            if(unitDetail == null){
                return null;
            }

            ruleMsg.setText(unitDetail.getHotelRemarks());
            image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dismiss();
                }
            });

            builder.setView(view);
            builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });

            return builder.create();
        }
    }

    private void data(){
        mAddress.setText(unitDetail.getAddress());//酒店地址
        mIntroduce.setText(unitDetail.getIntroduction());//酒店介绍


        if(unitDetail.getUnitName() != null){
            unitName.setText(unitDetail.getUnitName());
        }else{
            unitName.setVisibility(View.GONE);
        }

        if(unitDetail.isIsSweetomeHotel()){
            isToMeHotel.setVisibility(View.VISIBLE);
        }else{
            isToMeHotel.setVisibility(View.INVISIBLE);
        }

        if(unitDetail.getHighlight() != null){
            highlight.setText(unitDetail.getHighlight());
        }else{
            highlight.setVisibility(View.GONE);
        }
        if(unitDetail.getRoomCountSummary() != null ){
            roomCountSummary.setText(unitDetail.getRoomCountSummary() + unitDetail.getArea() + "平方" +
                    unitDetail.getBedCount() + "床" + "宜住" + unitDetail.getGuestLimit() + "人");
        }

        List<String> tagList = unitDetail.getFeatureTagList();
        for (int i = 0; i < tagList.size(); i++) {
            if (i == 0){
                featureTagList1.setVisibility(View.VISIBLE);
                featureTagList1.setText(tagList.get(0));
            }
            if (i == 1) {
                featureTagList2.setVisibility(View.VISIBLE);
                featureTagList2.setText(tagList.get(1));
            }
            if (i == 2) {
                featureTagList3.setVisibility(View.VISIBLE);
                featureTagList3.setText(tagList.get(2));
            }
            if (i == 3) {
                featureTagList4.setVisibility(View.VISIBLE);
                featureTagList4.setText(tagList.get(3));
            }
        }
        //百度地图定位
        TuJiaDetailFragment.setLatLng(unitDetail.getLatitude(),unitDetail.getLongitude());

        overall.setText(unitDetail.getUnitCommentSummary().getOverall()+"");
        totalCount.setText(unitDetail.getUnitCommentSummary().getTotalCount()+"");

        if(unitDetail.getUnitCommentSummary().getCommentSample() != null){
            commentSample.setText(unitDetail.getUnitCommentSummary().getCommentSample());
        }else{
            commentSample.setVisibility(View.GONE);
        }

        //规则和保障
        if(unitDetail.getHotelRemarks() != null){
            hotelRemarks.setText(unitDetail.getHotelRemarks());
        }

        //房间编号
        unitId.setText("房屋编号:" + unitDetail.getUnitID());

    }

    /**
     * 分享
     */
    public void doShare(View view){


            //初始化
            ShareSDK.initSDK(this);

            OnekeyShare oks = new OnekeyShare();
            //关闭sso授权
            oks.disableSSOWhenAuthorize();

            // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
            oks.setTitle(getString(R.string.share));
            // titleUrl是标题的网络链接，仅在人人网和QQ空间使用
        oks.setTitleUrl(unitDetail.getShareUrl());

            // text是分享文本，所有平台都需要这个字段
            oks.setText(unitDetail.getUnitName());

            //可以分享网络图片
            oks.setImageUrl(unitDetail.getDefaultPictureURL());

            // url仅在微信（包括好友和朋友圈）中使用
            oks.setUrl(unitDetail.getShareUrl());
//        oks.setUrl("http://www.baidu.com");

        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
        oks.setComment("我是测试评论文本");

        // site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite(getString(R.string.app_name));

        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl(unitDetail.getShareUrl());

        // 启动分享GUI
        oks.show(this);

    }
}
