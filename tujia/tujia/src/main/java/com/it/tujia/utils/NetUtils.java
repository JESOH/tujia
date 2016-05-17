package com.it.tujia.utils;

import android.content.Context;

import com.it.tujia.application.App;
import com.it.tujia.impl.DataCallBackListner;
import com.it.tujia.impl.DataCallBackListener2;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.HttpEntity;
import cz.msebera.android.httpclient.entity.StringEntity;

/**
 * Created by kkguo on 2015/11/14.
 */
public class NetUtils {
    public static NetUtils netUtils=new NetUtils();
    AsyncHttpClient httpClient;
    private NetUtils(){
        httpClient = App.getApp().getHttpClient();
    }
    public static NetUtils getInstance(){
       return netUtils;
   }

    public void sentPost(Context context,String url,String jsonData, final DataCallBackListner dataCallBackListner){
        HttpEntity httpEntity=new StringEntity(jsonData,"utf-8");
        httpClient.post(context,url,httpEntity,"application/json",new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                dataCallBackListner.onSuccess(response.toString());
            }

            @Override
            public void onFinish() {
                super.onFinish();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                dataCallBackListner.onFailure("请求失败，请注意");
            }
        });
    }

    public void sentPostWithProgress(Context context,String url,String jsonData, final DataCallBackListener2 dataCallBackListner){
        HttpEntity httpEntity=new StringEntity(jsonData,"utf-8");
        httpClient.post(context,url,httpEntity,"application/json",new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                dataCallBackListner.onSuccess(response.toString());
            }

            //必须重写所有onFailure方法才能完全获取到连接失败的异常
            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse) {
                dataCallBackListner.onFailure(statusCode, errorResponse.toString(), throwable);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                dataCallBackListner.onFailure(statusCode,responseString,throwable);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                dataCallBackListner.onFailure(statusCode, errorResponse.toString(), throwable);
            }
            @Override
            public void onProgress(long bytesWritten, long totalSize) {
                dataCallBackListner.onProgress(bytesWritten,totalSize);

            }

            @Override
            public void onUserException(Throwable error) {
                dataCallBackListner.onUserException(error);
            }
        });
    }
}
