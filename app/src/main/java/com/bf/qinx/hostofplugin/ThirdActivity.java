package com.bf.qinx.hostofplugin;

import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.bf.qinx.hostofplugin.merge.Plugin;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;

/**
 * <ul>
 * <li>Author: luson.he </li>
 * <li>Time: 2020/4/2 下午11:09</li>
 * <li>Contack: unicorn.luson@gmail.com</li>
 * </ul>
 */
public class ThirdActivity extends FragmentActivity {


    public ThirdActivity() {
        Log.i("ThirdActivity","ThirdActivity construct");
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.third_activity);
//        Plugin.checkResUpdate(this);
    }

    @Override
    public Resources getResources() {
        return super.getResources();
    }
}
