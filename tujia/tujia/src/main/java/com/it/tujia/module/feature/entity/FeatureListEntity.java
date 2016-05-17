package com.it.tujia.module.feature.entity;

import java.util.List;

/**
 * Created by JunShen
 */
public class FeatureListEntity {
    private int categoryId;

    private List<FeatureCategories> categories;

    private FeatureConditions conditions;

    private List<FeatureHotUnits> hotUnits;

    private List<String> subCategories;

    private List<FeatureUnits> units;


    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public void setCategories(List<FeatureCategories> categories) {
        this.categories = categories;
    }

    public void setConditions(FeatureConditions conditions) {
        this.conditions = conditions;
    }

    public void setHotUnits(List<FeatureHotUnits> hotUnits) {
        this.hotUnits = hotUnits;
    }

    public void setSubCategories(List<String> subCategories) {
        this.subCategories = subCategories;
    }

    public void setUnits(List<FeatureUnits> units) {
        this.units = units;
    }

    public int getCategoryId() {
        return categoryId;
    }


    public List<FeatureCategories> getCategories() {
        return categories;
    }

    public FeatureConditions getConditions() {
        return conditions;
    }

    public List<FeatureHotUnits> getHotUnits() {
        return hotUnits;
    }

    public List<String> getSubCategories() {
        return subCategories;
    }

    public List<FeatureUnits> getUnits() {
        return units;
    }

}
