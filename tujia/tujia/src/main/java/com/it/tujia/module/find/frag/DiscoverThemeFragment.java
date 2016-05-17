package com.it.tujia.module.find.frag;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.handmark.pulltorefresh.library.PullToRefreshGridView;
import com.it.tujia.R;
import com.it.tujia.constant.URL;
import com.it.tujia.impl.DataCallBackListner;
import com.it.tujia.module.find.adapter.DiscoverThemeAdapter;
import com.it.tujia.module.find.entity.Theme;
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
public class DiscoverThemeFragment extends Fragment {

    private static DiscoverThemeFragment mThemeFragment;

    public static DiscoverThemeFragment getThemeFragment(){
        mThemeFragment = new DiscoverThemeFragment();
        Bundle bundle = new Bundle();
        mThemeFragment.setArguments(bundle);
        return mThemeFragment;
    }

    @ViewInject(R.id.gv_gridview)
    private PullToRefreshGridView mGridView;

    private DiscoverThemeAdapter mAdapter;

    private List<Theme> mThemeList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_discover_theme,container,false);
        ViewUtils.inject(this, view);

        mAdapter = new DiscoverThemeAdapter(getActivity(), mThemeList);

        mGridView.setAdapter(mAdapter);

        mAdapter.notifyDataSetChanged();

        //initData();

        loadingData();

        return view;
    }

    /**
     * 模拟测试的数据
     */
    private void initData() {
        for (int i = 0; i < 50; i++) {

            Theme theme = new Theme();
            theme.setName("最新");
            mThemeList.add(theme);
        }

        for (int i = 0; i < 49; i++) {
            Theme theme = new Theme();
            theme.setName("四季");
            mThemeList.add(theme);
        }
    }

    private void loadingData() {
        NetUtils.getInstance().sentPost(
                getActivity(),
                URL.URL,
                JsonDataUtils.getDiscoverThemeData(URL.FIND_THEME_PARAMS_ID),
                new DataCallBackListner() {
                    @Override
                    public void onSuccess(String response) {
                        Log.e("onSuccess...", response);

                        Theme theme = null;
                        Theme.DetailListEntity listEntity = null;
                        try {
//                            JSONObject jsonObject = new JSONObject(response);
//                            JSONObject contentObject = jsonObject.getJSONObject("content");
//                            JSONArray arrayList1 = contentObject.getJSONArray("list");
//                            String conJSONString = null;
//                            for (int i = 0; i < arrayList1.length(); i++) {
//                                JSONObject conObject = arrayList1.getJSONObject(i);
//                                conJSONString = conObject.getString("con");
//                            }

                            String conJSONString = JSONDiscoverUtils.getJSONString(response);

                            Log.e("conJSONString...",conJSONString);

                            JSONObject conObject = new JSONObject(conJSONString);

                            JSONArray arrayList2 = conObject.getJSONArray("list");

                            for (int i = 0; i < arrayList2.length(); i++) {
                                JSONObject stringObj = arrayList2.getJSONObject(i);

                                String nameString = stringObj.getString("name");

                                //Log.e("nameString.....",nameString);

                                JSONArray arrayList3 = stringObj.getJSONArray("list");

                                for (int j = 0; j < arrayList3.length(); j++) {
                                    //外面的对象
                                    theme = new Theme();
                                    theme.setName(nameString);

                                    //内部对像
                                    listEntity = new Theme.DetailListEntity();
                                    JSONObject jsonObject2 = arrayList3.getJSONObject(j);

                                    listEntity.setId(jsonObject2.getInt("id"));
                                    //Log.e("id.....", jsonObject2.getInt("id") + "");
                                    listEntity.setName(jsonObject2.getString("name"));
                                    //Log.e("name.....", jsonObject2.getString("name"));

                                    listEntity.setSummary(jsonObject2.getString("summary"));
                                    listEntity.setIntroduction(jsonObject2.getString("introduction"));
                                    listEntity.setSmallPictureURL(jsonObject2.getString("smallPictureURL"));
                                    listEntity.setLargePictureURL(jsonObject2.getString("largePictureURL"));

                                    theme.setDetailList(listEntity);
                                    mThemeList.add(theme);
                                }
                            }
                            mAdapter.notifyDataSetChanged();

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
