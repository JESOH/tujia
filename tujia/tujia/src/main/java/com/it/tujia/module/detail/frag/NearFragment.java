package com.it.tujia.module.detail.frag;

import android.os.Bundle;
import android.support.annotation.Nullable;

import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.it.tujia.R;

/**
 * Created by Administrator on 2015/11/23.
 */
public class NearFragment extends Fragment {
    private static NearFragment mFragment;
    private TextView near;

    public static NearFragment getFragment(){
        mFragment = new NearFragment();
        return mFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_detail_introduce,container,false);

        near = (TextView) view.findViewById(R.id.tv_nearby_detail);

        return view;
    }

    public void setNearby(String info){
        near.setText(info);
    }
}
