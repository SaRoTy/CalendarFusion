package com.saroty.ter.activities;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.saroty.ter.R;
import com.saroty.ter.schedule.util.ScheduleFileUtil;


public class SchedulesListActivity extends ActionBarActivity
{
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedules_list);
        //String[] list = ScheduleFileUtil.getScheduleList();
        String[] list = {"Swagg schedule", "Even swagger schedule"};
        adapter=new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,
                list);
        ((ListView) findViewById(R.id.listView)).setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_schedules_list, menu);
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
        /*if (id == R.id.action_settings)
        {
            return true;
        }*/

        return super.onOptionsItemSelected(item);
    }
}
