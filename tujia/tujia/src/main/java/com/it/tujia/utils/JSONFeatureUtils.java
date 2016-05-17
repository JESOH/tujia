package com.it.tujia.utils;


import com.it.tujia.module.feature.entity.FeatureCategories;
import com.it.tujia.module.feature.entity.FeatureConditions;
import com.it.tujia.module.feature.entity.FeatureHotUnits;
import com.it.tujia.module.feature.entity.FeatureUnits;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/11/17.
 */
public class JSONFeatureUtils {


    public static List<FeatureUnits> getFeatureUnits(String response) {


        try {

            JSONObject jsonObject = new JSONObject(response);

            JSONObject content = jsonObject.getJSONObject("content");
            JSONArray list = content.getJSONArray("list");

            JSONObject backObject = list.getJSONObject(0);

            JSONArray unitArray = backObject.getJSONArray("units");

            JSONObject unitsObject = null;
            FeatureUnits units = null;
            List<FeatureUnits> unitsList = new ArrayList<>();
            int i = 0;
            for (i = 0; i < unitArray.length(); i++) {

                units = new FeatureUnits();

                unitsObject = unitArray.getJSONObject(i);

                units.setCityId(unitsObject.getInt("cityId"));
                units.setCityName(unitsObject.getString("cityName"));
                units.setDefaultPicture(unitsObject.getString("defaultPicture"));
                units.setHighlight(unitsObject.getString("highlight"));
                units.setHousetype(unitsObject.getString("housetype"));
                units.setIsFavorite(unitsObject.getBoolean("isFavorite"));
                units.setPrice(unitsObject.getInt("price"));
                units.setUnitId(unitsObject.getInt("unitId"));
                units.setUnitName(unitsObject.getString("unitName"));

                unitsList.add(units);
            }
            return unitsList;

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static List<FeatureHotUnits> getFeatureHotUnitsData(String response) {
        try {

            JSONObject jsonObject = new JSONObject(response);

            JSONObject content = jsonObject.getJSONObject("content");
            JSONArray list = content.getJSONArray("list");

            JSONObject backObject = list.getJSONObject(0);

            JSONArray hotUnitsArray = backObject.getJSONArray("hotUnits");

            JSONObject hotUnitsObject = null;
            FeatureHotUnits hotUnits = null;
            List<FeatureHotUnits> hotUnitsList = new ArrayList<>();
            int i = 0;
            for (i = 0; i < hotUnitsArray.length(); i++) {

                hotUnits = new FeatureHotUnits();

                hotUnitsObject = hotUnitsArray.getJSONObject(i);

                hotUnits.setCityId(hotUnitsObject.getInt("cityId"));
                hotUnits.setDefaultPicture(hotUnitsObject.getString("defaultPicture"));
                hotUnits.setIsFavorite(hotUnitsObject.getBoolean("isFavorite"));
                hotUnits.setUnitId(hotUnitsObject.getInt("unitId"));
                hotUnits.setUnitName(hotUnitsObject.getString("unitName"));

                hotUnitsList.add(hotUnits);
            }
            return hotUnitsList;

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static List<FeatureCategories> getFeatureCategoriesData(String response) {
        try {

            JSONObject jsonObject = new JSONObject(response);

            JSONObject content = jsonObject.getJSONObject("content");
            JSONArray list = content.getJSONArray("list");

            JSONObject backObject = list.getJSONObject(0);

            JSONArray categories = backObject.getJSONArray("categories");

            JSONObject categoryObject = null;
            FeatureCategories featureCategory = null;
            List<FeatureCategories> categoriesList = new ArrayList<>();
            int i = 0;
            for (i = 0; i < categories.length(); i++) {

                featureCategory = new FeatureCategories();

                categoryObject = categories.getJSONObject(i);

                featureCategory.setIsSelected(categoryObject.getBoolean("isSelected"));
                featureCategory.setLabel(categoryObject.getString("label"));
                featureCategory.setValue(categoryObject.getInt("value"));
                categoriesList.add(featureCategory);
            }
            return categoriesList;

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static FeatureConditions getFeatureConditionsData(String response) {
        try {

            JSONObject jsonObject = new JSONObject(response);

            JSONObject content = jsonObject.getJSONObject("content");
            JSONArray list = content.getJSONArray("list");

            JSONObject backObject = list.getJSONObject(0);

            JSONArray conditionsArray = backObject.getJSONArray("conditions");


            FeatureConditions featureConditions = new FeatureConditions();

            //城市
//            JSONObject citiesObject = conditionsArray.getJSONObject(0);
//            JSONArray citiesArray = citiesObject.getJSONArray("conditions");


            //标签
            JSONObject houseStylesObject = conditionsArray.getJSONObject(1);
            JSONArray houseStylesArray = houseStylesObject.getJSONArray("conditions");

            JSONObject houseStyleObject = null;

            List<FeatureConditions.HouseStyle> houseStyleList = new ArrayList<>();

            for (int i = 0; i < houseStylesArray.length(); i++) {

                houseStyleObject = houseStylesArray.getJSONObject(i);

                FeatureConditions.HouseStyle houseStyle = new FeatureConditions.HouseStyle();

                houseStyle.setIsSelected(houseStyleObject.getBoolean("isSelected"));
                houseStyle.setLabel(houseStyleObject.getString("label"));
                houseStyle.setValue(houseStyleObject.getString("value"));

                houseStyleList.add(houseStyle);
            }

            featureConditions.setHouseStyles(houseStyleList);

            return featureConditions;

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
