package com.example.warcraft;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void clickButton(View view) {
        Toast.makeText(this,"来啊，准备出发",Toast.LENGTH_LONG).show();
    }


//    @Override
//    public Resources getResources() {
//        return (getApplication() != null && getApplication().getResources()!= null)
//                ? getApplication().getResources()
//                : super.getResources();
//    }
}
