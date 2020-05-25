package com.bf.qinx.hostofplugin;

import android.content.res.Resources;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;

import android.util.Log;
import android.view.View;

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


    public void getString(View view) {
        getString(R.string.app_name);
    }

    @Override
    public Resources getResources() {
        return super.getResources();
    }

    public void getDrawable(View view) {
        getResources().getDrawable(R.drawable.foreigner);
    }
}
