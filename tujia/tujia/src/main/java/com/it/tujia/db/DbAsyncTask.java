package com.it.tujia.db;

import android.os.AsyncTask;

import com.it.tujia.constant.ConstantType;
import com.it.tujia.entity.City;

import java.util.List;

import de.greenrobot.event.EventBus;

/**
 * Created by JunShen on 2015/11/25.
 */
public class DbAsyncTask {

    public static boolean newInstance(List<City> list,int tag){

        switch (tag){
            case  ConstantType.MESTIC:
                new MesticAsyncTask().execute(list);
                break;
            case ConstantType.OVERSEA:
                new OverseaAsyncTask().execute(list);
                break;
        }
        return true;
    }


    private static class MesticAsyncTask extends AsyncTask<List<City>, Void, Void> {
        @Override
        protected Void doInBackground(List<City>... params) {

            List<City> list = params[0];
            MyDbUtils myDbUtils = MyDbUtils.getInstance();
            myDbUtils.insertCityData(list, ConstantType.MESTIC);
            EventBus.getDefault().post(params);
            return null;
        }
    }
    private static class OverseaAsyncTask extends AsyncTask<List<City>, Void, Void> {
        @Override
        protected Void doInBackground(List<City>... params) {

            List<City> list = params[0];
            MyDbUtils myDbUtils = MyDbUtils.getInstance();
            myDbUtils.insertCityData(list, ConstantType.OVERSEA);
            EventBus.getDefault().post(params);
            return null;
        }
    }
}
