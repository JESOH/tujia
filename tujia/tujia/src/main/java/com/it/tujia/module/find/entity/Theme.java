package com.it.tujia.module.find.entity;

/**
 * Created by Administrator on 2015/11/14.
 */
public class Theme {

    private String name;//最新

    private DetailListEntity detailListEntity;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setDetailList(DetailListEntity detailListEntity) {
        this.detailListEntity = detailListEntity;
    }

    public DetailListEntity getDetailList() {
        return detailListEntity;
    }

    public static class DetailListEntity {
        private int id;
        private String name;
        private String summary;
        private String introduction;
        private String smallPictureURL;
        private String largePictureURL;
        private Object unitIDList;

        public void setId(int id) {
            this.id = id;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setSummary(String summary) {
            this.summary = summary;
        }

        public void setIntroduction(String introduction) {
            this.introduction = introduction;
        }

        public void setSmallPictureURL(String smallPictureURL) {
            this.smallPictureURL = smallPictureURL;
        }

        public void setLargePictureURL(String largePictureURL) {
            this.largePictureURL = largePictureURL;
        }

        public void setUnitIDList(Object unitIDList) {
            this.unitIDList = unitIDList;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getSummary() {
            return summary;
        }

        public String getIntroduction() {
            return introduction;
        }

        public String getSmallPictureURL() {
            return smallPictureURL;
        }

        public String getLargePictureURL() {
            return largePictureURL;
        }

        public Object getUnitIDList() {
            return unitIDList;
        }

        @Override
        public String toString() {
            return "DetailListEntity{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", summary='" + summary + '\'' +
                    ", introduction='" + introduction + '\'' +
                    ", smallPictureURL='" + smallPictureURL + '\'' +
                    ", largePictureURL='" + largePictureURL + '\'' +
                    ", unitIDList=" + unitIDList +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "Content{" +
                "name='" + name + '\'' +
                ", detailListEntity=" + detailListEntity +
                '}';
    }
}
