package com.it.tujia.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by kkguo on 2015/11/18.
 */
public class InIsland implements Serializable{
    private int  cityCount;
    private List<City> list;

    public int getCityCount() {
        return cityCount;
    }

    public void setCityCount(int cityCount) {
        this.cityCount = cityCount;
    }

    public List<City> getList() {
        return list;
    }

    public void setList(List<City> list) {
        this.list = list;
    }
}
