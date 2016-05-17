package com.it.tujia.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by kkguo on 2015/11/16.
 */
public class Item implements Serializable{

    /**
     * allowBooking : true
     * hasPromotion : false
     * productFeature : 0
     * displayPrice : 438
     * finalPrice : 438
     * productID : 42110
     * roomCountSummary : 2室1厅2卫
     * distance : 0
     * grossArea : 110
     * businessArea :
     * commentScore : 4.6
     * isFavorite : false
     * distanceDescription : null
     * neareastSubwayStationName : null
     * isSweetomeHotel : false
     * longitude : 109.493015
     * district : 三亚湾
     * unitID : 9812
     * houseTypeName : 公寓
     * hasPickUpTrain : false
     * hasBreakfast : false
     * enumUnitFeatureForShow : 371
     * memberCashback : 0
     * soldNightCount : 158
     * memberReduce : 0
     * hasGift : false
     * defaultPictureURL : /upload/landlordunit/day_131121/201311211152598666.jpg
     * businessType : 2
     * pictureList : []
     * commentRecommendCount : 23
     * commentCount : 26
     * reduce : 0
     * returnType : 0
     * unitRating : 3
     * specialReturnCashRate : null
     * productCount : 1
     * supportFullPrepay : true
     * unitName : 三亚海风铃绿海田园海景两房
     * hasPickUpAir : false
     * returnAmount : 0
     * recommendedGuests : 4
     * unitInstanceCount : 2
     * cashback : 0
     * latitude : 18.273463
     * productPrice : 438
     * bedCount : 2
     * lowestPrice : 438
     */

    private boolean allowBooking;
    private boolean hasPromotion;
    private int productFeature;
    private int displayPrice;
    private int finalPrice;
    private int productID;
    private String roomCountSummary;
    private int distance;
    private int grossArea;
    private String businessArea;
    private double commentScore;
    private boolean isFavorite;
    private Object distanceDescription;
    private Object neareastSubwayStationName;
    private boolean isSweetomeHotel;
    private double longitude;
    private String district;
    private int unitID;
    private String houseTypeName;
    private boolean hasPickUpTrain;
    private boolean hasBreakfast;
    private int enumUnitFeatureForShow;
    private int memberCashback;
    private int soldNightCount;
    private int memberReduce;
    private boolean hasGift;
    private String defaultPictureURL;
    private int businessType;
    private int commentRecommendCount;
    private int commentCount;
    private int reduce;
    private int returnType;
    private int unitRating;
    private Object specialReturnCashRate;
    private int productCount;
    private boolean supportFullPrepay;
    private String unitName;
    private boolean hasPickUpAir;
    private int returnAmount;
    private int recommendedGuests;
    private int unitInstanceCount;
    private int cashback;
    private double latitude;
    private int productPrice;
    private int bedCount;
    private int lowestPrice;
    private List<?> pictureList;

    public void setAllowBooking(boolean allowBooking) {
        this.allowBooking = allowBooking;
    }

    public void setHasPromotion(boolean hasPromotion) {
        this.hasPromotion = hasPromotion;
    }

    public void setProductFeature(int productFeature) {
        this.productFeature = productFeature;
    }

    public void setDisplayPrice(int displayPrice) {
        this.displayPrice = displayPrice;
    }

    public void setFinalPrice(int finalPrice) {
        this.finalPrice = finalPrice;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public void setRoomCountSummary(String roomCountSummary) {
        this.roomCountSummary = roomCountSummary;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public void setGrossArea(int grossArea) {
        this.grossArea = grossArea;
    }

    public void setBusinessArea(String businessArea) {
        this.businessArea = businessArea;
    }

    public void setCommentScore(double commentScore) {
        this.commentScore = commentScore;
    }

    public void setIsFavorite(boolean isFavorite) {
        this.isFavorite = isFavorite;
    }

    public void setDistanceDescription(Object distanceDescription) {
        this.distanceDescription = distanceDescription;
    }

    public void setNeareastSubwayStationName(Object neareastSubwayStationName) {
        this.neareastSubwayStationName = neareastSubwayStationName;
    }

    public void setIsSweetomeHotel(boolean isSweetomeHotel) {
        this.isSweetomeHotel = isSweetomeHotel;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public void setUnitID(int unitID) {
        this.unitID = unitID;
    }

    public void setHouseTypeName(String houseTypeName) {
        this.houseTypeName = houseTypeName;
    }

    public void setHasPickUpTrain(boolean hasPickUpTrain) {
        this.hasPickUpTrain = hasPickUpTrain;
    }

    public void setHasBreakfast(boolean hasBreakfast) {
        this.hasBreakfast = hasBreakfast;
    }

    public void setEnumUnitFeatureForShow(int enumUnitFeatureForShow) {
        this.enumUnitFeatureForShow = enumUnitFeatureForShow;
    }

    public void setMemberCashback(int memberCashback) {
        this.memberCashback = memberCashback;
    }

    public void setSoldNightCount(int soldNightCount) {
        this.soldNightCount = soldNightCount;
    }

    public void setMemberReduce(int memberReduce) {
        this.memberReduce = memberReduce;
    }

    public void setHasGift(boolean hasGift) {
        this.hasGift = hasGift;
    }

    public void setDefaultPictureURL(String defaultPictureURL) {
        this.defaultPictureURL = defaultPictureURL;
    }

    public void setBusinessType(int businessType) {
        this.businessType = businessType;
    }

    public void setCommentRecommendCount(int commentRecommendCount) {
        this.commentRecommendCount = commentRecommendCount;
    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }

    public void setReduce(int reduce) {
        this.reduce = reduce;
    }

    public void setReturnType(int returnType) {
        this.returnType = returnType;
    }

    public void setUnitRating(int unitRating) {
        this.unitRating = unitRating;
    }

    public void setSpecialReturnCashRate(Object specialReturnCashRate) {
        this.specialReturnCashRate = specialReturnCashRate;
    }

    public void setProductCount(int productCount) {
        this.productCount = productCount;
    }

    public void setSupportFullPrepay(boolean supportFullPrepay) {
        this.supportFullPrepay = supportFullPrepay;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public void setHasPickUpAir(boolean hasPickUpAir) {
        this.hasPickUpAir = hasPickUpAir;
    }

    public void setReturnAmount(int returnAmount) {
        this.returnAmount = returnAmount;
    }

    public void setRecommendedGuests(int recommendedGuests) {
        this.recommendedGuests = recommendedGuests;
    }

    public void setUnitInstanceCount(int unitInstanceCount) {
        this.unitInstanceCount = unitInstanceCount;
    }

    public void setCashback(int cashback) {
        this.cashback = cashback;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setProductPrice(int productPrice) {
        this.productPrice = productPrice;
    }

    public void setBedCount(int bedCount) {
        this.bedCount = bedCount;
    }

    public void setLowestPrice(int lowestPrice) {
        this.lowestPrice = lowestPrice;
    }

    public void setPictureList(List<?> pictureList) {
        this.pictureList = pictureList;
    }

    public boolean isAllowBooking() {
        return allowBooking;
    }

    public boolean isHasPromotion() {
        return hasPromotion;
    }

    public int getProductFeature() {
        return productFeature;
    }

    public int getDisplayPrice() {
        return displayPrice;
    }

    public int getFinalPrice() {
        return finalPrice;
    }

    public int getProductID() {
        return productID;
    }

    public String getRoomCountSummary() {
        return roomCountSummary;
    }

    public int getDistance() {
        return distance;
    }

    public int getGrossArea() {
        return grossArea;
    }

    public String getBusinessArea() {
        return businessArea;
    }

    public double getCommentScore() {
        return commentScore;
    }

    public boolean isIsFavorite() {
        return isFavorite;
    }

    public Object getDistanceDescription() {
        return distanceDescription;
    }

    public Object getNeareastSubwayStationName() {
        return neareastSubwayStationName;
    }

    public boolean isIsSweetomeHotel() {
        return isSweetomeHotel;
    }

    public double getLongitude() {
        return longitude;
    }

    public String getDistrict() {
        return district;
    }

    public int getUnitID() {
        return unitID;
    }

    public String getHouseTypeName() {
        return houseTypeName;
    }

    public boolean isHasPickUpTrain() {
        return hasPickUpTrain;
    }

    public boolean isHasBreakfast() {
        return hasBreakfast;
    }

    public int getEnumUnitFeatureForShow() {
        return enumUnitFeatureForShow;
    }

    public int getMemberCashback() {
        return memberCashback;
    }

    public int getSoldNightCount() {
        return soldNightCount;
    }

    public int getMemberReduce() {
        return memberReduce;
    }

    public boolean isHasGift() {
        return hasGift;
    }

    public String getDefaultPictureURL() {
        return defaultPictureURL;
    }

    public int getBusinessType() {
        return businessType;
    }

    public int getCommentRecommendCount() {
        return commentRecommendCount;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public int getReduce() {
        return reduce;
    }

    public int getReturnType() {
        return returnType;
    }

    public int getUnitRating() {
        return unitRating;
    }

    public Object getSpecialReturnCashRate() {
        return specialReturnCashRate;
    }

    public int getProductCount() {
        return productCount;
    }

    public boolean isSupportFullPrepay() {
        return supportFullPrepay;
    }

    public String getUnitName() {
        return unitName;
    }

    public boolean isHasPickUpAir() {
        return hasPickUpAir;
    }

    public int getReturnAmount() {
        return returnAmount;
    }

    public int getRecommendedGuests() {
        return recommendedGuests;
    }

    public int getUnitInstanceCount() {
        return unitInstanceCount;
    }

    public int getCashback() {
        return cashback;
    }

    public double getLatitude() {
        return latitude;
    }

    public int getProductPrice() {
        return productPrice;
    }

    public int getBedCount() {
        return bedCount;
    }

    public int getLowestPrice() {
        return lowestPrice;
    }

    public List<?> getPictureList() {
        return pictureList;
    }
}
