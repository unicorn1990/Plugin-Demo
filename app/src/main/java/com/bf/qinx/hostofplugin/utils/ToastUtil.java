package com.bf.qinx.hostofplugin.utils;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.view.Gravity;
import android.widget.Toast;

/**
 * <ul>
 * <li>Author: luson.he </li>
 * <li>Time: 2020/6/14 下午2:23</li>
 * <li>Contack: unicorn.luson@gmail.com</li>
 * </ul>
 */
public class ToastUtil {

    private static boolean isInMainThread() {
        Looper myLooper = Looper.myLooper();
        Looper mainLooper = Looper.getMainLooper();
        return myLooper == mainLooper;
    }

    /**
     * at any thread
     * short time toast
     * @return Toast
     */
    public static void show(final Context context, final String msg) {
        if (isInMainThread()) {
            showToastShort(context, msg);
        } else {
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                @Override
                public void run() {
                    showToastShort(context, msg);
                }
            });
        }
    }


    public static Toast showToastShort(final Context context, final String msg) {
        if (isInMainThread()) {
            Toast.makeText(context,msg,Toast.LENGTH_SHORT).show();
        } else {
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                @Override
                public void run() {
                    showToastShort(context, msg);
                }
            });
        }
        return null;
    }


    public static Toast showToastLong(final Context context, final String msg) {
        if (isInMainThread()) {
            Toast.makeText(context,msg,Toast.LENGTH_LONG).show();
        } else {
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                @Override
                public void run() {
                    showToastLong(context, msg);
                }
            });
        }
        return null;
    }
}
