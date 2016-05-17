package com.it.tujia.utils;

import com.lidroid.xutils.http.client.RetryHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Administrator on 2015/11/15.
 */
public class JSONDiscoverUtils {

    public static String getJSONString (String JSONString) throws JSONException {

        JSONObject jsonObject = new JSONObject(JSONString);

        JSONObject contentObject = jsonObject.getJSONObject("content");

        JSONArray arrayList1 = contentObject.getJSONArray("list");

        String conJSONString = null;

        for (int i = 0; i < arrayList1.length(); i++) {

            JSONObject conObject = arrayList1.getJSONObject(i);

            conJSONString = conObject.getString("con");
        }

        return conJSONString;
    }
}
