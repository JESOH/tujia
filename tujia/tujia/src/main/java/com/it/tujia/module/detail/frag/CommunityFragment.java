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
public class CommunityFragment extends Fragment {

    private static CommunityFragment mFragment;
    private TextView community;

    public static CommunityFragment getFragment(){
        mFragment = new CommunityFragment();
        return mFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_detail_introduce,container,false);

        community = (TextView) view.findViewById(R.id.tv_nearby_detail);

        return view;
    }

    public void setCommunity(String info){
        community.setText(info);
    }
}
