package com.it.tujia.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.it.tujia.R;

/**
 * Created by kkguo on 2015/11/17.
 */
public class SearchDate extends RelativeLayout {
    public SearchDate(Context context) {
        super(context);
        init(context, null);
    }

    public SearchDate(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    TextView checkInDate;
    TextView checkInWeek;
    TextView checkOutDate;
    TextView checkOutWeek;
    private void init(Context context, AttributeSet attrs) {
        LayoutInflater.from(context).inflate(R.layout.frag_search_date_item, this);
         TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.SearchDate);
        checkInDate = (TextView) findViewById(R.id.checkin__frag_search_item);
        String checkInDates =
                array.getString(R.styleable.SearchDate_checkin_date);
        checkInDate.setText(checkInDates);
        checkInWeek = (TextView) findViewById(R.id.checkin_week__frag_search_item);
        String checkInWeeks =
                array.getString(R.styleable.SearchDate_checkin_week);
        checkInWeek.setText(checkInWeeks);
        checkOutDate = (TextView) findViewById(R.id.checkout_frag_search_item);
        String checkOutDates =
                array.getString(R.styleable.SearchDate_checkout_date);
        checkOutDate.setText(checkOutDates);
        checkOutWeek = (TextView) findViewById(R.id.checkout_week__frag_search_item);
        String checkOutWeeks =
                array.getString(R.styleable.SearchDate_checkout_week);
        checkOutWeek.setText(checkOutWeeks);
    }

    public void  setdata(String checkInDate,String checkInWeek,String checkOutDate,String checkOutWeek){
        this.checkInDate.setText(checkInDate);
        this.checkInWeek.setText(checkInWeek);
        this.checkOutDate.setText(checkOutDate);
        this.checkOutWeek.setText(checkOutWeek);
    }
    public void  setCheckInDate(String checkInDate){
        this.checkInDate.setText(checkInDate);
    };
    public void  setCheckInWeek(String checkInWeek){
        this.checkInWeek.setText(checkInWeek);
    };
    public void  setCheckOutDate(String checkOutDate){
        this.checkOutDate.setText(checkOutDate);
    };
    public void  setCheckOutWeek(String checkOutWeek){
        this.checkOutWeek.setText(checkOutWeek);
    };
}
