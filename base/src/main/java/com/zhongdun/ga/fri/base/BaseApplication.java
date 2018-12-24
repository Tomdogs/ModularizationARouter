package com.zhongdun.ga.fri.base;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import com.alibaba.android.arouter.launcher.ARouter;
import com.zhongdun.ga.fri.base.router.IComponentApplication;
import com.zhongdun.ga.fri.base.router.ModuleConfig;
import com.zhongdun.ga.fri.base.util.GlobalConfig;
import com.zhongdun.ga.fri.base.util.ILog;

/**
 * Created by Tomdog on 2018/10/26.
 */

public class BaseApplication extends Application {
    private static Activity topActivity;
    private static BaseApplication baseApplication;
    public synchronized static BaseApplication getBaseApplication(){
        return baseApplication;
    }
    public static Activity getTopActivity(){
        return topActivity;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        baseApplication = this;


        if(GlobalConfig.DEBUG){
            ARouter.openLog();     // 打印日志
            ARouter.openDebug();   // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
        }
        ARouter.init( this ); // 尽可能早，推荐在Application中初始化

        //Module类的Application初始化
        modulesApplicationInit();
        //管理所有activity的生命周期
        registerActivityLifecycleCallbacks(new AppActivityLifeManagement());
    }

    private void modulesApplicationInit() {
        for(String moduleImpl : ModuleConfig.MODULESLIST){
            try {
                Class clazz = Class.forName(moduleImpl);
                Object obj = clazz.newInstance();
                if(obj instanceof IComponentApplication){
                    ((IComponentApplication) obj).onCreate(BaseApplication.getBaseApplication());
                }
            } catch (Exception e) {
                ILog.i("module 初始化出错，请检查module的名字是否合法！");
                e.printStackTrace();
            }
        }
    }


    private class AppActivityLifeManagement implements ActivityLifecycleCallbacks{

        @Override
        public void onActivityCreated(Activity activity, Bundle savedInstanceState) {

        }

        @Override
        public void onActivityStarted(Activity activity) {
            ILog.i("ActivityLifecycleCallbacks onActivityStarted ");
            topActivity = activity;
        }

        @Override
        public void onActivityResumed(Activity activity) {

        }

        @Override
        public void onActivityPaused(Activity activity) {

        }

        @Override
        public void onActivityStopped(Activity activity) {

        }

        @Override
        public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

        }

        @Override
        public void onActivityDestroyed(Activity activity) {

        }
    }
}
