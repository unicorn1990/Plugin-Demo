package com.example.warcraft;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import androidx.fragment.app.FragmentActivity;

import com.example.warcraft.plugin.fragment.PatchResFragment;

public class MainActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    private Fragment currentFragment = null;
    public void clickButton(View view) {

       FrameLayout frameLayout = findViewById(R.id.frameLayout);

        FragmentManager  fragmentManager = getSupportFragmentManager();
        currentFragment = new PatchResFragment();
        fragmentManager.beginTransaction().add(frameLayout.getId(),currentFragment).commit();
       // Toast.makeText(this,"来啊，准备出发",Toast.LENGTH_LONG).show();
    }


    public void clickButton2(View view) {
        FragmentManager  fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().remove(currentFragment).commit();
    }
}
