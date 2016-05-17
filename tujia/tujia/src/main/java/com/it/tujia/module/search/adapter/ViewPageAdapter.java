package com.it.tujia.module.search.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;

import java.util.List;

/**
 * Created by kkguo on 2015/11/17.
 */
public class ViewPageAdapter extends FragmentStatePagerAdapter{
    private List<Fragment> lists;
    private String[]title;

    public ViewPageAdapter(FragmentManager fm,List<Fragment> lists,String[]title) {
        super(fm);
        this.lists=lists;
        this.title=title;
    }

    @Override
    public Fragment getItem(int position) {
        return lists.get(position);
    }

    @Override
    public int getCount() {
        return lists.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return title[position];
    }
}
