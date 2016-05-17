package com.it.tujia.module.feature.view;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.it.tujia.module.feature.entity.FeatureConditions;

import java.util.List;

/**
 * 标签
 *
 * Created by JunShen
 */
public class HouseStyleSubView extends LinearLayout {


    public HouseStyleSubView(Context context, List<FeatureConditions.HouseStyle> houseStyles,int position) {
        super(context);
        init(context, houseStyles,position);
    }

    public HouseStyleSubView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    private void init(Context context, List<FeatureConditions.HouseStyle> houseStyles,int position) {
        LinearLayout parentLayout = new LinearLayout(context);
        parentLayout.setOrientation(LinearLayout.HORIZONTAL);
        LinearLayout.LayoutParams parentParams =
                new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT);
        parentLayout.setLayoutParams(parentParams);

        LinearLayout.LayoutParams childParams = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1);
        //子布局集1
        LinearLayout subLayout1 = new LinearLayout(context);
        subLayout1.setOrientation(LinearLayout.HORIZONTAL);
        subLayout1.setLayoutParams(childParams);

        //子布局集2
        LinearLayout subLayout2 = new LinearLayout(context);
        subLayout2.setOrientation(LinearLayout.HORIZONTAL);
        subLayout2.setLayoutParams(childParams);

        parentLayout.addView(subLayout1);
        parentLayout.addView(subLayout2);

        if (position % 2 == 0) {
            //第一个TextView
            TextView tv_sub_left = new TextView(getContext());
            tv_sub_left.setGravity(Gravity.CENTER);
            tv_sub_left.setClickable(true);
            tv_sub_left.setText(houseStyles.get(position).getLabel());
            tv_sub_left.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
            tv_sub_left.setTextSize(14);

            subLayout1.addView(tv_sub_left, childParams);
        } else {
            //第二个TextView
            TextView tv_sub_mid = new TextView(getContext());
            tv_sub_mid.setGravity(Gravity.CENTER);
            tv_sub_mid.setClickable(true);
            tv_sub_mid.setText(houseStyles.get(position).getLabel());
            tv_sub_mid.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
            tv_sub_mid.setTextSize(13);

            subLayout2.addView(tv_sub_mid, childParams);
        }
        this.addView(parentLayout);
    }
}
