package com.it.tujia.db;

import com.lidroid.xutils.db.annotation.Column;
import com.lidroid.xutils.db.annotation.Id;

/**
 * Created by kkguo on 2015/11/19.
 */
public class DbCity {

/*    private int id;
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
    private String pinyin;*/
    @Id
    private int id;

    @Column
    private String cityName;

    @Column
    private String firstLetter;

    @Column
    private int cityId;

    @Column
    private double longitude;

    @Column
    private double latitude;

    @Column
    private String pictureURL;

    @Column
    private String pinyin;

    @Column
    private String shortPinyin;

    @Column
    private boolean isDefault;

    @Column
    private int sweetomeUnitCount;
    @Column
    private int externalID;

    @Column
    private boolean isHot;

    @Column
    private boolean isHotInApp;

    @Column
    private int searchType;

    public boolean isHot() {
        return isHot;
    }

    public void setIsHot(boolean isHot) {
        this.isHot = isHot;
    }

    public boolean isHotInApp() {
        return isHotInApp;
    }

    public void setIsHotInApp(boolean isHotInApp) {
        this.isHotInApp = isHotInApp;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getExternalID() {
        return externalID;
    }

    public void setExternalID(int externalID) {
        this.externalID = externalID;
    }

    public int getSweetomeUnitCount() {
        return sweetomeUnitCount;
    }

    public void setSweetomeUnitCount(int sweetomeUnitCount) {
        this.sweetomeUnitCount = sweetomeUnitCount;
    }

    public String getPinyin() {
        return pinyin;
    }

    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }

    public String getPictureURL() {
        return pictureURL;
    }

    public void setPictureURL(String pictureURL) {
        this.pictureURL = pictureURL;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getFirstLetter() {
        return firstLetter;
    }

    public void setFirstLetter(String firstLetter) {
        this.firstLetter = firstLetter;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public boolean isDefault() {
        return isDefault;
    }

    public void setIsDefault(boolean isDefault) {
        this.isDefault = isDefault;
    }

    public String getShortPinyin() {
        return shortPinyin;
    }

    public void setShortPinyin(String shortPinyin) {
        this.shortPinyin = shortPinyin;
    }

    public int getSearchType() {
        return searchType;
    }

    public void setSearchType(int searchType) {
        this.searchType = searchType;
    }
}
