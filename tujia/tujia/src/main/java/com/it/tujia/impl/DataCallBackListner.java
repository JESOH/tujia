package com.it.tujia.impl;


/**
 * Created by kkguo on 2015/11/13.
 */
public interface DataCallBackListner {
    void onSuccess(String response);
    void onFailure(String error);
}
