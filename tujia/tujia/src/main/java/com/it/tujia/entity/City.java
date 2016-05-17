package com.it.tujia.entity;

import java.io.Serializable;

/**
 * Created by kkguo on 2015/11/18.
 */
public class City implements Serializable{
    /**
     * id : 48
     * isDefault : true
     * isHotInApp : false
     * name : 北京
     * shortPinyin : bj
     * isHot : true
     * longitude : 116.413617
     * sweetomeUnitCount : 28
     * externalID : 0
     * latitude : 39.910828
     * pictureURL : http://pic.tujia.com/upload/config/day_120927/201209270717281025.jpg
     * pinyin : beijing
     */

    private int id;
    private boolean isDefault;
    private boolean isHotInApp;
    private String name;
    private String shortPinyin;
    private boolean isHot;
    private double longitude;
    private int sweetomeUnitCount;
    private int externalID;
    private double latitude;
    private String pictureURL;
    private String pinyin;

    public void setId(int id) {
        this.id = id;
    }

    public void setIsDefault(boolean isDefault) {
        this.isDefault = isDefault;
    }

    public void setIsHotInApp(boolean isHotInApp) {
        this.isHotInApp = isHotInApp;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setShortPinyin(String shortPinyin) {
        this.shortPinyin = shortPinyin;
    }

    public void setIsHot(boolean isHot) {
        this.isHot = isHot;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public void setSweetomeUnitCount(int sweetomeUnitCount) {
        this.sweetomeUnitCount = sweetomeUnitCount;
    }

    public void setExternalID(int externalID) {
        this.externalID = externalID;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setPictureURL(String pictureURL) {
        this.pictureURL = pictureURL;
    }

    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }

    public int getId() {
        return id;
    }

    public boolean isIsDefault() {
        return isDefault;
    }

    public boolean isIsHotInApp() {
        return isHotInApp;
    }

    public String getName() {
        return name;
    }

    public String getShortPinyin() {
        return shortPinyin;
    }

    public boolean isIsHot() {
        return isHot;
    }

    public double getLongitude() {
        return longitude;
    }

    public int getSweetomeUnitCount() {
        return sweetomeUnitCount;
    }

    public int getExternalID() {
        return externalID;
    }

    public double getLatitude() {
        return latitude;
    }

    public String getPictureURL() {
        return pictureURL;
    }

    public String getPinyin() {
        return pinyin;
    }
}
