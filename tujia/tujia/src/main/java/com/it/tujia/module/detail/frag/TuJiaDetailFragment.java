package com.it.tujia.module.detail.frag;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.model.LatLng;
import com.it.tujia.R;

/**
 * Created by Administrator on 2015/11/20.
 */
public class TuJiaDetailFragment extends Fragment{

    private static TuJiaDetailFragment mFragment;
    private static LatLng szqf = new LatLng(22.540151, 113.951743);
    private  MapView mapView;
    private static BaiduMap baiduMap;

    public static void setLatLng(double lat,double lng){
        szqf = new LatLng(lat, lng);
    }

    public static TuJiaDetailFragment getFragment(){
        mFragment = new TuJiaDetailFragment();
        Bundle bundle = new Bundle();
        mFragment.setArguments(bundle);
        return mFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_baidu_common,container,false);

        mapView = (MapView) view.findViewById(R.id.bmapView);
        baiduMap = mapView.getMap();

        control();

        moveCenter();
        draw();

        return view;
    }

    private static void moveCenter() {
        //获取创建代表一堆经纬度信息的点
        //LatLng point = new LatLng(22.540151, 113.951743);
        LatLng point = szqf;
        MapStatusUpdate center = MapStatusUpdateFactory.newLatLng(point);
        //设置定位的中心点
        baiduMap.setMapStatus(center);
    }

    private void control() {
        //设置地图到指定的缩放级别
        MapStatusUpdate levelStatus = MapStatusUpdateFactory.zoomTo(18);
        //改变地图的状态
        baiduMap.setMapStatus(levelStatus);
    }

    private static void draw() {
        //MarkerOptions options = new MarkerOptions();
        //定义Maker坐标点
        //LatLng point = new LatLng(39.963175, 116.400244);
        //构建Marker图标
        BitmapDescriptor bitmap = BitmapDescriptorFactory
                .fromResource(R.drawable.ic_map_office);
        //构建MarkerOption，用于在地图上添加Marker
        //OverlayOptions option = new MarkerOptions()
        MarkerOptions option = new MarkerOptions()
                .position(szqf)
                .icon(bitmap);
        //在地图上添加Marker，并显示
        baiduMap.addOverlay(option);

    }
}
