package com.it.tujia.utils;

public class JsonDataUtils {

    public static String getHomePageData(String id) {
        String word = "{\"parameter\":{\"list\":[{\"con\":null,\"getConfigModel\":null,\"ver\":\"\",\"id\":" + id + "}]},\"client\":{\"appVersion\":\"4.1_36\",\"channelCode\":\"qq\",\"devModel\":\"NoxW\",\"devToken\":\"0807aaacd9e\",\"uID\":\"00000000-565a-c83a-ffff-ffff99d603a9\",\"locale\":\"zh-CN\",\"osVersion\":\"4.4.2\",\"screenInfo\":\"\",\"devType\":2},\"code\":null,\"psid\":\"7bcd17b2-cc19-498b-b2a8-9f47ac6966f1\",\"type\":\"GetConfigInfo\",\"user\":null,\"usid\":\"ad47d164-044f-4678-9edb-7759abfe67ca\"}";
        return word;
    }
    public static String getCityHotelData(int value){
        String cityHotel="{\"parameter\":{\"H5Url\":null,\"conditions\":[{\"value\":\""+value+"\",\"label\":null,\"type\":42,\"gType\":0}],\"pageIndex\":0,\"pageSize\":20,\"returnAllConditions\":false},\"client\":{\"appVersion\":\"4.1_36\",\"channelCode\":\"qq\",\"devModel\":\"NoxW\",\"devToken\":\"020150d537d\",\"uID\":\"00000000-565a-c83a-ffff-ffff99d603a9\",\"locale\":\"zh-CN\",\"osVersion\":\"4.4.2\",\"screenInfo\":\"\",\"devType\":2},\"code\":null,\"psid\":\"a3caed19-6a01-4284-983c-c9eb3d9829ae\",\"type\":\"SearchUnitFull\",\"user\":null,\"usid\":\"ad47d164-044f-4678-9edb-7759abfe67ca\"}";
                return cityHotel;
    }
    public  static  String getCitySelcetData(int value){
        String selcet="{\"parameter\":{\"H5Url\":null,\"conditions\":[{\"value\":\""+value+"\",\"label\":null,\"type\":42,\"gType\":0}],\"pageIndex\":0,\"pageSize\":20,\"returnAllConditions\":true},\"client\":{\"appVersion\":\"4.1_36\",\"channelCode\":\"qq\",\"devModel\":\"NoxW\",\"devToken\":\"020150d537d\",\"uID\":\"00000000-565a-c83a-ffff-ffff99d603a9\",\"locale\":\"zh-CN\",\"osVersion\":\"4.4.2\",\"screenInfo\":\"\",\"devType\":2},\"code\":null,\"psid\":\"3e7ce1b7-9720-4519-9c98-6b516d8a6f72\",\"type\":\"SearchUnitFull\",\"user\":null,\"usid\":\"ad47d164-044f-4678-9edb-7759abfe67ca\"}";
        return selcet;
    }

    public static String getThemeData(String id) {
        String theme = "{\"parameter\":{\"id\":" + id + "},\"client\":{\"appVersion\":\"4.1_36\",\"channelCode\":\"qq\",\"devModel\":\"NoxW\",\"devToken\":\"0807aaacd9e\",\"uID\":\"00000000-565a-c83a-ffff-ffff99d603a9\",\"locale\":\"zh-CN\",\"osVersion\":\"4.4.2\",\"screenInfo\":\"\",\"devType\":2},\"psid\":\"1d7140b9-73a5-4767-b40b-0151ae1559d9\",\"type\":\"GetThemeContent\",\"usid\":\"ad47d164-044f-4678-9edb-7759abfe67ca\"}";
        return theme;
    }

    public static String getDetailData(String unitID) {
        String room_detail = "{\"parameter\":{\"pageIndex\":0,\"pageSize\":10,\"hasPicture\":false,\"unitID\":" + unitID + "},\"client\":{\"appVersion\":\"4.1_36\",\"channelCode\":\"qq\",\"devModel\":\"NoxW\",\"devToken\":\"03076439f63\",\"uID\":\"00000000-565a-c83a-ffff-ffff99d603a9\",\"locale\":\"zh-CN\",\"osVersion\":\"4.4.2\",\"screenInfo\":\"\",\"devType\":2},\"psid\":\"52710987-cc04-4de4-8073-9c1cfca009a9\",\"type\":\"GetUnitComment\",\"usid\":\"ad47d164-044f-4678-9edb-7759abfe67ca\"}";
        return room_detail;
    }

    /**
     * 位置区域
     *
     * @return
     */
    public static String getLocationAreaData(){
        String areaData = "{\"parameter\":{\"destId\":48},\"client\":{\"appVersion\":\"4.1_36\",\"channelCode\":\"hiapk\",\"devModel\":\"NoxW\",\"devToken\":\"050f2bceac7\",\"uID\":\"00000000-37f2-3d5a-ffff-ffff99d603a9\",\"locale\":\"zh-CN\",\"osVersion\":\"4.4.2\",\"screenInfo\":\"\",\"devType\":2},\"code\":null,\"psid\":\"0d01cc00-8ee6-4a73-ac06-7a86a0f9fc08\",\"type\":\"GetGeoFilter\",\"user\":null,\"usid\":\"b8b751ee-4458-4d18-bf31-09fd7caa9792\"}";

        return areaData;
    }

    /**
     * 主题
     *
     * @param id
     * @return
     */
    public static String getDiscoverThemeData(String id) {
        String discoverString = "{\"parameter\":{\"list\":[{\"con\":null,\"getConfigModel\":null,\"ver\":\"\",\"id\":" + id + "}]}," +
                "\"client\":{\"appVersion\":\"4.1_36\",\"channelCode\":\"qq\",\"devModel\":\"NoxW\",\"devToken\":\"0807aaacd9e\"," +
                "\"uID\":\"00000000-565a-c83a-ffff-ffff99d603a9\",\"locale\":\"zh-CN\",\"osVersion\":\"4.4.2\",\"screenInfo\":\"\"," +
                "\"devType\":2},\"code\":null,\"psid\":\"7bcd17b2-cc19-498b-b2a8-9f47ac6966f1\",\"type\":\"GetConfigInfo\",\"user\":null," +
                "\"usid\":\"ad47d164-044f-4678-9edb-7759abfe67ca\"}";
        return discoverString;
    }

    /**
     * 攻略
     *
     * @param id
     * @return
     */
    public static String getDiscoverStrategyData(String id) {
        String strategyData = "{\"parameter\":{\"list\":[{\"con\":null,\"getConfigModel\":null,\"ver\":\"\",\"id\":" + id + "}]}," +
                "\"client\":{\"appVersion\":\"4.1_36\",\"channelCode\":\"qq\",\"devModel\":\"NoxW\",\"devToken\":\"0807aaacd9e\"," +
                "\"uID\":\"00000000-565a-c83a-ffff-ffff99d603a9\",\"locale\":\"zh-CN\",\"osVersion\":\"4.4.2\",\"screenInfo\":\"\"," +
                "\"devType\":2},\"code\":null,\"psid\":\"7bcd17b2-cc19-498b-b2a8-9f47ac6966f1\",\"type\":\"GetConfigInfo\",\"user\":null," +
                "\"usid\":\"ad47d164-044f-4678-9edb-7759abfe67ca\"}\n";
        return strategyData;
    }

    /**
     * 发现的详情内容
     *
     * @param id
     * @return
     */
    public static String getDisCoverDetailData(int id) {
        String detailData = "{\"parameter\":{\"id\":" + id + "},\"client\":{\"appVersion\":\"4.1_36\",\"channelCode\":\"qq\",\"devModel\":" +
                "\"NoxW\",\"devToken\":\"020150d537d\",\"uID\":\"00000000-565a-c83a-ffff-ffff99d603a9\",\"locale\":\"zh-CN\",\"osVersion\"" +
                ":\"4.4.2\",\"screenInfo\":\"\",\"devType\":2},\"psid\":\"14d81ab9-eba8-4d8c-b77d-50cb13369ba1\",\"type\":\"GetThemeContent\"," +
                "\"usid\":\"ad47d164-044f-4678-9edb-7759abfe67ca\"}";
        return detailData;
    }

    /**
     * 酒店介绍
     *
     * @param unitID
     * @return
     */
    public static String getDetailIntroduceData(int unitID) {
        String introduceData = "{\"parameter\":{\"unitID\":" + unitID + "},\"client\":{\"appVersion\":\"4.2_37\",\"channelCode\":\"wandoujia\",\"devModel\":\"NoxW\",\"devToken\":\"00019415d9f\",\"uID\":\"ffffffff-e207-55b7-ffff-ffffbdf93ea2\",\"locale\":\"zh-CN\",\"osVersion\":\"4.1.1\",\"screenInfo\":\"\",\"devType\":2},\"code\":null,\"psid\":\"46b1431d-7d93-418b-966c-97313d052b5d\",\"type\":\"GetUnitDetailInfo\",\"user\":null,\"usid\":\"0042037b-517b-40e0-bd50-3b8f0a8e50bf\"}";
        return introduceData;
    }

    /**
     * 特色
     *
     * @param id
     * @return
     */
    public static String getFeatureUnitsData(int id, int pageIndex) {
        String detailParams2 = "{\"parameter\":{\"conditions\":[],\"id\":" + id + " ,\"pageIndex\":" + pageIndex + ",\"pageSize\":8}," +
                "\"client\":{\"appVersion\":\"4.1_36\",\"channelCode\":\"qq\",\"devModel\":\"NoxW\",\"devToken\":\"020150d537d\"," +
                "\"uID\":\"00000000-565a-c83a-ffff-ffff99d603a9\",\"locale\":\"zh-CN\",\"osVersion\":\"4.4.2\",\"screenInfo\":\"\",\"devType\":2}," +
                "\"code\":null,\"psid\":\"00ca9df1-b514-4fff-a167-e45a2f16b625\",\"type\":\"GetPhotoWall\",\"user\":null," +
                "\"usid\":\"ad47d164-044f-4678-9edb-7759abfe67ca\"}";
        return detailParams2;
    }

}
