package com.it.tujia.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ListView;

/**
 * Created by Administrator on 2015/11/15.
 */
public class TuJiaDiscoverDetailListView extends ListView {

    public TuJiaDiscoverDetailListView(Context context) {
        super(context);
    }

    public TuJiaDiscoverDetailListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int heightSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE>>2, MeasureSpec.AT_MOST);

        super.onMeasure(widthMeasureSpec, heightSpec);

        //addFooterView();
    }
}
