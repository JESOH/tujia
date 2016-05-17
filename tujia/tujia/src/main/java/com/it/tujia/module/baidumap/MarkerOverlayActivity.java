package com.it.tujia.module.baidumap;

import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;
import com.it.tujia.R;

import android.os.Bundle;

public class MarkerOverlayActivity extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		draw();
	}

	private void draw() {
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
