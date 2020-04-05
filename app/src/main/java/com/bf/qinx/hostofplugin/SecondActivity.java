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

/**
 * <ul>
 * <li>Author: luson.he </li>
 * <li>Time: 2020/4/2 下午11:09</li>
 * <li>Contack: unicorn.luson@gmail.com</li>
 * </ul>
 */
public class SecondActivity extends FragmentActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.second_activity);

        Button btn2 = findViewById(R.id.btn2);
        if(btn2!=null)
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                Fragment currentFragment = null;
                try {
                    currentFragment = (Fragment) Class.forName("com.example.warcraft.plugin.fragment.PatchNoResFragment").newInstance();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                //先移除所有fragment
                for(Fragment iterfragment:fragmentManager.getFragments()){
                    fragmentManager.beginTransaction().remove(iterfragment).commit();
                }

                fragmentManager.beginTransaction().add(R.id.frameLayout,currentFragment).commit();
            }
        });

        Button btn3 = findViewById(R.id.btn3);
        if(btn3!=null)
        findViewById(R.id.btn3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                Fragment currentFragment = null;
                try {
                    currentFragment = (Fragment) Class.forName("com.example.warcraft.plugin.fragment.PatchResFragment").newInstance();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }

                //先移除所有fragment
                for(Fragment iterfragment:fragmentManager.getFragments()){
                    fragmentManager.beginTransaction().remove(iterfragment).commit();
                }
                fragmentManager.beginTransaction().add(R.id.frameLayout,currentFragment).commit();
            }
        });
    }

//    @Override
//    public Resources getResources() {
//        return (getApplication() != null && getApplication().getResources() != null)
//                ? getApplication().getResources()
//                : super.getResources();
//    }
}
