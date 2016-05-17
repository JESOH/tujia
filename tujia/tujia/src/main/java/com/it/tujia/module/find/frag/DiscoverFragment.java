package com.it.tujia.module.find.frag;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RadioGroup;
import com.it.tujia.R;
import com.it.tujia.module.find.adapter.DiscoverViewPager;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

/**
 * Created by Administrator on 2015/11/13.
 */
public class DiscoverFragment extends Fragment implements RadioGroup.OnCheckedChangeListener, View.OnClickListener {

    private static DiscoverFragment mFragment;

    public  static DiscoverFragment getFragment(){
        mFragment = new DiscoverFragment();
        Bundle bundle = new Bundle();
        mFragment.setArguments(bundle);
        return mFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // Bundle bundle = getArguments();
    }

    private FragmentManager manager;
    @ViewInject(R.id.rg_discover_frag)
    private RadioGroup mRadioGroup;
    @ViewInject(R.id.iv_discover_actionbar_menu)
    private ImageView ivMenu;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_discover,container,false);
        ViewUtils.inject(this, view);

        manager = getChildFragmentManager();
        manager.beginTransaction().replace(R.id.fl_discover_container,DiscoverThemeFragment.getThemeFragment()).commit();

        mRadioGroup.setOnCheckedChangeListener(this);

        ivMenu.setOnClickListener(this);

        DiscoverViewPager mViewPager = new DiscoverViewPager();

        return view;
    }

    /**
     *
     * @param group
     * @param checkedId
     */
    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId){
            case R.id.rb_discover_theme:
                manager.beginTransaction().replace(R.id.fl_discover_container,DiscoverThemeFragment.getThemeFragment()).commit();
                break;

            case R.id.rb_discover_strategy:
                manager.beginTransaction().replace(R.id.fl_discover_container,DiscoverStrategyFragment.getStrategyFragment()).commit();
                break;
        }
    }

    /**
     * menu按钮
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_discover_actionbar_menu:
                View popupView = getActivity().getLayoutInflater().inflate(R.layout.frag_discover_popupwindow, null);

                final PopupWindow mPopupWindow = new PopupWindow(
                        popupView,
                        160,
                        WindowManager.LayoutParams.WRAP_CONTENT,
                        true);
                mPopupWindow.showAsDropDown(popupView, 600, 120);

                // 取消mPopupWindow
                ColorDrawable cd = new ColorDrawable(Color.TRANSPARENT);
                mPopupWindow.setBackgroundDrawable(cd);

                mPopupWindow.setTouchable(true);

                //设置空白区域取消
                mPopupWindow.setOutsideTouchable(true);

                //处理返回
                popupView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (mPopupWindow != null && mPopupWindow.isShowing()) {
                            //取消mPopupWindow显示
                            mPopupWindow.dismiss();
                        }
                    }
                });

//                ((MainActivity)getActivity()).onBackPressed();
                break;
        }
    }
}
