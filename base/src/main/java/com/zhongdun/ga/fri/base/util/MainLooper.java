package com.zhongdun.ga.fri.base.util;

import android.os.Handler;
import android.os.Looper;

/**
 * Created by Tomdog on 2018/10/29.
 */

public class MainLooper extends Handler {
    private static MainLooper mainLooper = new MainLooper(Looper.getMainLooper());
    protected MainLooper(Looper looper){
        super(looper);
    }
    public static MainLooper getMainLooper(){
        return mainLooper;
    }
    public static void runOnUIThread(Runnable runnable){
        if(Looper.getMainLooper().equals(Looper.myLooper())){
            runnable.run();
        }else {
            mainLooper.post(runnable);
        }
    }
}
