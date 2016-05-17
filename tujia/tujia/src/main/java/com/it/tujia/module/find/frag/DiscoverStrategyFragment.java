package com.it.tujia.module.find.frag;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.it.tujia.R;
import com.it.tujia.constant.URL;
import com.it.tujia.impl.DataCallBackListner;
import com.it.tujia.module.find.adapter.DiscoverStrategyAdapter;
import com.it.tujia.module.find.entity.Strategy;
import com.it.tujia.utils.JSONDiscoverUtils;
import com.it.tujia.utils.JsonDataUtils;
import com.it.tujia.utils.NetUtils;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/11/14.
 */
public class DiscoverStrategyFragment extends Fragment{
    private static DiscoverStrategyFragment mStrategyFragment;

    public static DiscoverStrategyFragment getStrategyFragment(){
        mStrategyFragment = new DiscoverStrategyFragment();
        Bundle bundle = new Bundle();
        mStrategyFragment.setArguments(bundle);
        return mStrategyFragment;
    }

    @ViewInject(R.id.lv_Listview)
    private PullToRefreshListView mListView;

    private DiscoverStrategyAdapter mAdapter;

    private List<Strategy> mStrategyList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_discover_strategy,container,false);
        ViewUtils.inject(this, view);

        mAdapter = new DiscoverStrategyAdapter(getActivity(),mStrategyList);

        mListView.setAdapter(mAdapter);

        mAdapter.notifyDataSetChanged();

        loadingData();

        return view;
    }

    private void loadingData() {
        String mStrategyData = JsonDataUtils.getDiscoverStrategyData(URL.FIND_STRATEGY_PARAMS_ID);
        NetUtils.getInstance().sentPost(
                getActivity(),
                URL.URL,
                mStrategyData,
                new DataCallBackListner() {
            @Override
            public void onSuccess(String response) {
                Strategy mStrategy = null;
                Strategy.StrategyListEntity mListEntity = null;

                try {
                    String conJsonString = JSONDiscoverUtils.getJSONString(response);
                    Log.e("StrategyListEntity...",conJsonString);

                    JSONObject jsonObject = new JSONObject(conJsonString);
                    JSONArray arrayList1 = jsonObject.getJSONArray("list");

                    for (int i = 0; i < arrayList1.length(); i++) {

                        JSONObject jsonObject2 = arrayList1.getJSONObject(i);

                        String nameString = jsonObject2.getString("name");

                        JSONArray arrayList2 = jsonObject2.getJSONArray("list");

                        for (int j = 0; j < arrayList2.length(); j++) {
                            mStrategy = new Strategy();

                            mListEntity = new Strategy.StrategyListEntity();

                            mStrategy.setName(nameString);//推荐城市

                            JSONObject jsonObject3 = arrayList2.getJSONObject(j);

                            mListEntity.setId(jsonObject3.getInt("id"));

                            mListEntity.setName(jsonObject3.getString("name"));

                            mListEntity.setLargePictureURL(jsonObject3.getString("largePictureURL"));

                            mStrategy.setList(mListEntity);

                            mStrategyList.add(mStrategy);
                        }
                    }
                    mAdapter.notifyDataSetChanged();

                    Log.e("mStrategyList...",mStrategyList.toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(String error) {

            }
        });
    }
}
