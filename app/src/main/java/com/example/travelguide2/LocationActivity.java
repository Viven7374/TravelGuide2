package com.example.travelguide2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;

import java.util.ArrayList;
import java.util.List;


public class LocationActivity extends AppCompatActivity {

    //声明变量
    TextView locationInfo;
    Button bt_dh;
    double la,lg;
    LocationClient mLocationClient;
    MapView mapView;
    BaiduMap baiduMap = null;
    Boolean isFirstLocate = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //地图环境初始化
        SDKInitializer.setAgreePrivacy(getApplicationContext(), true);
        SDKInitializer.initialize(getApplicationContext());
        LocationClient.setAgreePrivacy(true);
        setContentView(R.layout.activity_location);

        //初始化
        locationInfo = findViewById(R.id.locationInfo);
        bt_dh = findViewById(R.id.location_dh);
        mapView = findViewById(R.id.mapView);
        baiduMap = mapView.getMap();

        baiduMap.setMapType(BaiduMap.MAP_TYPE_SATELLITE);

        baiduMap.setMyLocationEnabled(true);

        try {
            mLocationClient = new LocationClient(getApplicationContext());
        }catch (Exception e){
            e.printStackTrace();
        }
        mLocationClient.registerLocationListener(new MyLocationListener());


        //申请权限
        List<String> permissionList = new ArrayList<>();
        if (ContextCompat.checkSelfPermission(LocationActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            permissionList.add(Manifest.permission.ACCESS_FINE_LOCATION);
        }
        if (ContextCompat.checkSelfPermission(LocationActivity.this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED){
            permissionList.add(Manifest.permission.READ_PHONE_STATE);
        }
        if (ContextCompat.checkSelfPermission(LocationActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            permissionList.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }
        if (!permissionList.isEmpty()){
            String [] permission = permissionList.toArray(new String[permissionList.size()]);
            ActivityCompat.requestPermissions(LocationActivity.this,permission,1);
        }else {
            //请求定位
            requestLocation();
        }

        bt_dh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("geo:" + lg + "," + la + "");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

        //获取Intent数据
        Intent intent = getIntent();
        String sightName = intent.getStringExtra("sight");
//
//        switch (sightName){
//            case "dq":
//                la=30.264062;
//                lg=120.157776;
//                break;
//            case "yh":
//                la=30.25308;
//                lg=120.21551;
//                break;
//            case "ll":
//                la=30.24691;
//                lg=120.10783;
//                break;
//            case "xx":
//                la=30.27289;
//                lg=120.07153;
//                break;
//        }
        //设置终点位置
//        LatLng latLng = new LatLng(la, lg);
//        MapStatus mapStatus = new MapStatus.Builder().target(latLng).zoom(16).build();
//        MapStatusUpdate msu = MapStatusUpdateFactory.newMapStatus(mapStatus);
//        baiduMap.setMapStatus(msu);
        //用图标标识目标坐标位置
//        BitmapDescriptor searchBDA = BitmapDescriptorFactory.fromResource(R.drawable.start);
//        MarkerOptions options = new MarkerOptions().icon(searchBDA).position(latLng);
//        baiduMap.addOverlay(options);

    }
//
        //权限判断
        @Override
        public void onRequestPermissionsResult ( int requestCode, @NonNull String[] permissions,
        @NonNull int[] grantResults){
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
            switch (requestCode) {
                case 1:
                    if (grantResults.length > 0) {
                        for (int result : grantResults) {
                            if (result != PackageManager.PERMISSION_GRANTED) {
                                Toast.makeText(this, "授予权限不足！", Toast.LENGTH_SHORT).show();
                                finish();
                                return;
                            }
                        }
                        requestLocation();
                    } else {
                        Toast.makeText(this, "未知！", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                    break;
            }
        }
//
    //请求定位
    private void requestLocation(){
        //初始化定位
        initLocation();
        mLocationClient.start();
    }

    //初始化定位
    private void initLocation(){
        LocationClientOption option = new LocationClientOption();
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);//高精度定位模式
        option.setCoorType("bd0911");//百度经纬度坐标
        //option.setScanSpan(1000);//发起定位请求间隔，1s
        option.setIsNeedAddress(true);//显示地理位置信息

        mLocationClient.setLocOption(option);
    }

    //定位监听
    private class MyLocationListener extends BDAbstractLocationListener{

        @Override
        public void onReceiveLocation(BDLocation bdLocation) {
            navigateTo(bdLocation);

        }
    }


    //将当前位置在地图上显示出来
    private void navigateTo(BDLocation bdLocation){
        if (isFirstLocate){
            LatLng ll = new LatLng(bdLocation.getLatitude(),bdLocation.getLongitude());
            MapStatusUpdate update = MapStatusUpdateFactory.newLatLng(ll);
            baiduMap.animateMapStatus(update);
            update = MapStatusUpdateFactory.zoomTo(16f);
            baiduMap.animateMapStatus(update);
        }
        MyLocationData.Builder builder = new MyLocationData.Builder();
        builder.longitude(bdLocation.getLongitude());
        builder.latitude(bdLocation.getLatitude());
        MyLocationData locationData = builder.build();
        baiduMap.setMyLocationData(locationData);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
        baiduMap.setMyLocationEnabled(false);
        mLocationClient.stop();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mapView.onPause();
    }
}










