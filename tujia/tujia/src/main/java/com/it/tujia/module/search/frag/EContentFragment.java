package com.it.tujia.module.search.frag;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.it.tujia.R;
import com.it.tujia.module.search.event.LeftEvent;
import com.it.tujia.module.search.event.RightEvent;

import de.greenrobot.event.EventBus;
/**
 * Created by Administrator on 2015/11/17.
 * 订阅者
 */
public class EContentFragment extends Fragment {
    private TextView tv;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.frag_search_area_right_content,container,false);
        tv= (TextView) view.findViewById(R.id.content_tv);
        EventBus.getDefault().register(this);
        return view;
    }


    public void onEvent(String event) {
        tv.setText(event);
    }

//    public void onEventMainThread(String event) {
//        tv.setText(event);
//    };
    public void onEvent(LeftEvent event) {
        tv.setText(event.name);
    }

    public void onEvent(RightEvent event) {
        tv.setText(event.name);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }


}
