package com.it.tujia.module.feature.entity;

/**
 * Created by JunShen
 */
public class FeatureCategories {

    private boolean isSelected;
    private String label;
    private int value;

    public void setIsSelected(boolean isSelected) {
        this.isSelected = isSelected;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public void setValue(int value) {
        this.value = value;
    }


    public boolean isIsSelected() {
        return isSelected;
    }

    public String getLabel() {
        return label;
    }

    public int getValue() {
        return value;
    }

}
