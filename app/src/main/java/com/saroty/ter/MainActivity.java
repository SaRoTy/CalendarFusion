package com.saroty.ter;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.saroty.ter.adapter.Adapter;
import com.saroty.ter.adapter.exception.NoAdapterFoundException;
import com.saroty.ter.adapter.factory.AdapterFactory;
import com.saroty.ter.task.AdaptScheduleTask;

import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends ActionBarActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AdapterFactory factory = new AdapterFactory();

        AdaptScheduleTask task = new AdaptScheduleTask();
        try
        {
            task.execute(new URL("https://celcatfsi.ups-tlse.fr/FSIpargroupes/g558.xml"));
        } catch (MalformedURLException e)
        {
            e.printStackTrace();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings)
        {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
