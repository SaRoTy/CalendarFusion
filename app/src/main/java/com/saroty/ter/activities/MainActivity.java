package com.saroty.ter.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.saroty.ter.R;
import com.saroty.ter.adapters.NavigationRowAdapter;
import com.saroty.ter.fragments.DayListFragment;
import com.saroty.ter.fragments.HomeFragment;
import com.saroty.ter.fragments.ScheduleListFragment;
import com.saroty.ter.models.list.NavigationRowModel;
import com.saroty.ter.schedule.Schedule;

import java.util.ArrayList;

public class MainActivity extends ActionBarActivity
{
    //TODO: Regrouper les tableau dans une Structure
    private final NavigationRowModel[] mNavigationModel = {new NavigationRowModel("Accueil"), new NavigationRowModel("Calendriers"), new NavigationRowModel("Cours")};
    private final Fragment[] mNavigationFragments = {new HomeFragment(), new ScheduleListFragment(), new DayListFragment()};
    private final int[] mNavigationFragmentsMenus = {R.menu.menu_empty, R.menu.menu_schedule_list, R.menu.menu_empty};

    private ArrayList<Schedule> mSchedules = new ArrayList<Schedule>();
    private int mCurrentSchedule = -1;
    private int mNavigationPosition;

    private ListView mNavigationListView;
    private DrawerLayout mDrawerLayout;

    public boolean hasCurrentSchedule()
    {
        return (mCurrentSchedule != -1);
    }

    public Schedule getCurrentSchedule()
    {
        if (hasCurrentSchedule())
            return mSchedules.get(mCurrentSchedule);
        else
            return null;
    }

    public void setCurrentSchedule(Schedule s)
    {
        for (int i = 0; i < mSchedules.size(); i++)
        {
            if (mSchedules.get(i) == s)
            {
                mCurrentSchedule = i;
                return;
            }
        }
    }

    public ArrayList<Schedule> getSchedules()
    {
        return mSchedules;
    }

    public void addSchedule(Schedule s)
    {
        mSchedules.add(s);
        if(!hasCurrentSchedule())
            setCurrentSchedule(s);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDrawerLayout = ((DrawerLayout) findViewById(R.id.drawer_layout));

        mNavigationListView = ((ListView) findViewById(R.id.drawer_list));
        mNavigationListView.setAdapter(new NavigationRowAdapter(this, mNavigationModel));

        mNavigationListView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                onDrawerListItemClick(position);
            }
        });

        if (savedInstanceState == null)
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.frame_container, mNavigationFragments[mNavigationPosition])
                    .commit();


    }

    private void onDrawerListItemClick(int position)
    {
        mNavigationPosition = position;
        ((NavigationRowAdapter) mNavigationListView.getAdapter()).setSelectedElement(position);
        mDrawerLayout.closeDrawers();

        setCurrentFragment(mNavigationFragments[position],false);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_schedule_list, menu);
        return true;
    }

    public void setCurrentFragment(Fragment fragment, boolean backStack)
    {
        if(backStack)
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.frame_container, fragment)
                    .addToBackStack(null)
                    .commit();
        else
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.frame_container, fragment)
                    .commit();

    }

    public void fragmentBack(){
        getSupportFragmentManager().popBackStack();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        return mNavigationFragments[mNavigationPosition].onOptionsItemSelected(item);
    }
}