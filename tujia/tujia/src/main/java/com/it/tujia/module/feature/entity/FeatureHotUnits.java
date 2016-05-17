package com.it.tujia.module.feature.entity;

import java.util.List;

/**
 * Created by JunShen
 */
public class FeatureHotUnits {

        private int cityId;
        private Object cityName;
        private Object cityPinyin;
        private String defaultPicture;
        private Object description;
        private Object highlight;
        private Object housetype;
        private boolean isFavorite;
        private int price;
        private int unitId;
        private String unitName;
        private List<String> pictures;

        public void setCityId(int cityId) {
            this.cityId = cityId;
        }

        public void setCityName(Object cityName) {
            this.cityName = cityName;
        }

        public void setCityPinyin(Object cityPinyin) {
            this.cityPinyin = cityPinyin;
        }

        public void setDefaultPicture(String defaultPicture) {
            this.defaultPicture = defaultPicture;
        }

        public void setDescription(Object description) {
            this.description = description;
        }

        public void setHighlight(Object highlight) {
            this.highlight = highlight;
        }

        public void setHousetype(Object housetype) {
            this.housetype = housetype;
        }

        public void setIsFavorite(boolean isFavorite) {
            this.isFavorite = isFavorite;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        public void setUnitId(int unitId) {
            this.unitId = unitId;
        }

        public void setUnitName(String unitName) {
            this.unitName = unitName;
        }

        public void setPictures(List<String> pictures) {
            this.pictures = pictures;
        }

        public int getCityId() {
            return cityId;
        }

        public Object getCityName() {
            return cityName;
        }

        public Object getCityPinyin() {
            return cityPinyin;
        }

        public String getDefaultPicture() {
            return defaultPicture;
        }

        public Object getDescription() {
            return description;
        }

        public Object getHighlight() {
            return highlight;
        }

        public Object getHousetype() {
            return housetype;
        }

        public boolean isIsFavorite() {
            return isFavorite;
        }

        public int getPrice() {
            return price;
        }

        public int getUnitId() {
            return unitId;
        }

        public String getUnitName() {
            return unitName;
        }

        public List<String> getPictures() {
            return pictures;
        }

}
