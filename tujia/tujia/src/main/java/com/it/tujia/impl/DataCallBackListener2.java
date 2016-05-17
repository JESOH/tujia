package com.it.tujia.impl;


/**
 * Created by kkguo on 2015/11/13.
 */
public interface DataCallBackListener2 {
    void onSuccess(String response);
    void onFailure(int statusCode, String response, Throwable throwable);
    void onProgress(long bytesWritten,long totalSize);
    void onUserException(Throwable error);
}
