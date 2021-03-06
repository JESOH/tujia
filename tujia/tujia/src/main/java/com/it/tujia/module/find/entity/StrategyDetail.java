package com.it.tujia.module.find.entity;

import java.util.List;

/**
 * Created by Administrator on 2015/11/16.
 */
public class StrategyDetail {

    private ContentEntity content;

    public void setContent(ContentEntity content) {
        this.content = content;
    }

    public ContentEntity getContent() {
        return content;
    }

    /****************************************/

    public static class ContentEntity {
        private int id;
        private int groupId;
        private String name;
        private int order;
        private boolean isActive;
        private String introduction;
        private String summary;
        private String smallPictureURL;
        private String mediumPictureURL;
        private String largePictureURL;
        private Object shareSetting;

        private List<ElementsEntity> elements;


        public void setId(int id) {
            this.id = id;
        }

        public void setGroupId(int groupId) {
            this.groupId = groupId;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setOrder(int order) {
            this.order = order;
        }

        public void setIsActive(boolean isActive) {
            this.isActive = isActive;
        }

        public void setIntroduction(String introduction) {
            this.introduction = introduction;
        }

        public void setSummary(String summary) {
            this.summary = summary;
        }

        public void setSmallPictureURL(String smallPictureURL) {
            this.smallPictureURL = smallPictureURL;
        }

        public void setMediumPictureURL(String mediumPictureURL) {
            this.mediumPictureURL = mediumPictureURL;
        }

        public void setLargePictureURL(String largePictureURL) {
            this.largePictureURL = largePictureURL;
        }

        public void setShareSetting(Object shareSetting) {
            this.shareSetting = shareSetting;
        }


        public int getId() {
            return id;
        }

        public int getGroupId() {
            return groupId;
        }

        public String getName() {
            return name;
        }

        public int getOrder() {
            return order;
        }

        public boolean isIsActive() {
            return isActive;
        }

        public String getIntroduction() {
            return introduction;
        }

        public String getSummary() {
            return summary;
        }

        public String getSmallPictureURL() {
            return smallPictureURL;
        }

        public String getMediumPictureURL() {
            return mediumPictureURL;
        }

        public String getLargePictureURL() {
            return largePictureURL;
        }

        public Object getShareSetting() {
            return shareSetting;
        }


        public void setElements(List<ElementsEntity> elements) {
            this.elements = elements;
        }

        public List<ElementsEntity> getElements() {
            return elements;
        }

        private ElementsEntity elementsEntity;
        public void setElement(ElementsEntity elementsEntity){this.elementsEntity = elementsEntity;}
        public ElementsEntity getElement() {
            return elementsEntity;
        }


        public static class ElementsEntity {

//            private ElementNodeType1 elements1;

//            private ElementNodeType2 elements2;
//
//            private ElementNodeType3 elements3;


//            public ElementNodeType1 getElements1() {
//                return elements1;
//            }
//
//            public void setElements1(ElementNodeType1 elements1) {
//                this.elements1 = elements1;
//            }

//            public ElementNodeType2 getElements2() {
//                return elements2;
//            }
//
//            public void setElements2(ElementNodeType2 elements2) {
//                this.elements2 = elements2;
//            }
//
//            public ElementNodeType3 getElements3() {
//                return elements3;
//            }
//
//            public void setElements3(ElementNodeType3 elements3) {
//                this.elements3 = elements3;
//            }

            //            public static class ElementNodeType1{
            //nodeType 1&2
//            private String id;
//            private String name;
//            private int nodeType;
//
//            public void setId(String id) {
//                this.id = id;
//            }
//
//            public void setName(String name) {
//                this.name = name;
//            }
//
//            public void setNodeType(int nodeType) {
//                this.nodeType = nodeType;
//            }
//
//            public String getId() {
//                return id;
//            }
//
//            public String getName() {
//                return name;
//            }
//
//            public int getNodeType() {
//                return nodeType;
//            }
//
//            @Override
//            public String toString() {
//                return "ElementNodeType1{" +
//                        "id='" + id + '\'' +
//                        ", name='" + name + '\'' +
//                        ", nodeType=" + nodeType +
//                        '}';
//            }
//        }
//    }
//            }
//
//            public static class ElementNodeType2{
//                //nodeType 1&2
//                private String id;
//                private String name;
//                private int nodeType;
//                private String introduction;
//                private String pictureUrl;
//
//                public void setId(String id) {
//                    this.id = id;
//                }
//
//                public void setName(String name) {
//                    this.name = name;
//                }
//
//                public void setNodeType(int nodeType) {
//                    this.nodeType = nodeType;
//                }
//
//                public String getId() {
//                    return id;
//                }
//
//                public String getName() {
//                    return name;
//                }
//
//                public int getNodeType() {
//                    return nodeType;
//                }
//
//                /*********************nodeType2*******************/
//                public String getIntroduction() {
//                    return introduction;
//                }
//
//                public void setIntroduction(String introduction) {
//                    this.introduction = introduction;
//                }
//
//                public String getPictureUrl() {
//                    return pictureUrl;
//                }
//
//                public void setPictureUrl(String pictureUrl) {
//                    this.pictureUrl = pictureUrl;
//                }
//
//                @Override
//                public String toString() {
//                    return "ElementNodeType2{" +
//                            "id='" + id + '\'' +
//                            ", name='" + name + '\'' +
//                            ", nodeType=" + nodeType +
//                            ", introduction='" + introduction + '\'' +
//                            ", pictureUrl='" + pictureUrl + '\'' +
//                            '}';
//                }
//            }
//
//            public static class ElementNodeType3 {

            private String id;
            private String name;
            private int nodeType;

            private String introduction;
            private String pictureUrl;

            private int displayType;
            private NavigationEntity navigation;
            private UnitListEntity unitList;
            private ThemeUnitsEntity themeUnits;


            public void setName(String name) {
                this.name = name;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getIntroduction() {
                return introduction;
            }

            public void setIntroduction(String introduction) {
                this.introduction = introduction;
            }

            public void setNodeType(int nodeType) {
                this.nodeType = nodeType;
            }

            public String getPictureUrl() {
                return pictureUrl;
            }

            public void setPictureUrl(String pictureUrl) {
                this.pictureUrl = pictureUrl;
            }

            public void setDisplayType(int displayType) {
                this.displayType = displayType;
            }

            public void setNavigation(NavigationEntity navigation) {
                this.navigation = navigation;
            }

            public void setUnitList(UnitListEntity unitList) {
                    this.unitList = unitList;
            }

            public void setThemeUnits(ThemeUnitsEntity themeUnits) {
                this.themeUnits = themeUnits;
            }

            public int getDisplayType() {
                return displayType;
            }

            public NavigationEntity getNavigation() {
                return navigation;
            }

            public String getId() {
                return id;
            }

            public String getName() {
                return name;
            }

            public int getNodeType() {
                return nodeType;
            }

//                public List<UnitListEntity> getUnitList() {
//                    return unitList;
//                }
//
//                public List<ThemeUnitsEntity> getThemeUnits() {
//                    return themeUnits;
//                }

            public UnitListEntity getUnitList() {
                    return unitList;
            }

            public ThemeUnitsEntity getThemeUnits() {
                return themeUnits;
            }

                public static class NavigationEntity {
                    private boolean isWorldwide;
                    private int cityId;
                    private String cityName;
                    private int districtId;
                    private Object districtName;
                    private int areaId;
                    private String id;
                    private String name;
                    private int nodeType;

                    public void setIsWorldwide(boolean isWorldwide) {
                        this.isWorldwide = isWorldwide;
                    }

                    public void setCityId(int cityId) {
                        this.cityId = cityId;
                    }

                    public void setCityName(String cityName) {
                        this.cityName = cityName;
                    }

                    public void setDistrictId(int districtId) {
                        this.districtId = districtId;
                    }

                    public void setDistrictName(Object districtName) {
                        this.districtName = districtName;
                    }

                    public void setAreaId(int areaId) {
                        this.areaId = areaId;
                    }

                    public void setId(String id) {
                        this.id = id;
                    }

                    public void setName(String name) {
                        this.name = name;
                    }

                    public void setNodeType(int nodeType) {
                        this.nodeType = nodeType;
                    }

                    public boolean isIsWorldwide() {
                        return isWorldwide;
                    }

                    public int getCityId() {
                        return cityId;
                    }

                    public String getCityName() {
                        return cityName;
                    }

                    public int getDistrictId() {
                        return districtId;
                    }

                    public Object getDistrictName() {
                        return districtName;
                    }

                    public int getAreaId() {
                        return areaId;
                    }

                    public String getId() {
                        return id;
                    }

                    public String getName() {
                        return name;
                    }

                    public int getNodeType() {
                        return nodeType;
                    }

                    @Override
                    public String toString() {
                        return "NavigationEntity{" +
                                "isWorldwide=" + isWorldwide +
                                ", cityId=" + cityId +
                                ", cityName='" + cityName + '\'' +
                                ", districtId=" + districtId +
                                ", districtName=" + districtName +
                                ", areaId=" + areaId +
                                ", id='" + id + '\'' +
                                ", name='" + name + '\'' +
                                ", nodeType=" + nodeType +
                                '}';
                    }
                }

                public static class UnitListEntity {
                    private int unitID;
                    private String unitName;
                    private String pictureURL;
                    private int cityID;
                    private String cityName;
                    private int scenicSpotID;
                    private int seoScenicSpotId;
                    private String scenicSpotName;
                    private String houseTypeLabel;
                    private double longitude;
                    private double latitude;
                    private boolean isFavorite;
                    private boolean isWorldwide;
                    private String roomCountSummary;
                    private double lowestPrice;
                    private double highestPrice;
                    private double finalPrice;
                    private String priceUnitLabel;
                    private int recommendPeopleCount;
                    private int unitInstanceCount;
                    private int enumCooperationMode;
                    private Object extension400;
                    private String subDistrict;
                    private Object summary;
                    private boolean isHidePrice;

                    public void setUnitID(int unitID) {
                        this.unitID = unitID;
                    }

                    public void setUnitName(String unitName) {
                        this.unitName = unitName;
                    }

                    public void setPictureURL(String pictureURL) {
                        this.pictureURL = pictureURL;
                    }

                    public void setCityID(int cityID) {
                        this.cityID = cityID;
                    }

                    public void setCityName(String cityName) {
                        this.cityName = cityName;
                    }

                    public void setScenicSpotID(int scenicSpotID) {
                        this.scenicSpotID = scenicSpotID;
                    }

                    public void setSeoScenicSpotId(int seoScenicSpotId) {
                        this.seoScenicSpotId = seoScenicSpotId;
                    }

                    public void setScenicSpotName(String scenicSpotName) {
                        this.scenicSpotName = scenicSpotName;
                    }

                    public void setHouseTypeLabel(String houseTypeLabel) {
                        this.houseTypeLabel = houseTypeLabel;
                    }

                    public void setLongitude(double longitude) {
                        this.longitude = longitude;
                    }

                    public void setLatitude(double latitude) {
                        this.latitude = latitude;
                    }

                    public void setIsFavorite(boolean isFavorite) {
                        this.isFavorite = isFavorite;
                    }

                    public void setIsWorldwide(boolean isWorldwide) {
                        this.isWorldwide = isWorldwide;
                    }

                    public void setRoomCountSummary(String roomCountSummary) {
                        this.roomCountSummary = roomCountSummary;
                    }

                    public void setLowestPrice(double lowestPrice) {
                        this.lowestPrice = lowestPrice;
                    }

                    public void setHighestPrice(double highestPrice) {
                        this.highestPrice = highestPrice;
                    }

                    public void setFinalPrice(double finalPrice) {
                        this.finalPrice = finalPrice;
                    }

                    public void setPriceUnitLabel(String priceUnitLabel) {
                        this.priceUnitLabel = priceUnitLabel;
                    }

                    public void setRecommendPeopleCount(int recommendPeopleCount) {
                        this.recommendPeopleCount = recommendPeopleCount;
                    }

                    public void setUnitInstanceCount(int unitInstanceCount) {
                        this.unitInstanceCount = unitInstanceCount;
                    }

                    public void setEnumCooperationMode(int enumCooperationMode) {
                        this.enumCooperationMode = enumCooperationMode;
                    }

                    public void setExtension400(Object extension400) {
                        this.extension400 = extension400;
                    }

                    public void setSubDistrict(String subDistrict) {
                        this.subDistrict = subDistrict;
                    }

                    public void setSummary(Object summary) {
                        this.summary = summary;
                    }

                    public void setIsHidePrice(boolean isHidePrice) {
                        this.isHidePrice = isHidePrice;
                    }

                    public int getUnitID() {
                        return unitID;
                    }

                    public String getUnitName() {
                        return unitName;
                    }

                    public String getPictureURL() {
                        return pictureURL;
                    }

                    public int getCityID() {
                        return cityID;
                    }

                    public String getCityName() {
                        return cityName;
                    }

                    public int getScenicSpotID() {
                        return scenicSpotID;
                    }

                    public int getSeoScenicSpotId() {
                        return seoScenicSpotId;
                    }

                    public String getScenicSpotName() {
                        return scenicSpotName;
                    }

                    public String getHouseTypeLabel() {
                        return houseTypeLabel;
                    }

                    public double getLongitude() {
                        return longitude;
                    }

                    public double getLatitude() {
                        return latitude;
                    }

                    public boolean isIsFavorite() {
                        return isFavorite;
                    }

                    public boolean isIsWorldwide() {
                        return isWorldwide;
                    }

                    public String getRoomCountSummary() {
                        return roomCountSummary;
                    }

                    public double getLowestPrice() {
                        return lowestPrice;
                    }

                    public double getHighestPrice() {
                        return highestPrice;
                    }

                    public double getFinalPrice() {
                        return finalPrice;
                    }

                    public String getPriceUnitLabel() {
                        return priceUnitLabel;
                    }

                    public int getRecommendPeopleCount() {
                        return recommendPeopleCount;
                    }

                    public int getUnitInstanceCount() {
                        return unitInstanceCount;
                    }

                    public int getEnumCooperationMode() {
                        return enumCooperationMode;
                    }

                    public Object getExtension400() {
                        return extension400;
                    }

                    public String getSubDistrict() {
                        return subDistrict;
                    }

                    public Object getSummary() {
                        return summary;
                    }

                    public boolean isIsHidePrice() {
                        return isHidePrice;
                    }

                    @Override
                    public String toString() {
                        return "UnitListEntity{" +
                                "unitID=" + unitID +
                                ", unitName='" + unitName + '\'' +
                                ", pictureURL='" + pictureURL + '\'' +
                                ", cityID=" + cityID +
                                ", cityName='" + cityName + '\'' +
                                ", scenicSpotID=" + scenicSpotID +
                                ", seoScenicSpotId=" + seoScenicSpotId +
                                ", scenicSpotName='" + scenicSpotName + '\'' +
                                ", houseTypeLabel='" + houseTypeLabel + '\'' +
                                ", longitude=" + longitude +
                                ", latitude=" + latitude +
                                ", isFavorite=" + isFavorite +
                                ", isWorldwide=" + isWorldwide +
                                ", roomCountSummary='" + roomCountSummary + '\'' +
                                ", lowestPrice=" + lowestPrice +
                                ", highestPrice=" + highestPrice +
                                ", finalPrice=" + finalPrice +
                                ", priceUnitLabel='" + priceUnitLabel + '\'' +
                                ", recommendPeopleCount=" + recommendPeopleCount +
                                ", unitInstanceCount=" + unitInstanceCount +
                                ", enumCooperationMode=" + enumCooperationMode +
                                ", extension400=" + extension400 +
                                ", subDistrict='" + subDistrict + '\'' +
                                ", summary=" + summary +
                                ", isHidePrice=" + isHidePrice +
                                '}';
                    }
                }

            public static class ThemeUnitsEntity {
                private String id;
                private int unitID;
                private String unitName;
                private String pictureUrl;
                private Object summary;
                private String introduction;
                private boolean isHidePrice;

                public void setId(String id) {
                    this.id = id;
                }

                public void setUnitID(int unitID) {
                    this.unitID = unitID;
                }

                public void setUnitName(String unitName) {
                    this.unitName = unitName;
                }

                public void setPictureUrl(String pictureUrl) {
                    this.pictureUrl = pictureUrl;
                }

                public void setSummary(Object summary) {
                    this.summary = summary;
                }

                public void setIntroduction(String introduction) {
                    this.introduction = introduction;
                }

                public void setIsHidePrice(boolean isHidePrice) {
                    this.isHidePrice = isHidePrice;
                }

                public String getId() {
                    return id;
                }

                public int getUnitID() {
                    return unitID;
                }

                public String getUnitName() {
                    return unitName;
                }

                public String getPictureUrl() {
                    return pictureUrl;
                }

                public Object getSummary() {
                    return summary;
                }

                public String getIntroduction() {
                    return introduction;
                }

                public boolean isIsHidePrice() {
                    return isHidePrice;
                }

                @Override
                public String toString() {
                    return "ThemeUnitsEntity{" +
                            "id='" + id + '\'' +
                            ", unitID=" + unitID +
                            ", unitName='" + unitName + '\'' +
                            ", pictureUrl='" + pictureUrl + '\'' +
                            ", summary=" + summary +
                            ", introduction='" + introduction + '\'' +
                            ", isHidePrice=" + isHidePrice +
                            '}';
                }
            }

            @Override
            public String toString() {
                return "ElementsEntity{" +
                        "id='" + id + '\'' +
                        ", name='" + name + '\'' +
                        ", nodeType=" + nodeType +
                        ", introduction='" + introduction + '\'' +
                        ", pictureUrl='" + pictureUrl + '\'' +
                        ", displayType=" + displayType +
                        ", navigation=" + navigation +
                        ", unitList=" + unitList +
                        ", themeUnits=" + themeUnits +
                        '}';
            }
        }

        @Override
        public String toString() {
            return "ContentEntity{" +
                    "id=" + id +
                    ", groupId=" + groupId +
                    ", name='" + name + '\'' +
                    ", order=" + order +
                    ", isActive=" + isActive +
                    ", introduction='" + introduction + '\'' +
                    ", summary='" + summary + '\'' +
                    ", smallPictureURL='" + smallPictureURL + '\'' +
                    ", mediumPictureURL='" + mediumPictureURL + '\'' +
                    ", largePictureURL='" + largePictureURL + '\'' +
                    ", shareSetting=" + shareSetting +
                    ", elements=" + elementsEntity +
                    '}';
        }
    }
}
