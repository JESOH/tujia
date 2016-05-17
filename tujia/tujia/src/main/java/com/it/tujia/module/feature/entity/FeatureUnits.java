package com.it.tujia.module.feature.entity;

import java.util.List;

/**
 * Created by JunShen
 */
public class FeatureUnits {
    private int cityId;
    private String cityName;
    private Object cityPinyin;
    private String defaultPicture;
    private Object description;
    private String highlight;
    private String housetype;
    private boolean isFavorite;
    private int price;
    private int unitId;
    private String unitName;
    private List<String> pictures;

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public void setCityPinyin(Object cityPinyin) {
        this.cityPinyin = cityPinyin;
    }

    public void setDefaultPicture(String defaultPicture) {
        this.defaultPicture = defaultPicture;
    }

    public void setDescription(Object description) {
        this.description = description;
    }

    public void setHighlight(String highlight) {
        this.highlight = highlight;
    }

    public void setHousetype(String housetype) {
        this.housetype = housetype;
    }

    public void setIsFavorite(boolean isFavorite) {
        this.isFavorite = isFavorite;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setUnitId(int unitId) {
        this.unitId = unitId;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public void setPictures(List<String> pictures) {
        this.pictures = pictures;
    }

    public int getCityId() {
        return cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public Object getCityPinyin() {
        return cityPinyin;
    }

    public String getDefaultPicture() {
        return defaultPicture;
    }

    public Object getDescription() {
        return description;
    }

    public String getHighlight() {
        return highlight;
    }

    public String getHousetype() {
        return housetype;
    }

    public boolean isIsFavorite() {
        return isFavorite;
    }

    public int getPrice() {
        return price;
    }

    public int getUnitId() {
        return unitId;
    }

    public String getUnitName() {
        return unitName;
    }

    public List<String> getPictures() {
        return pictures;
    }
}
