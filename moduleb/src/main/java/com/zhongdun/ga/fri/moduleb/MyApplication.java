package com.zhongdun.ga.fri.moduleb;

import android.app.Application;

import com.baidu.mapapi.CoordType;
import com.baidu.mapapi.SDKInitializer;
import com.zhongdun.ga.fri.base.BaseApplication;
import com.zhongdun.ga.fri.base.router.IComponentApplication;

/**
 * Created by Tomdog on 2018/10/24.
 * 这是以反射的方式在base BaseApplication中初始化的
 */

public class MyApplication implements IComponentApplication {

    @Override
    public void onCreate(BaseApplication application) {
        // 在使用 SDK 各组间之前初始化 context 信息，传入 ApplicationContext
        SDKInitializer.initialize(application);
        //自4.3.0起，百度地图SDK所有接口均支持百度坐标和国测局坐标，用此方法设置您使用的坐标类型.
        //包括BD09LL和GCJ02两种坐标，默认是BD09LL坐标。
        SDKInitializer.setCoordType(CoordType.BD09LL);
    }

    @Override
    public Application getAppliaction() {
        return null;
    }
}
