package com.it.tujia.module.feature.entity;

import java.util.List;

/**
 * Created by JunShen
 */
public class FeatureContent {
    private int count;

    private List<FeatureListEntity> list;

    public void setCount(int count) {
        this.count = count;
    }

    public void setList(List<FeatureListEntity> list) {
        this.list = list;
    }

    public int getCount() {
        return count;
    }

    public List<FeatureListEntity> getList() {
        return list;
    }
}
