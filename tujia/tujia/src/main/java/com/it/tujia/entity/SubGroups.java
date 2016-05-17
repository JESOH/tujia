package com.it.tujia.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by kkguo on 2015/11/23.
 */
public class SubGroups implements Serializable {
    List<CityHotelItems> list;
    int gType;
    String label;
      int sType;

    public List<CityHotelItems> getList() {
        return list;
    }

    public void setList(List<CityHotelItems> list) {
        this.list = list;
    }

    public int getgType() {
        return gType;
    }

    public void setgType(int gType) {
        this.gType = gType;
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
}
