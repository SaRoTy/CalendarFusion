package com.saroty.ter;

import android.app.Activity;
import android.os.Bundle;

import com.saroty.ter.adapter.factory.AdapterFactory;
import com.saroty.ter.task.AdaptScheduleTask;

import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends Activity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        /*getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);*/
        setContentView(R.layout.activity_main);


        AdaptScheduleTask task = new AdaptScheduleTask(getApplicationContext());
        try
        {
            task.execute(new URL("https://celcatfsi.ups-tlse.fr/FSIpargroupes/g558.xml"));
        } catch (MalformedURLException e)
        {
            e.printStackTrace();
        }
    }
}
