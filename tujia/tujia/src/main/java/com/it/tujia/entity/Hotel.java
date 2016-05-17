package com.it.tujia.entity;

/**
 * Created by kkguo on 2015/11/14.
 */
public class Hotel {

    public int refType;
    public int refId;
    public String unitName;
    public int commentCount;
    public boolean hasPromotion;
    public int commentScore;
    public String pic;
    public int displayPrice;
    public int unitID;
    public Item item;

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Hotel() {

    }

    public int getUnitID() {
        return unitID;
    }

    public void setUnitID(int unitID) {
        this.unitID = unitID;
    }

    public int getDisplayPrice() {
        return displayPrice;
    }

    public void setDisplayPrice(int displayPrice) {
        this.displayPrice = displayPrice;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public int getCommentScore() {
        return commentScore;
    }

    public void setCommentScore(int commentScore) {
        this.commentScore = commentScore;
    }

    public boolean isHasPromotion() {
        return hasPromotion;
    }

    public void setHasPromotion(boolean hasPromotion) {
        this.hasPromotion = hasPromotion;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public int getRefId() {
        return refId;
    }

    public void setRefId(int refId) {
        this.refId = refId;
    }

    public int getRefType() {
        return refType;
    }

    public void setRefType(int refType) {
        this.refType = refType;
    }

    @Override
    public String toString() {
        return "Hotel{" +
                "refType=" + refType +
                ", refId=" + refId +
                ", unitName='" + unitName + '\'' +
                ", commentCount=" + commentCount +
                ", hasPromotion=" + hasPromotion +
                ", commentScore=" + commentScore +
                ", pic='" + pic + '\'' +
                ", displayPrice=" + displayPrice +
                ", unitID=" + unitID +
                '}';
    }
}

