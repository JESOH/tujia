package com.it.tujia.module.find.entity;

import java.util.List;

/**
 * Created by Administrator on 2015/11/15.
 */
public class Strategy {

    /**
     * id : 2c9b1c18-a9ec-478b-9dec-af36a691ed23
     * indexTag : 热门
     * name : 推荐城市
     * list : [{"id":17,"name":"北京度假攻略","largePictureURL":"http://pic.tujia.com/upload/mobileconfig/day_150924/201509241215066141.jpg"}]
     */

    private String id;
    private String indexTag;
    private String name;

    private StrategyListEntity mStrategyList;

    public void setId(String id) {
        this.id = id;
    }

    public void setIndexTag(String indexTag) {
        this.indexTag = indexTag;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setList(StrategyListEntity list) {
        this.mStrategyList = list;
    }

    public String getId() {
        return id;
    }

    public String getIndexTag() {
        return indexTag;
    }

    public String getName() {
        return name;
    }

    public StrategyListEntity getList() {
        return mStrategyList;
    }

    public static class StrategyListEntity {
        private int id;
        private String name;
        private String largePictureURL;

        public void setId(int id) {
            this.id = id;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setLargePictureURL(String largePictureURL) {
            this.largePictureURL = largePictureURL;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getLargePictureURL() {
            return largePictureURL;
        }

        @Override
        public String toString() {
            return "StrategyListEntity{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", largePictureURL='" + largePictureURL + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "Strategy{" +
                "id='" + id + '\'' +
                ", indexTag='" + indexTag + '\'' +
                ", name='" + name + '\'' +
                ", mStrategyList=" + mStrategyList +
                '}';
    }
}
