package com.saroty.ter;

import android.app.Application;
import android.content.Context;

/**
 * Created by Arthur on 10/03/2015.
 */
public class ScheduleApplication extends Application
{
    private static ScheduleApplication instance;

    public static Context getContext()
    {
        return instance.getApplicationContext();
    }

    @Override
    public void onCreate()
    {
        super.onCreate();
        instance = this;
    }
}
