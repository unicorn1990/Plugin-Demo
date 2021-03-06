package com.bf.qinx.hostofplugin;

import android.content.ComponentName;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;
import com.bf.qinx.hostofplugin.loadByStaticProxy.ProxyActivity;
import com.bf.qinx.hostofplugin.merge.Plugin;

public class MainActivity extends FragmentActivity {

    private static final String TAG = "MainActivity";
    private static final String PATCH_ACTIVITY = "com.example.warcraft.plugin.PatchActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Plugin.checkResUpdate(this);
        //ClassLoader.getSystemClassLoader();
        System.out.println("luson3");
        findViewById(R.id.btn1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startCosplayActivity();

                startPatchActivityFormAMS();

//                statPatchActivityFromInstrumentation();
            }
        });

        findViewById(R.id.btn2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //loadApplicationRes = true;
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

        findViewById(R.id.btn3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //loadApplicationRes = true;
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

        findViewById(R.id.btn4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //loadApplicationRes = false;
                Intent intent = new Intent(MainActivity.this,SecondActivity.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.btn5).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                //先移除所有fragment
                for(Fragment iterfragment:fragmentManager.getFragments()){
                    fragmentManager.beginTransaction().remove(iterfragment).commit();
                }
            }
        });

        //
        ClassLoader loader = getClassLoader();
        while(loader!=null){
            Log.i(TAG,loader.getClass().toString());
            Log.i(TAG,loader.toString());
            loader = loader.getParent();
        }
    }

    private boolean loadApplicationRes = false;

    @Override
    public Resources getResources() {
        Log.i("MainActivity",getApplication()==null ? "application null ":getApplication().toString());
        loadApplicationRes = false;
        if(loadApplicationRes){
            return (getApplication() != null && getApplication().getResources()!= null)
                    ? getApplication().getResources()
                    : super.getResources();
        }else{
            return super.getResources();
        }
    }

    private void startPatchActivityFormAMS(){
        Intent intent = new Intent();
        ComponentName componentName = new ComponentName("com.example.warcraft" , PATCH_ACTIVITY);
        intent.setComponent(componentName);
        startActivity(intent);
    }

    private void statPatchActivityFromInstrumentation(){
        Intent intent = new Intent();
        ComponentName componentName = new ComponentName(MainActivity.this , PATCH_ACTIVITY);
        intent.setComponent(componentName);
        startActivity(intent);
    }

    private void startCosplayActivity(){
        Intent intent = new Intent();
        intent.setClass(MainActivity.this, ProxyActivity.class);
        intent.putExtra(ProxyActivity.PLUGIN_STUB, ProxyActivity.PLUGIN_CLASS_NAME);
        startActivity(intent);
    }


    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }
}
