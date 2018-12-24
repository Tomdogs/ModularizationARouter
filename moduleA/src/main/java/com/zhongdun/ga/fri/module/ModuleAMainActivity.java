package com.zhongdun.ga.fri.module;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.ppmap.navicore.mapdal.NativeEnv;
import com.ppmap.navicore.mapdal.NativeEnvParams;
import com.ppmap.navicore.mapdal.WorldManager;
import com.ppmap.ppmapsdk.MinemapAccountManager;
import com.ppmap.ppmapsdk.camera.CameraPosition;
import com.ppmap.ppmapsdk.geometry.LatLng;
import com.ppmap.ppmapsdk.maps.MapView;
import com.ppmap.ppmapsdk.maps.MineMap;
import com.ppmap.ppmapsdk.maps.OnMapReadyCallback;
import com.ppmap.ppmapsdk.storage.FileSource;
import com.zhongdun.ga.fri.module.util.Config;

@Route(path = "/module/1")
public class ModuleAMainActivity extends AppCompatActivity {
    //地图视图
    private MapView mMapView;
    //地图操作类
    private MineMap mMineMap;

    //谈话框 确认 定位功能
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_PHONE_STATE
    };
    public static void verifyStoragePermissions(Activity activity) {
        // Check if we have location permission
        int permission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_FINE_LOCATION);
        if (permission != PackageManager.PERMISSION_GRANTED) {
            // We don't have permission so prompt the user
            ActivityCompat.requestPermissions(
                    activity,
                    PERMISSIONS_STORAGE,
                    REQUEST_EXTERNAL_STORAGE
            );
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        verifyStoragePermissions(this);
        init();
        MinemapAccountManager.getInstance(this, Config.mToken, Config.mId);
        setTitle("PPMap");

        setContentView(R.layout.activity_main_module_a);
        mMapView = (MapView)findViewById(R.id.mapview);
        initMap(savedInstanceState);
        Log.i("lgd","这是moduleA");
    }

    /**
     * 初始化引擎所需的基础环境
     */
    private void init() {

        //设置应用程序根目录，地图缓存，地图数据，导航数据，搜索数据存储位置
        String mAppPath = FileSource.getCachePath(this) + "/";
        NativeEnvParams params = new NativeEnvParams(this,mAppPath);
        //初始化NaviCore引擎（搜索、逆地理，导航，公交功能使用前需初始化）
        NativeEnv.init(getApplicationContext(), params);
        //初始化世界管理器，用来管理当前的国家、城市、行政区划关系
        WorldManager.getInstance().init();
    }

    /**
     * 初始化地图
     *
     * @param savedInstanceState
     */
    private void initMap(Bundle savedInstanceState) {
        mMapView.onCreate(savedInstanceState);
        mMapView.setStyleUrl(Config.mBaseUrl);
        mMapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(MineMap mMineMap) {
                ModuleAMainActivity.this.mMineMap = mMineMap;
                //设置logo是否显示,默认是显示
                mMineMap.getUiSettings().setLogoEnabled(false);
                //设置是否显示引用，默认是显示，一般设置为false
                mMineMap.getUiSettings().setAttributionEnabled(false);
                //是否显示指南针
                mMineMap.getUiSettings().setCompassEnabled(true);
                //地图最大级别
                mMineMap.setMaxZoomPreference(17);
                //地图最小级别
                mMineMap.setMinZoomPreference(3);
                //mMineMap.setOnMapClickListener(BasicMapActivity.this);
                mMineMap.setCameraPosition(
                        new CameraPosition.Builder().
                                target(new LatLng(39.90758,116.39754))//设置中心点
                                .zoom(10)//设置缩放级别
                                .tilt(0)//设置倾斜角 0-60
                                .bearing(0)//设置旋转角
                                .build());
            }
        });
    }


}
