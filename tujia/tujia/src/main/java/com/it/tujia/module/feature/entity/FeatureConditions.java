package com.it.tujia.module.feature.entity;

import java.util.List;

/**
 * Created by JunShen
 */
public class FeatureConditions {

    private List<HouseStyle> houseStyles;
    private List<City> cityList;

    public void setHouseStyles(List<HouseStyle> houseStyles) {
        this.houseStyles = houseStyles;
    }

    public void setCityList(List<City> cityList) {
        this.cityList = cityList;
    }


    public List<HouseStyle> getHouseStyles() {
        return houseStyles;
    }

    public List<City> getCityList() {
        return cityList;
    }


    public static class HouseStyle {
        private boolean isSelected;
        private String label;
        private Object param;
        private String value;

        public void setIsSelected(boolean isSelected) {
            this.isSelected = isSelected;
        }

        public void setLabel(String label) {
            this.label = label;
        }

        public void setParam(Object param) {
            this.param = param;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public boolean isIsSelected() {
            return isSelected;
        }

        public String getLabel() {
            return label;
        }

        public Object getParam() {
            return param;
        }

        public String getValue() {
            return value;
        }

    }

    public static class City {
        private boolean isSelected;
        private String label;
        private String value;

        public void setIsSelected(boolean isSelected) {
            this.isSelected = isSelected;
        }

        public void setLabel(String label) {
            this.label = label;
        }

        public void setValue(String value) {
            this.value = value;
        }


        public boolean isIsSelected() {
            return isSelected;
        }

        public String getLabel() {
            return label;
        }

        public String getValue() {
            return value;
        }

    }
}
