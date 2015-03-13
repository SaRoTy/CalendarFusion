package com.saroty.ter.activities;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.saroty.ter.R;
import com.saroty.ter.adapters.ScheduleRowAdapter;
import com.saroty.ter.models.list.ScheduleRowModel;


public class SchedulesListActivity extends ActionBarActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedules_list);
        //String[] list = ScheduleFileUtil.getScheduleList();
        final ScheduleRowModel[] list = {new ScheduleRowModel("Emplois du temps L3 2.1", "C", null)};
        ScheduleRowAdapter adapter = new ScheduleRowAdapter(this, list);

        ListView listView = ((ListView) findViewById(R.id.listView));

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                Log.v("OnClick", "okok");
            }
        });

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
