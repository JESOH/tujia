package com.it.tujia.module.login.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.it.tujia.R;
import com.it.tujia.module.login.frag.NormalLoginFragment;
import com.it.tujia.module.login.frag.TelLoginFragment;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;

/**
 * Created by Administrator on 2015/11/19.
 */
@ContentView(R.layout.activity_welome_login)
public class LoginActivity extends FragmentActivity implements RadioGroup.OnCheckedChangeListener, View.OnClickListener {

    @ViewInject(R.id.rg_login)
    private RadioGroup mRadioGroup;
    @ViewInject(R.id.rb_login_normal)
    private RadioButton mRbNormal;
    @ViewInject(R.id.rb_login_mobile)
    private RadioButton mRbMobile;
    @ViewInject(R.id.fl_login_container)
    private FrameLayout mFrameLayout;
    @ViewInject(R.id.iv_login_back)
    private ImageView mBackImage;

    private FragmentManager mFragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_welome_login);
        ViewUtils.inject(this);

        mRadioGroup.setOnCheckedChangeListener(this);
        mBackImage.setOnClickListener(this);

        mFragmentManager = getSupportFragmentManager();

        mFragmentManager.beginTransaction().replace(R.id.fl_login_container,new NormalLoginFragment()).commit();


    }

    /**
     * 返回键
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_login_back:
                this.finish();
                break;
        }

    }

    /**
     * RadioGroup
     * @param group
     * @param checkedId
     */
    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {

        switch (checkedId){
            case R.id.rb_login_normal:
                mFragmentManager.beginTransaction().replace(R.id.fl_login_container,new NormalLoginFragment()).commit();
                break;
            case R.id.rb_login_mobile:
                mFragmentManager.beginTransaction().replace(R.id.fl_login_container,new TelLoginFragment()).commit();
                break;
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        this.finish();

        mFragmentManager.isDestroyed();
    }



}
