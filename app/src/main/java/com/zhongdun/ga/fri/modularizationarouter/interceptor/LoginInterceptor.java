package com.zhongdun.ga.fri.modularizationarouter.interceptor;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.annotation.Interceptor;
import com.alibaba.android.arouter.facade.callback.InterceptorCallback;
import com.alibaba.android.arouter.facade.template.IInterceptor;
import com.zhongdun.ga.fri.base.BaseApplication;
import com.zhongdun.ga.fri.base.util.ILog;
import com.zhongdun.ga.fri.base.util.MainLooper;

/**
 * Created by Tomdog on 2018/10/29.
 */
@Interceptor(priority = 7)
public class LoginInterceptor implements IInterceptor {
    Context mcontext;
    @Override
    public void process(final Postcard postcard, final InterceptorCallback callback) {
        if ("/test/login".equals(postcard.getPath())) {
            ILog.i("当前线程",""+Thread.currentThread().getName());
            final AlertDialog.Builder builder = new AlertDialog.Builder(BaseApplication.getTopActivity());
            builder.setCancelable(false);
            builder.setTitle("拦截提醒");
            builder.setMessage("触发的登录的拦截跳转");
            builder.setNegativeButton("继续", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    callback.onContinue(postcard);
                }
            });
            builder.setNeutralButton("算了", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    callback.onInterrupt(null);
                }
            });
            builder.setPositiveButton("添加参数", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    postcard.withString("extra","123456");
                    callback.onContinue(postcard);
                }
            });

            MainLooper.runOnUIThread(new Runnable() {
                @Override
                public void run() {
                    builder.create().show();
                }
            });

        }else {
            callback.onContinue(postcard);
        }
    }

    @Override
    public void init(Context context) {
        mcontext = context;
    }
}
