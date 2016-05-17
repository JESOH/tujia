package com.it.tujia.module.feature.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.ViewGroup;

import com.it.tujia.module.feature.entity.FeatureCategories;
import com.it.tujia.module.feature.entity.FeatureListEntity;
import com.it.tujia.module.feature.frag.FeatureContentFragment;

import java.util.List;

/**
 * Created by JunShen
 */
public class FeatureMainPagerAdapter extends FragmentStatePagerAdapter {

    private List<FeatureCategories> mCategoriesList;
    private List<FeatureListEntity> mfeatureListEntities;
    private FragmentManager fm;

    public FeatureMainPagerAdapter(FragmentManager fm,List<FeatureListEntity> featureListEntities) {
        super(fm);
        this.fm = fm;
        this.mfeatureListEntities = featureListEntities;
        this.mCategoriesList = featureListEntities.get(0).getCategories();
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        if (position ==0){
            fragment = FeatureContentFragment.newInstance(position,mfeatureListEntities);
        }else {

            fragment = FeatureContentFragment.newInstance(position);

            fm.beginTransaction().disallowAddToBackStack();
            fm.beginTransaction().attach(fragment);
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return mCategoriesList.size();
    }

    /**
     * 获得标题
     *
     * @param position
     * @return
     */
    @Override
    public CharSequence getPageTitle(int position) {

        return mCategoriesList.get(position).getLabel();

    }

    // 初始化每个页卡选项
    @Override
    public Object instantiateItem(ViewGroup arg0, int arg1) {
        return super.instantiateItem(arg0, arg1);
    }

}
