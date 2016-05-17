package com.it.tujia.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by kkguo on 2015/11/23.
 */
public class AllConditions implements Serializable{

    int gType;
    boolean isNew;
    String label;
    int sType;
    List<SubGroups> list;
    List<CityHotelItems>items;

    public List<CityHotelItems> getItems() {
        return items;
    }

    public void setItems(List<CityHotelItems> items) {
        this.items = items;
    }

    public int getgType() {
        return gType;
    }

    public void setgType(int gType) {
        this.gType = gType;
    }

    public boolean isNew() {
        return isNew;
    }

    public void setIsNew(boolean isNew) {
        this.isNew = isNew;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public int getsType() {
        return sType;
    }

    public void setsType(int sType) {
        this.sType = sType;
    }

    public List<SubGroups> getList() {
        return list;
    }

    public void setList(List<SubGroups> list) {
        this.list = list;
    }
}
