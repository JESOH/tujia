package com.it.tujia.module.feature.view;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.it.tujia.R;
import com.it.tujia.application.App;
import com.it.tujia.module.detail.activity.TuJiaDetailActivity;
import com.it.tujia.module.feature.entity.FeatureUnits;
import com.makeramen.roundedimageview.RoundedImageView;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

/**
 * Item
 *
 * Created by JunShen
 */
public class HouseUnitsView extends LinearLayout {


    ImageLoader mImageLoader;
    DisplayImageOptions options;

    private RoundedImageView unit_picture;

    private ImageView unit_favorite;

    private TextView unit_price;

    private TextView unit_unitName;

    private TextView unit_houseType;

    public HouseUnitsView(Context context, FeatureUnits featureUnits) {
        super(context, null);
        init(context, featureUnits);
    }

    public HouseUnitsView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    private void init(final Context context, final FeatureUnits units) {

        mImageLoader = App.getApp().getImagerLoader();
        options = App.getApp().getOptions();

        LayoutInflater inflater = LayoutInflater.from(context);

        inflater.inflate(R.layout.frag_feature_unit_item, this);

        unit_picture = (RoundedImageView) findViewById(R.id.iv_feature_unit_picture);
        unit_picture.setCornerRadius(5);
        unit_picture.setScaleType(ImageView.ScaleType.CENTER);

        unit_favorite = (ImageView) findViewById(R.id.iv_feature_unit_picture);

        unit_price = (TextView) findViewById(R.id.tv_feature_unit_price);

        unit_unitName = (TextView) findViewById(R.id.tv_feature_unit_unitName);

        unit_houseType = (TextView) findViewById(R.id.tv_feature_unit_houseType);


        mImageLoader.displayImage(units.getDefaultPicture(), unit_picture, options);

        unit_price.setText("￥" + units.getPrice());

        unit_unitName.setText(units.getUnitName());

        unit_houseType.setText(units.getHousetype());

        unit_picture.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), TuJiaDetailActivity.class);
                intent.putExtra("unitID",units.getUnitId());
                getContext().startActivity(intent);
            }
        });

    }

}
