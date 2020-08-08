package com.bf.qinx.hostofplugin;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import androidx.annotation.IntDef;

/**
 * <ul>
 * <li>Author: luson.he </li>
 * <li>Time: 2020/7/8 下午10:07</li>
 * <li>Contack: unicorn.luson@gmail.com</li>
 * </ul>
 */
public class HookMode{



    private HookMode() {
    }


    //hook instrumentation
    public static final int HOOK_MODE_INSTRUMENTATION = 1;
    //hook ams
    public static final int HOOK_MODE_AMS = 2;
    //静态代理
    public static final int HOOK_MODE_STATIC_PROXY = 3;

    @IntDef({HOOK_MODE_AMS, HOOK_MODE_INSTRUMENTATION, HOOK_MODE_STATIC_PROXY})
    @Retention(RetentionPolicy.SOURCE)
    public @interface MODE {}

    public static @MODE int currentMode = HOOK_MODE_STATIC_PROXY;

//    private static volatile HookMode instance = null;
//    private static HookMode getInstance(){
//        if(instance == null){
//            synchronized (HookMode.class){
//                if(instance == null){
//                    instance = new HookMode();
//                }
//            }
//        }
//
//        return instance;
//    }


}
