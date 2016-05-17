package com.it.tujia.module.search.acitivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.it.tujia.R;
import com.it.tujia.constant.ConstantType;
import com.squareup.timessquare.CalendarCellDecorator;
import com.squareup.timessquare.CalendarPickerView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;

import de.greenrobot.event.EventBus;

public class TimeSquare extends Activity {
    private AlertDialog theDialog;
    CalendarPickerView calendar;
    private CalendarPickerView dialogView;
    Button checkDate;
    private Date first;
    private Date last;
    int count=0;
    int searchType;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_square);
         Intent intent = getIntent();
          searchType = intent.getIntExtra("searchType", ConstantType.MESTIC);
        final Calendar nextYear = Calendar.getInstance();
        nextYear.add(Calendar.YEAR, 1);

        final Calendar lastYear = Calendar.getInstance();
        lastYear.add(Calendar.YEAR, -1);

      /*  Calendar nextYear = Calendar.getInstance();
        nextYear.add(Calendar.YEAR, 1);

        CalendarPickerView calendar = (CalendarPickerView) findViewById(R.id.calendar_view);
        Date today = new Date();
        calendar.init(today, nextYear.getTime())
                .withSelectedDate(today);*/

        //
        first=new Date();
        calendar = (CalendarPickerView) findViewById(R.id.calendar_view);
        checkDate = (Button) findViewById(R.id.check_date);
        calendar.init(lastYear.getTime(), nextYear.getTime()) //
                .inMode(CalendarPickerView.SelectionMode.RANGE) //
                .withSelectedDate(new Date());
        calendar.setOnDateSelectedListener(new CalendarPickerView.OnDateSelectedListener() {
            @Override
            public void onDateSelected(Date date) {
                if ((count % 2) == 1) {
                    first = date;
                } else {
                    last = date;
                }
                count++;
            }

            @Override
            public void onDateUnselected(Date date) {

            }
        });
/*
        Calendar today = Calendar.getInstance();
        ArrayList<Date> dates = new ArrayList<Date>();
        for (int i = 0; i < 2; i++) {
            today.add(Calendar.DAY_OF_MONTH, 3);
            dates.add(today.getTime());
        }
        calendar.setDecorators(Collections.<CalendarCellDecorator>emptyList());
        calendar.init(new Date(), nextYear.getTime()) //
                .inMode(CalendarPickerView.SelectionMode.MULTIPLE) //
                .withSelectedDates(dates);*/

        checkDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(last==null){
                    Toast.makeText(getApplicationContext(),"请选择查询开始时间-结束日期",Toast.LENGTH_LONG).show();

                }else {
                    DateSelect dateSelect=new DateSelect();
                    dateSelect.setSearchType(searchType);
                    dateSelect.setFirst(first);
                    dateSelect.setLast(last);
                    EventBus.getDefault().post(dateSelect);
                    onBackPressed();
                }

            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
