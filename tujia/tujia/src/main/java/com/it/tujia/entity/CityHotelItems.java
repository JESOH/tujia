package com.it.tujia.entity;

import java.io.Serializable;

/**
 * Created by kkguo on 2015/11/23.
 */
public class CityHotelItems implements Serializable{
    /**
     * gType : 1
     * isNew : false
     * isSelected : false
     * label : 5公里以内
     * type : 10
     * value : 5000
     */

    private int gType;
    private boolean isNew;
    private boolean isSelected;
    private String label;
    private int type;
    private String value;

    public void setGType(int gType) {
        this.gType = gType;
    }

    public void setIsNew(boolean isNew) {
        this.isNew = isNew;
    }

    public void setIsSelected(boolean isSelected) {
        this.isSelected = isSelected;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public void setType(int type) {
        this.type = type;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int getGType() {
        return gType;
    }

    public boolean isIsNew() {
        return isNew;
    }

    public boolean isIsSelected() {
        return isSelected;
    }

    public String getLabel() {
        return label;
    }

    public int getType() {
        return type;
    }

    public String getValue() {
        return value;
    }
}
