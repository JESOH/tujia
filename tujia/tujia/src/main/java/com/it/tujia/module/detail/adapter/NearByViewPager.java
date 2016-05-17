package com.it.tujia.module.detail.adapter;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import com.it.tujia.module.detail.entity.TuJiaDetail;
import com.it.tujia.module.detail.frag.CommunityFragment;
import com.it.tujia.module.detail.frag.IntroduceFragment;
import com.it.tujia.module.detail.frag.NearFragment;

import java.util.List;

/**
 * Created by Administrator on 2015/11/23.
 */
public class NearByViewPager extends PagerAdapter{

    private Context mContext;
    private List<Fragment> mFragList;
    private String[] nearbyName;
    private TuJiaDetail.ContentEntity.UnitDetailEntity unitDetail;

    public NearByViewPager(Context mContext, List<Fragment> mFragList, String[] nearbyName,TuJiaDetail.ContentEntity.UnitDetailEntity unitDetail) {
        this.mContext = mContext;
        this.mFragList = mFragList;
        this.nearbyName = nearbyName;
        this.unitDetail = unitDetail;
    }

    @Override
    public int getCount() {
        return mFragList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        Bundle bundle = new Bundle();
        bundle.putSerializable("unitDetail", unitDetail);

        if(position == 0){
            IntroduceFragment fragment = (IntroduceFragment) mFragList.get(0);
            fragment.setArguments(bundle);
            return fragment;
        }else if(position == 1){
            NearFragment fragment = (NearFragment) mFragList.get(1);
            fragment.setArguments(bundle);
            return fragment;
        }else{
            CommunityFragment fragment = (CommunityFragment) mFragList.get(2);
            fragment.setArguments(bundle);
            return fragment;
        }
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        //container.removeView((View) object);
    }

    @Override
    public CharSequence getPageTitle(int position) {

        return nearbyName[position];
    }
}
