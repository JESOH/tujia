package com.it.tujia.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.it.tujia.R;
import com.it.tujia.module.detail.entity.TuJiaDetail;

import java.util.List;

/**
 * Created by Administrator on 2015/11/23.
 */
public class DetailContentView extends LinearLayout{

    private TextView unitName;
    private ImageView isToMeHotel;
    private TextView highlight;
    private TextView roomCountSummary;
    private TextView featureTagList;

    public DetailContentView(Context context) {
        this(context, null);
        init(context, null);
    }

    public DetailContentView(Context context, AttributeSet attrs) {
        super(context, attrs);
        //init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        LayoutInflater.from(context).inflate(R.layout.activity_detail_introduce_header_content,this);

        unitName = (TextView) findViewById(R.id.tv_detail_item_unitName);
        isToMeHotel = (ImageView) findViewById(R.id.iv_detail_isSweetomeHotel);
        highlight = (TextView) findViewById(R.id.tv_detail_highlight);

        roomCountSummary = (TextView) findViewById(R.id.tv_detail_roomCountSummary);
        featureTagList = (TextView) findViewById(R.id.tv_detail_featureTagList1);


    }

    public void setUnit(TuJiaDetail tuJiaDetail){
        TuJiaDetail.ContentEntity.UnitDetailEntity unitDetail = tuJiaDetail.getContent().getUnitDetail();
        if(unitDetail.getUnitName() != null){
            unitName.setText(unitDetail.getUnitName());
        }else{
            unitName.setVisibility(View.GONE);
        }
    }

    public void setUnitNameText(TuJiaDetail tuJiaDetail){
        TuJiaDetail.ContentEntity.UnitDetailEntity unitDetail = tuJiaDetail.getContent().getUnitDetail();
//
//        if(unitDetail.getUnitName() != null){
//            unitName.setText(unitDetail.getUnitName());
//        }else{
//            unitName.setVisibility(View.GONE);
//        }

        if(unitDetail.isIsSweetomeHotel()){
            isToMeHotel.setVisibility(VISIBLE);
        }else{
            isToMeHotel.setVisibility(INVISIBLE);
        }

        if(unitDetail.getHighlight() != null){
            highlight.setText(unitDetail.getHighlight());
        }else{
            highlight.setVisibility(View.GONE);
        }
        if(unitDetail.getRoomCountSummary() != null ){
            roomCountSummary.setText(unitDetail.getRoomCountSummary() + unitDetail.getArea() + "平方" +
                    unitDetail.getBedCount() + "床" + "宜住" + unitDetail.getGuestLimit() + "人");
        }

        List<String> featureTagList1 = unitDetail.getFeatureTagList();
        for (int i = 0; i < featureTagList1.size(); i++) {
            featureTagList.setText(featureTagList1.get(0));
        }
    }
}
