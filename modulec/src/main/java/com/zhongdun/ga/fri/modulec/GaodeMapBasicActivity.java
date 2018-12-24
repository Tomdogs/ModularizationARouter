package com.zhongdun.ga.fri.modulec;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.amap.api.maps2d.AMap;
import com.amap.api.maps2d.MapView;

@Route(path = "/modulec/1")
public class GaodeMapBasicActivity extends Activity implements View.OnClickListener {
    private MapView mapView;
    private AMap aMap;
    private Button basicmap;
    private Button rsmap;
    private RadioGroup mRadioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gaode_map_basic);

        mapView = (MapView) findViewById(R.id.map);
        mapView.onCreate(savedInstanceState);// 此方法必须重写
        init();
    }

    /**
     * 初始化AMap对象
     */
    private void init() {
        if (aMap == null) {
            aMap = mapView.getMap();

        }
        basicmap = (Button)findViewById(R.id.basicmap);
        basicmap.setOnClickListener(this);
        rsmap = (Button)findViewById(R.id.rsmap);
        rsmap.setOnClickListener(this);

        mRadioGroup = (RadioGroup) findViewById(R.id.check_language);

        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.radio_en) {
                    aMap.setMapLanguage(AMap.ENGLISH);
                } else {
                    aMap.setMapLanguage(AMap.CHINESE);
                }
            }
        });
    }

    /**
     * 方法必须重写
     */
    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }

    /**
     * 方法必须重写
     */
    @Override
    protected void onPause() {
        super.onPause();
        mapView.onPause();
    }

    /**
     * 方法必须重写
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }

    /**
     * 方法必须重写
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.basicmap) {
            aMap.setMapType(AMap.MAP_TYPE_NORMAL);// 矢量地图模式

        } else if (i == R.id.rsmap) {
            aMap.setMapType(AMap.MAP_TYPE_SATELLITE);// 卫星地图模式

        }

    }
}
