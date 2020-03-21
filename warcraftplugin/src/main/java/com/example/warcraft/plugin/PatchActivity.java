package com.example.warcraft.plugin;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.warcraft.R;

/**
 * <ul>
 * <li>Author: luson.he </li>
 * <li>Time: 2020/3/21 下午9:18</li>
 * <li>Contack: unicorn.luson@gmail.com</li>
 * </ul>
 */
public class PatchActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.plugin_activity);
    }

    public void clickButton(View view) {
        Toast.makeText(this,"点击响应",Toast.LENGTH_SHORT).show();
    }

    @Override
        public Resources getResources() {
        Log.i("PatchActivity",getApplication()==null ? "application null ":getApplication().toString());
        return (getApplication() != null && getApplication().getResources()!= null)
                ? getApplication().getResources()
                : super.getResources();
    }
}
