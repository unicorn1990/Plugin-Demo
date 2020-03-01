package com.example.warcraft.plugin;

import android.app.Activity;
import android.os.Bundle;

/**
 * <ul>
 * <li>Author: luson.he </li>
 * <li>Time: 2020/3/1 下午10:56</li>
 * <li>Contack: unicorn.luson@gmail.com</li>
 * </ul>
 */
public abstract class BasePluginActivity
{
    protected Activity mHost;

    protected abstract void onCreate(Bundle paramBundle);

    protected void onDestroy() {}

    protected void onPause() {}

    protected void onRestart() {}

    protected void onResume() {}

    protected void onStart() {}

    protected void onStop() {}

    public void proxy(Activity paramActivity)
    {
        this.mHost = paramActivity;
    }

    public void setContentView(int paramInt)
    {
        this.mHost.setContentView(paramInt);
    }
}
