package com.it.tujia.utils;

import android.util.Log;

import com.google.gson.Gson;
import com.it.tujia.application.App;
import com.it.tujia.constant.ConstantType;
import com.it.tujia.entity.AllConditions;
import com.it.tujia.entity.City;
import com.it.tujia.entity.CityHotelItems;
import com.it.tujia.entity.HomeTitleVP;
import com.it.tujia.entity.HotCity;
import com.it.tujia.entity.Hotel;
import com.it.tujia.entity.InIsland;
import com.it.tujia.entity.Item;
import com.it.tujia.entity.SubGroups;
import com.it.tujia.entity.Theme;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kkguo on 2015/11/14.
 */
public class JsonUtils {

    public static  List<Theme> getThemeLists(String response){
        {
            try {
                JSONObject jsonObject = new JSONObject(response);

                JSONObject content = jsonObject.getJSONObject("content");
                JSONArray list = content.getJSONArray("list");

                //要一个主题名字以及图片
                List<Theme> themeLists=new ArrayList<>();
                Hotel hotel=null;
              //外面正一个，
                    JSONObject object = list.getJSONObject(0);
                    String conl = object.getString("con");
                    JSONArray con = new JSONArray(conl);//真正包含主题信息以及个数，但是目前只有一个


                    for (int j = 0; j < con.length(); j++) {

                        Theme themeData=new Theme();  //主题/有setGroupName，setGroupId，setDisplayType，lis<hotel>
                        JSONObject theme = con.getJSONObject(j);
                        themeData.setGroupName(theme.getString("groupName"));//解析theme名字，也是title//精选主题
                        themeData.setGroupId(theme.getInt("groupId"));
                        themeData.setDisplayType(theme.getInt("displayType"));
                        JSONArray themeList = theme.getJSONArray("list");//每个主题都有一系列的酒店信息，得到信息数组
                        List<Hotel>hotelList=new ArrayList<>();
                        for (int a = 0; a < themeList.length(); a++) { //
                            //list里面的一个item数据，一个酒店
                            //hotel=new Hotel();  //hotel 里面有
                            hotel=new Hotel();
                            JSONObject listItem = themeList.getJSONObject(a);

                            int refType = listItem.getInt("refType");
                            hotel.setRefId(listItem.getInt("refId"));
                            hotel.setRefType(refType);
                            hotel.setPic(listItem.getString("pic"));


                            //unitName，commentCount,,hasPromotion,特价，commentScore  发现公寓，refType: 1
                            if (refType == 2) {
                                //这个是一个对象，//什么都不做只是纯粹展示图片

                            } else if (refType == 1) {
                                JSONObject item = listItem.getJSONObject("item");
                                String string = item.toString();
                               Item items = App.getApp().getGson().fromJson(string, Item.class);
                                hotel.setUnitName(item.getString("unitName"));
                                hotel.setCommentCount(item.getInt("commentCount"));
                                hotel.setCommentScore(item.getInt("commentScore"));
                                hotel.setHasPromotion(item.getBoolean("hasPromotion"));
                                hotel.setUnitID(item.getInt("unitID"));
                                hotel.setDisplayPrice(item.getInt("displayPrice"));
                                hotel.setItem(items);

                            }
                            hotelList.add(hotel);
                        }
                        themeData.setList(hotelList);

                        //在这里增加主题信息
                        themeLists.add(themeData);
                    }

                return  themeLists;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return  null;
    }



    public static  List<HomeTitleVP> getBannerLists(String response){
        {
            try {
                JSONObject jsonObject = new JSONObject(response);

                JSONObject content = jsonObject.getJSONObject("content");
                JSONArray list = content.getJSONArray("list");
                //外面正一个，
                JSONObject object = list.getJSONObject(0);
                String conl = object.getString("con");
                JSONObject con = new JSONObject(conl);//真正包含主题信息以及个数，但是目前只有一个
                 JSONArray jsonArray = con.getJSONArray("list");
                List<HomeTitleVP>result=new ArrayList<>();
                for (int i=0;i<jsonArray.length();i++){
                     JSONObject item =  jsonArray.getJSONObject(i);
                     String msg = item.toString();
                    Gson gson=App.getApp().getGson();
                     HomeTitleVP json = gson.fromJson(msg, HomeTitleVP.class);
                    result.add(json);
                }
                return  result;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return  null;
    }


    public static  List<HotCity> getHotCityLists(String response){
        {
            try {
                JSONObject jsonObject = new JSONObject(response);

                JSONObject content = jsonObject.getJSONObject("content");
                JSONArray list = content.getJSONArray("list");
                //外面正一个，
                JSONObject object = list.getJSONObject(0);
                String conl = object.getString("con");
                JSONObject con = new JSONObject(conl);//真正包含主题信息以及个数，但是目前只有一个
                JSONArray jsonArray = con.getJSONArray("list");
                List<HotCity>result=new ArrayList<>();
                JSONObject item;
                String msg;
                HotCity json;
                Gson gson=App.getApp().getGson();
                for (int i=0;i<jsonArray.length();i++){
                    item = jsonArray.getJSONObject(i);
                    msg = item.toString();
                    json = gson.fromJson(msg, HotCity.class);
                    result.add(json);
                }
                return  result;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return  null;
    }

    public static  InIsland getCityLists(String response){
        {
            try {
                InIsland is=new InIsland();
                JSONObject jsonObject = new JSONObject(response);

                JSONObject content = jsonObject.getJSONObject("content");
                JSONArray list = content.getJSONArray("list");
                //外面正一个，
                JSONObject object = list.getJSONObject(0);
                String conl = object.getString("con");
                JSONObject con = new JSONObject(conl);//真正包含主题信息以及个数，但是目前只有一个
                 int count = con.getInt("count");
                is.setCityCount(count);
                JSONArray jsonArray = con.getJSONArray("list");
                List<City>result=new ArrayList<>();
                JSONObject item;
                String msg;
                Gson gson=App.getApp().getGson();
                for (int i=0;i<jsonArray.length();i++){
                    item = jsonArray.getJSONObject(i);
                    msg = item.toString();
                    City json = gson.fromJson(msg, City.class);
                    result.add(json);
                }
                is.setList(result);
                return  is;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return  null;
    }

    public  static List<AllConditions> getSelect(String responce){
        try {
            JSONObject object=new JSONObject(responce);
             JSONObject content = object.getJSONObject("content");
             JSONArray allConditions = content.getJSONArray("allConditions");
            JSONObject jsonObject=null;
            CityHotelItems hotelItems=null;
            SubGroups groups=null;
            List<AllConditions> all=new ArrayList<>();
            AllConditions conditions=null;
            for (int i=0;i<allConditions.length();i++){
                    conditions=new AllConditions();
                 jsonObject = allConditions.getJSONObject(i);
                int gType=jsonObject.getInt("gType");
                conditions.setgType(gType);
                conditions.setsType(jsonObject.getInt("sType"));
                conditions.setLabel(jsonObject.getString("label"));
                if(ConstantType.CITY_HOTEL_SORT==gType||ConstantType.CITY_HOTEL_PRICE==gType){
                     JSONArray firstItems = jsonObject.getJSONArray("items");
                    List<CityHotelItems> firstList=new ArrayList<>();
                    for (int f=0;f<firstItems.length();f++){
                        hotelItems=new CityHotelItems();
                        JSONObject last = firstItems.getJSONObject(f);
                        hotelItems.setGType(last.getInt("gType"));
                        hotelItems.setIsNew(last.getBoolean("isNew"));
                        hotelItems.setIsSelected(last.getBoolean("isSelected"));
                        hotelItems.setLabel(last.getString("label"));
                        hotelItems.setType(last.getInt("type"));
                        hotelItems.setValue(last.getString("value"));
                        firstList.add(hotelItems);
                    }
                   conditions.setItems(firstList);
                }

                List<SubGroups>  subList=new ArrayList<>();
                JSONArray subGroups = jsonObject.getJSONArray("subGroups");
                for (int j=0;j<subGroups.length();j++){
                    groups=new SubGroups();
                     JSONObject sub = subGroups.getJSONObject(j);
                     JSONArray items = sub.getJSONArray("items");
                   List<CityHotelItems> itemList=new ArrayList<>();
                    //要进行判断是否为0，为0就进行获取subGroups;
                    if(items.length()==0){
                        getOther(sub);
                    }

                    groups.setgType(sub.getInt("gType"));
                    groups.setsType(sub.getInt("sType"));
                    groups.setLabel(sub.getString("label"));
                    for (int a=0;a<items.length();a++){
                        hotelItems=new CityHotelItems();
                         JSONObject last = items.getJSONObject(a);
                        hotelItems.setGType(last.getInt("gType"));
                       hotelItems.setIsNew(last.getBoolean("isNew"));
                       hotelItems.setIsSelected(last.getBoolean("isSelected"));
                       hotelItems.setLabel(last.getString("label"));
                      hotelItems.setType(last.getInt("type"));
                      hotelItems.setValue(last.getString("value"));
                        itemList.add(hotelItems);
                    }
                    groups.setList(itemList);
                    subList.add(groups);
                }
                conditions.setList(subList);
                all.add(conditions);
            }
           return all;
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static void getOther(JSONObject sub) throws JSONException {
            CityHotelItems otherItem=null;

            JSONArray subJSONArray = sub.getJSONArray("subGroups");
            for (int c=0;c<subJSONArray.length();c++){
                JSONObject other = subJSONArray.getJSONObject(c);
                JSONArray otherItems = other.getJSONArray("items");
                List<CityHotelItems>otherList=new ArrayList<>();
                for (int d=0;d<otherItems.length();d++){
                    JSONObject obj = otherItems.getJSONObject(d);
                    otherItem=new CityHotelItems();
                    otherItem.setGType(obj.getInt("gType"));
                    otherItem.setIsNew(obj.getBoolean("isNew"));
                    otherItem.setIsSelected(obj.getBoolean("isSelected"));
                    otherItem.setLabel(obj.getString("label"));
                    otherItem.setType(obj.getInt("type"));
                    otherItem.setValue(obj.getString("value"));
                    otherList.add(otherItem);
                }
                //setlist
            }
        }
    }

