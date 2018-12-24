package com.zhongdun.ga.fri.base.router;

import android.app.Application;

import com.zhongdun.ga.fri.base.BaseApplication;

/**
 * Created by Tomdog on 2018/10/26.
 */

public interface IComponentApplication {
    void onCreate(BaseApplication application);

    Application getAppliaction();
}
