package com.it.tujia.application;

import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.os.Environment;
import android.widget.Toast;

import com.baidu.mapapi.SDKInitializer;
import com.google.gson.Gson;
import com.it.tujia.R;
import com.it.tujia.db.DbCity;
import com.lidroid.xutils.DbUtils;
import com.lidroid.xutils.exception.DbException;
import com.loopj.android.http.AsyncHttpClient;
import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiskCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.io.File;


/**
 * Created by kkguo on 2015/11/13.
 */
public class App extends Application {
    private static App apps;
    private ImageLoader mImageLoader;
    private DisplayImageOptions mOptions;
    private Gson mGson;
    private  AsyncHttpClient httpClient ;

    @Override
    public void onCreate() {
        super.onCreate();
        apps = this;
        //开始进行初始化一切空间
        initImageloader();
        initDbUtils();
        mGson = new Gson();
        httpClient = new AsyncHttpClient();
        httpClient.setConnectTimeout(10*1000);
        httpClient.setResponseTimeout(10*1000);
        //初始化百度地图
        initBaiduMap();
    }
    private DbUtils dbUtils;
    private DbUtils.DaoConfig daoConfig;
    private void initDbUtils() {

        daoConfig=new DbUtils.DaoConfig(getApplicationContext());
        daoConfig.setDbName("tujia.db");
        daoConfig.setDbVersion(1);

        daoConfig.setDbDir( Environment.getExternalStorageDirectory().getPath()+"/sdcard/");
        dbUtils = DbUtils.create(daoConfig);
        try {
            dbUtils.createTableIfNotExist(DbCity.class);
        } catch (DbException e) {
            e.printStackTrace();
        }

    }

    public DbUtils getDbUtils(){
        return dbUtils;
    }

    private void initImageloader() {
        mImageLoader = ImageLoader.getInstance();

        int maxSize = (int) Runtime.getRuntime().maxMemory() / 8;
        String path = null;
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {//有sd卡挂载
            path = Environment.getExternalStorageDirectory().getPath();
        } else {
            path = getCacheDir().getPath();
        }
        File cacheFile = new File(path, "/tujia/images");
        cacheFile.mkdirs();


        ImageLoaderConfiguration configuration =
                new ImageLoaderConfiguration.Builder(this)
                        .memoryCacheSize(maxSize)
                        .diskCache(new UnlimitedDiskCache(cacheFile))
                        .diskCacheSize(30 * 1024 * 1024)
                        .defaultDisplayImageOptions(mOptions)
                        .diskCacheFileCount(300)
                        .build();


        ImageLoader.getInstance().init(configuration);

        mOptions = new DisplayImageOptions.Builder()
                .cacheOnDisk(true)
                .cacheInMemory(true)
                .delayBeforeLoading(200)
                .showImageOnLoading(R.drawable.default_unit_small)
                .showImageOnFail(R.drawable.no_result_icon)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .build();
    }

    public AsyncHttpClient getHttpClient() {
        return httpClient;
    }



    public static App getApp() {
        return apps;
    }

    public ImageLoader getImagerLoader() {
        return mImageLoader;
    }

    public DisplayImageOptions getOptions() {
        return mOptions;
    }

    public Gson getGson() {
        return mGson;
    }

    /**
     * 初始化百度地图
     */
    private void initBaiduMap(){
        //注意该方法要再setContentView方法之前实现
        SDKInitializer.initialize(getApplicationContext());
        //动态SDK注册
        MyReceiver receiver = new MyReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction(SDKInitializer.SDK_BROADCAST_ACTION_STRING_NETWORK_ERROR);
        filter.addAction(SDKInitializer.SDK_BROADTCAST_ACTION_STRING_PERMISSION_CHECK_ERROR);
        registerReceiver(receiver, filter);
    }

    /**
     * 构造广播监听类，监听 SDK key 验证以及网络异常广播
     */
    class MyReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if(action.equals(SDKInitializer.SDK_BROADCAST_ACTION_STRING_NETWORK_ERROR)){
                Toast.makeText(getApplicationContext(), "网络出错", Toast.LENGTH_LONG).show();
            }else if(action.equals(SDKInitializer.SDK_BROADTCAST_ACTION_STRING_PERMISSION_CHECK_ERROR)){
//                Toast.makeText(getApplicationContext(), "key 验证出错!", Toast.LENGTH_LONG).show();
            }
        }
    }

}
