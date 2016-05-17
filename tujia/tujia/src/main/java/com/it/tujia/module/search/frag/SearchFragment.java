package com.it.tujia.module.search.frag;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.it.tujia.R;
import com.it.tujia.common.SlidingTabLayout;
import com.it.tujia.constant.ConstantType;
import com.it.tujia.module.search.adapter.ViewPageAdapter;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kkguo on 2015/11/13.
 */
public class SearchFragment extends Fragment {

    public SearchFragment(){

    }


    public static SearchFragment getInstance(int searchType){
        Bundle bundle=new Bundle();
        bundle.putInt("searchType",searchType);
        SearchFragment searchFragment=new SearchFragment();
        searchFragment.setArguments(bundle);
        return  searchFragment;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.frag_search, container, false);
        ViewUtils.inject(this, view);
        initTab();

        return view;
    }

    private void initTab() {
        mSlidingTab.setCustomTabView(R.layout.tab_view, R.id.tabText);
        mSlidingTab.setSelectedIndicatorColors(R.color.red);
    }

    @ViewInject(R.id.vp_frag_searhc)
    private ViewPager mViewPage;
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        mViewPage.setAdapter(adapter);
        mSlidingTab.setViewPager(mViewPage);
        adapter.notifyDataSetChanged();
        switch (searchType){
            case ConstantType.MESTIC:

                break;
            case ConstantType.OVERSEA:
                Log.e("OVERSEA",ConstantType.OVERSEA +"");
                mViewPage.setCurrentItem(2,true);
                break;
        }

    }
    int searchType;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initdata();
         Bundle bundle = getArguments();

        searchType = bundle.getInt("searchType", ConstantType.MESTIC);
        adapter = new ViewPageAdapter(getChildFragmentManager(),fragmentlist,title);
    }
    ViewPageAdapter adapter;
    @ViewInject(R.id.stl_frag_search)
    SlidingTabLayout mSlidingTab;
    List<Fragment> fragmentlist=new ArrayList<>();
    String []title={"国内公寓","海外港台"};
    private void initdata() {
        MesticFrag mesticFrag=new MesticFrag();
        fragmentlist.add(mesticFrag);
        OverSeaFrag overSeaFrag=new OverSeaFrag();
        fragmentlist.add(overSeaFrag);

    }

}
