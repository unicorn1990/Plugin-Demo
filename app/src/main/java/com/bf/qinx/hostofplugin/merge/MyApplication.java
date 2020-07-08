package com.bf.qinx.hostofplugin.merge;

import android.app.Application;
import android.content.Context;
import android.content.res.Resources;
import android.util.Log;

import com.bf.qinx.hostofplugin.HookMode;
import com.bf.qinx.hostofplugin.loadByHookAms.AMSHookHelper;
import com.bf.qinx.hostofplugin.loadByHookInstrumentation.InstrumentationHookHelper;

import java.util.Locale;

import dalvik.system.PathClassLoader;

/**
 * Created by QinX on 2019-06-28.
 * <p>
 * Description :
 */
public class MyApplication extends Application {

    private static final String TAG = "MyApplication";

    @Override
    public void onCreate() {
        super.onCreate();
    }

//    private MyApplication(){
//        Log.i("lusonTest","MyApplication");
//    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);

        try {
            /**
             * 方式一：自行管理Instrumentation
             */
            if(HookMode.currentMode == HookMode.HOOK_MODE_INSTRUMENTATION){
                InstrumentationHookHelper.hookInstrumentation(base);
            }
            /**
             * 方式二：代理AMS
             */
            else if(HookMode.currentMode == HookMode.HOOK_MODE_AMS){
                AMSHookHelper.hook(base);
            }
            /**
             * 方式三：静态代理
             */
            else if(HookMode.currentMode == HookMode.HOOK_MODE_STATIC_PROXY){
                // 没错，方式三这里什么都不用写 //
            }


            Log.i(TAG,String.format(Locale.CHINA,"before load plugin:%s",getClassLoader().toString()));

            Plugin.loadPluginDex(this, getClassLoader());

            Log.i(TAG,String.format(Locale.CHINA,"after load plugin:%s",getClassLoader().toString()));


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Resources getResources() {
        return Plugin.getPluginResources() == null
                ? super.getResources()
                : Plugin.getPluginResources();
    }
}
