package com.it.tujia.module.guide.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.AnimationSet;
import android.widget.Button;
import android.widget.ImageView;

import com.it.tujia.R;
import com.it.tujia.constant.ConstantType;
import com.it.tujia.module.MainActivity;
import com.it.tujia.module.guide.adapter.WelcomePagerAdapter;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;

import de.greenrobot.event.EventBus;

/**
 * Created by JunShen on 2015/11/14.
 */

@ContentView(R.layout.activity_welcome)
public class WelcomeActivity extends Activity implements ViewPager.OnPageChangeListener {

    @ViewInject(R.id.vp_first_guide)
    private ViewPager mFirstGuideViewPager;

    @ViewInject(R.id.iv_load_home_img)
    private ImageView mLoadHomeImageView;

    @ViewInject(R.id.btn_goToHome)
    private Button btnGoToHome;

    private boolean isFirstRun;

    private int[] imageIds = {R.drawable.guide_1, R.drawable.guide_2, R.drawable.guide_3, R.drawable.guide_4};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //设置全屏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        ViewUtils.inject(this);

        initData();

        initView();
        EventBus.getDefault().register(this);
    }

    /**
     * 初始化数据
     */
    private void initData() {
        SharedPreferences sharedPreferences = getSharedPreferences("FirstGuide", Context.MODE_PRIVATE);

        isFirstRun = sharedPreferences.getBoolean("isFirstRun", true);
    }

    private WelcomePagerAdapter mWelcomePagerAdapter;

    /**
     * 初始化视图
     */
    private void initView() {
        //加载第一屏ImageView
        mLoadHomeImageView.setScaleType(ImageView.ScaleType.FIT_XY);
        mLoadHomeImageView.setVisibility(ImageView.VISIBLE);


        //设置动画
        setAlphaAnimation(mLoadHomeImageView);

        if (isFirstRun) {
            handlerLoadGuidePage.sendEmptyMessageDelayed(1, 3000);

        } else {
            handlerLoadHome.sendEmptyMessageDelayed(1, 3000);
        }
    }


    /**
     * 加载引导页
     */
    private Handler handlerLoadGuidePage = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            mFirstGuideViewPager.setVisibility(ViewPager.VISIBLE);
            mWelcomePagerAdapter = new WelcomePagerAdapter(WelcomeActivity.this, imageIds);
            mFirstGuideViewPager.setAdapter(mWelcomePagerAdapter);
            mFirstGuideViewPager.setCurrentItem(0);
            mFirstGuideViewPager.addOnPageChangeListener(WelcomeActivity.this);
        }
    };

    private Handler handlerLoadHome = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            loadHomePage();
        }
    };
    /**
     * 跳转到主页
     */
    private void loadHomePage() {
        Intent intent = new Intent();
        intent.setClass(WelcomeActivity.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        getApplicationContext().startActivity(intent);

        overridePendingTransition(R.anim.abc_fade_in, R.anim.abc_fade_out);

        WelcomeActivity.this.finish();
    }

    public void  onEvent(Integer finish){
        if(finish== ConstantType.INSERT_CITY_FINISH){
//            Log.e("welcome_finish","welcome_finish");
            WelcomeActivity.this.finish();
        }
    }

    /**
     * 渐变动画
     *
     * @param imageView
     */
    private void setAlphaAnimation(ImageView imageView) {
        AnimationSet animationSet = new AnimationSet(true);
        AlphaAnimation alphaAnimation = new AlphaAnimation(0.7f, 1);
        alphaAnimation.setDuration(1000);
        animationSet.addAnimation(alphaAnimation);
        imageView.startAnimation(animationSet);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        if (position == imageIds.length - 1) {
            btnGoToHome.setBackgroundResource(R.drawable.guide_btn_selector);
            btnGoToHome.setText("开始体验");
            btnGoToHome.setTextColor(getResources().getColor(R.color.orange));
            btnGoToHome.setVisibility(Button.VISIBLE);
            btnGoToHome.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SharedPreferences.Editor editor =
                            getSharedPreferences("FirstGuide", Context.MODE_PRIVATE).edit();
                    editor.putBoolean("isFirstRun", false);
                    editor.commit();

                    loadHomePage();
                }
            });
        } else {
            if (btnGoToHome != null) {
                btnGoToHome.setVisibility(Button.GONE);
            }
        }
    }

    @Override
    public void onPageSelected(int position) { }

    @Override
    public void onPageScrollStateChanged(int state) { }

    @Override
    public void onBackPressed() {
        handlerLoadHome.removeMessages(1);
        super.onBackPressed();
    }

    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }
}
