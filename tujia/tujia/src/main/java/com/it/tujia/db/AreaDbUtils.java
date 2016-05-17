package com.it.tujia.db;

import android.util.Log;

import com.it.tujia.application.App;
import com.it.tujia.entity.City;
import com.lidroid.xutils.DbUtils;
import com.lidroid.xutils.db.sqlite.Selector;
import com.lidroid.xutils.db.sqlite.WhereBuilder;
import com.lidroid.xutils.exception.DbException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kkguo on 2015/11/19.
 */
public class AreaDbUtils {
  private    static AreaDbUtils myDbUtils=new AreaDbUtils();
  private DbUtils dbUtils;
    private AreaDbUtils(){
       dbUtils= App.getApp().getDbUtils();
    }

    public static AreaDbUtils getInstance(){
        return myDbUtils;
    }

  //开始各类方法，

  public  void insertAreaData(List<City>list,int searchType){

    List<DbCity>cities=new ArrayList<>();
    DbCity city=null;
    for (int i=0;i<list.size();i++){
        city=new DbCity();
        city.setCityId(list.get(i).getId());
        city.setCityName(list.get(i).getName());
      city.setFirstLetter(String.valueOf(list.get(i).getPinyin().charAt(0)));
      city.setCityName(list.get(i).getName());
      city.setLongitude(list.get(i).getLongitude());
      city.setLatitude(list.get(i).getLatitude());
      city.setShortPinyin(list.get(i).getShortPinyin());
      city.setPictureURL(list.get(i).getPictureURL());
      city.setPinyin(list.get(i).getPinyin());
      city.setExternalID(list.get(i).getExternalID());
      city.setIsDefault(list.get(i).isIsDefault());
      city.setIsHot(list.get(i).isIsHot());
      city.setIsHotInApp(list.get(i).isIsHotInApp());
      city.setSweetomeUnitCount(list.get(i).getSweetomeUnitCount());
      city.setSearchType(searchType);
      cities.add(city);
    }
    try {
      dbUtils.saveAll(cities);
      Log.e("cities",cities.size()+"");
    } catch (DbException e) {
      e.printStackTrace();
    }
  }


  public  List<DbCity> queryAllAreaData(int SearchType){
    try {
      List<DbCity>list=dbUtils.findAll(Selector.from(DbCity.class).where("searchType", "=", SearchType).orderBy("firstLetter"));
      return  list;
    } catch (DbException e) {
      e.printStackTrace();
    }
    return null;
  }

  public List<DbCity> queryHotCity(int searchType){

  /*  where("id" ,"<", 54)
            .and(WhereBuilder.b("age", ">", 20).or("age", " < ", 30))*/
    ;
    try {
       List<DbCity> all = dbUtils.findAll(Selector.from(DbCity.class).where("searchType", "=", searchType)
              .and(WhereBuilder.b("isHot", "=", true)));
      return all;
    } catch (DbException e) {
      e.printStackTrace();
    }
    return null;
  }

  public List<DbCity> querySearch(int searchType,String word){

  /*  where("id" ,"<", 54)
            .and(WhereBuilder.b("age", ">", 20).or("age", " < ", 30))*/
    ;
    try {
      /*SELECT * FROM Persons
      WHERE City LIKE 'N%'*/
      List<DbCity> all = dbUtils.findAll(Selector.from(DbCity.class).where("searchType", "=", searchType)
              .and(WhereBuilder.b("cityName", "like", word+"%")));
      return all;
    } catch (DbException e) {
      e.printStackTrace();
    }
    return null;
  }
}
