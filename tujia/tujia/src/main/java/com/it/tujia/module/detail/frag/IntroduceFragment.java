package com.it.tujia.module.detail.frag;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.it.tujia.R;
import com.it.tujia.module.detail.entity.TuJiaDetail;

import java.io.Serializable;

/**
 * Created by Administrator on 2015/11/23.
 */
public class IntroduceFragment extends Fragment {

    private static IntroduceFragment mFragment;
    private static TextView introduce;
    private static TuJiaDetail.ContentEntity.UnitDetailEntity unitDetail;

    public static IntroduceFragment getFragment(){
        mFragment = new IntroduceFragment();
        Bundle bundle = new Bundle();
        unitDetail = (TuJiaDetail.ContentEntity.UnitDetailEntity) bundle.getSerializable("unitDetail");

        return mFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view  = inflater.inflate(R.layout.frag_detail_introduce,container,false);

        introduce = (TextView) view.findViewById(R.id.tv_nearby_detail);
        introduce.setText(unitDetail.getIntroduction());

//        TuJiaDetailActivity.NearbyViewDialogFragment nearbyViewDialogFragment = new TuJiaDetailActivity.NearbyViewDialogFragment();
//        ((TuJiaDetailActivity)getActivity()).setOnTextListener(new TuJiaDetailActivity.OnTextCallBackListener() {
//            @Override
//            public void setTextView(String str) {
//                introduce.setText(str);
//            }
//        });

        return view;
    }

    public void setIntroduce(String info){
        introduce.setText(info);
    }

}
