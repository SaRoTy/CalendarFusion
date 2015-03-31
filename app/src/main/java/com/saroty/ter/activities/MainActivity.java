package com.saroty.ter.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.saroty.ter.R;
import com.saroty.ter.adapters.NavigationRowAdapter;
import com.saroty.ter.fragments.ListCoursesOfDayFragment;
import com.saroty.ter.fragments.ScheduleListFragment;
import com.saroty.ter.models.list.NavigationRowModel;

public class MainActivity extends ActionBarActivity
{
    private final NavigationRowModel[] mNavigationModel = {new NavigationRowModel("Accueil"), new NavigationRowModel("Calendriers"), new NavigationRowModel("Options")};
    private final Class[] mNavigationFragments = {ListCoursesOfDayFragment.class, ScheduleListFragment.class, null};
    private ListView mNavigationListView;
    private DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDrawerLayout = ((DrawerLayout) findViewById(R.id.drawer_layout));

        mNavigationListView = ((ListView) findViewById(R.id.drawer_list));
        mNavigationListView.setAdapter(new NavigationRowAdapter(this, mNavigationModel));

        if (savedInstanceState == null)
        {

        }

        mNavigationListView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                onDrawerListItemClick(parent, view, position, id);
            }
        });
    }

    private void onDrawerListItemClick(AdapterView<?> parent, View view, int position, long id)
    {
        ((NavigationRowAdapter) mNavigationListView.getAdapter()).setSelectedElement(position);
        mDrawerLayout.closeDrawers();

        try
        {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.frame_container, (Fragment) mNavigationFragments[position].newInstance())
                    .commit();
        } catch (InstantiationException e)
        {
            e.printStackTrace();
        } catch (IllegalAccessException e)
        {
            e.printStackTrace();
        }

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
        if (id == R.id.action_plus)
        {
            Log.v("ActionBar", "Plus");
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
