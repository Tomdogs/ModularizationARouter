package com.zhongdun.ga.fri.modularizationarouter;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.facade.callback.NavCallback;
import com.alibaba.android.arouter.launcher.ARouter;
import com.zhongdun.ga.fri.base.util.ILog;
import com.zhongdun.ga.fri.modularizationarouter.util.GlobalConfig;

@Route(path = "/app/main")
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(GlobalConfig.isDebug){
            ARouter.openLog();     // 打印日志
            ARouter.openDebug();   // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
        }
        ARouter.init(getApplication()); // 尽可能早，推荐在Application中初始化
    }
    public void skipPPmap(View view){
        ARouter.getInstance().build("/module/1").navigation();

       /* Intent intent = new Intent(this, com.zhongdun.ga.fri.module.ModuleAMainActivity.class);
        startActivity(intent);*/

        /*try {
            Class clazz = Class.forName("com.zhongdun.ga.fri.module.ModuleAMainActivity");
            startActivity(new Intent(this,clazz));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }*/

    }

    public void skipBaidu(View view){
        ARouter.getInstance().build("/moduleB/1").navigation();
        ILog.i("skipBaidu");
    }

    public void skipGaode(View view){
        ARouter.getInstance().build("/modulec/1").navigation();
    }

    public void login(View view){
        ARouter.getInstance().build("/test/login").navigation(this, new NavCallback() {
            @Override
            public void onFound(Postcard postcard) {
                super.onFound(postcard);
                ILog.i("loginActivity找到了");
            }

            @Override
            public void onArrival(Postcard postcard) {
                ILog.i("loginActivity跳转完了");
            }

            @Override
            public void onInterrupt(Postcard postcard) {
                super.onInterrupt(postcard);
                ILog.i("loginActivity被拦截了");
            }

            @Override
            public void onLost(Postcard postcard) {
                super.onLost(postcard);
                ILog.i("loginActivity找不到了");
            }
        });
    }
}
