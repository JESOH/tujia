package com.it.tujia.module.baidumap;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.model.LatLng;
import com.it.tujia.R;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

public class BaseActivity extends Activity {
	protected MapView mapView;
	protected BaiduMap baiduMap;
	protected LatLng szqf = new LatLng(22.540151, 113.951743);
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_baidu_common);
		
		mapView = (MapView) findViewById(R.id.bmapView);
		baiduMap = mapView.getMap();
		
		control();
		moveCenter();
	}
	
	private void moveCenter() {
		//获取创建代表一堆经纬度信息的点
		//LatLng point = new LatLng(22.540151, 113.951743);
		LatLng point = szqf;
		MapStatusUpdate center = MapStatusUpdateFactory.newLatLng(point);
		//设置定位的中心点
		baiduMap.setMapStatus(center);
	}

	private void control() {
		// 获得当前的缩放级别.  默认是12
		float zoom = baiduMap.getMapStatus().zoom;
		Log.e("等级", zoom+"");
		
		//百度地图 的缩放级别是：3-20级   20是带3D效果i
		//设置地图到指定的缩放级别
		MapStatusUpdate levelStatus = MapStatusUpdateFactory.zoomTo(18);
		//改变地图的状态
		baiduMap.setMapStatus(levelStatus);
	}
}
