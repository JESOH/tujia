package com.it.tujia.module.homepage.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.it.tujia.R;
import com.it.tujia.constant.ConstantType;
import com.it.tujia.constant.URL;
import com.it.tujia.entity.HomeTitleVP;
import com.it.tujia.view.PopItem;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;

import java.io.Serializable;

@ContentView(R.layout.activity_banner_detal)
public class BannerDetalActivity extends Activity implements View.OnClickListener {

    @ViewInject(R.id.wv_bannerlist_hv)
    private WebView mWebView;
    @ViewInject(R.id.iv_banner_vp_back)
    ImageView mBack;
    @ViewInject(R.id.iv_banner_vp_more)
    ImageView mMore;
    @ViewInject(R.id.iv_banner_vp_title)
    TextView mTitle;
    @ViewInject(R.id.ll_banner_detal_more)
    LinearLayout mLinearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setContentView(R.layout.activity_banner_detal);
        ViewUtils.inject(this);
        Intent intent = getIntent();
         Bundle bundle = intent.getExtras();
         int displayType = bundle.getInt("displayType");
        HomeTitleVP msg=null;
        String url=null;
        switch (displayType){
            case ConstantType.DISPLAY_BANNER:
                msg = (HomeTitleVP) bundle.getSerializable("msg");
                mTitle.setText(msg.getName());
                url = msg.getUrl();
                break;
            case ConstantType.DISPLAY_TEHUI:
                url=URL.HOME_TEHUI_URL;
                break;
            case ConstantType.DISPLAY_WEEK_RENT:
                url=URL.HOME_WEEK_RENET_URL;
                mTitle.setText("周租月租");
                break;
        }
        mWebView.loadUrl(url);
        mBack.setOnClickListener(this);
        mMore.setOnClickListener(this);
        message.setOnClickListener(this);
        share.setOnClickListener(this);
        oreder.setOnClickListener(this);
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });


    }

    @ViewInject(R.id.banner_menu_message_hv)
    private PopItem message;
    @ViewInject(R.id.banner_menu_oreder_hv)
    private PopItem oreder;
    @ViewInject(R.id.banner_menu_share_hv)
    private PopItem share;
    boolean menuShow = false;

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_banner_vp_more:
                //显示视图
                if (menuShow) {
                    mLinearLayout.setVisibility(View.GONE);
                    menuShow=true;
                } else {
                    mLinearLayout.setVisibility(View.VISIBLE);
                    menuShow=false;
                }
                break;
            case R.id.iv_banner_vp_back:
                Toast.makeText(getApplicationContext(), "finish", Toast.LENGTH_SHORT).show();
                this.finish();
                break;
            case R.id.banner_menu_oreder_hv:
                Toast.makeText(getApplicationContext(), "订单", Toast.LENGTH_SHORT).show();
                mLinearLayout.setVisibility(View.GONE);
                break;
            case R.id.banner_menu_message_hv:
                mLinearLayout.setVisibility(View.GONE);
                Toast.makeText(getApplicationContext(), "消息", Toast.LENGTH_SHORT).show();
                break;
            case R.id.banner_menu_share_hv:
                Toast.makeText(getApplicationContext(), "分享", Toast.LENGTH_SHORT).show();
                mLinearLayout.setVisibility(View.GONE);
                break;
        }
    }

    @Override
    public void onBackPressed() {
        if(menuShow){
            mLinearLayout.setVisibility(View.GONE);
            menuShow=false;
        }else {
           this.finish();
        }
    }
}
