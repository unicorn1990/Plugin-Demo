package com.bf.qinx.hostofplugin;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.bf.qinx.hostofplugin.merge.Plugin;

/**
 * <ul>
 * <li>Author: luson.he </li>
 * <li>Time: 2020/4/2 下午11:09</li>
 * <li>Contack: unicorn.luson@gmail.com</li>
 * </ul>
 */
public class SecondActivity extends FragmentActivity {


    public SecondActivity() {
        Log.i("PatchActivity","SecondActivity construct");
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_activity);
        Plugin.checkResUpdate(this);
    }

    @Override
    public Resources getResources() {
        return super.getResources();
    }

}
