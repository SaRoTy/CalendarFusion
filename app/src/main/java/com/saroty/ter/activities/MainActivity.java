package com.saroty.ter.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.saroty.ter.R;
import com.saroty.ter.adapters.NavigationRowAdapter;
import com.saroty.ter.database.DatabaseHelper;
import com.saroty.ter.fragments.navigation.day.DaysNavigationFragment;
import com.saroty.ter.fragments.navigation.home.HomeNavigationFragment;
import com.saroty.ter.fragments.navigation.NavigationFragment;
import com.saroty.ter.fragments.navigation.schedule.SchedulesNavigationFragment;
import com.saroty.ter.fragments.navigation.week.WeekNavigationFragment;
import com.saroty.ter.schedule.Schedule;
import com.saroty.ter.schedule.ScheduleManager;

public class MainActivity extends ActionBarActivity
{
    private final NavigationFragment[] mNavigationFragments = {new HomeNavigationFragment(), new SchedulesNavigationFragment(), new DaysNavigationFragment(), new WeekNavigationFragment()};

    private DatabaseHelper mDatabaseHelper;
    private ScheduleManager mScheduleManager;

    private int mNavigationPosition;

    private ListView mNavigationListView;
    private DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        mDatabaseHelper = DatabaseHelper.getInstance();

        setContentView(R.layout.activity_main);

        mDrawerLayout = ((DrawerLayout) findViewById(R.id.drawer_layout));

        mNavigationListView = ((ListView) findViewById(R.id.drawer_list));

        mNavigationListView.setAdapter(new NavigationRowAdapter(this, mNavigationFragments));

        mNavigationListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
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

        mDrawerLayout.post(new Runnable() {
            @Override
            public void run() {
                setCurrentFragment(mNavigationFragments[mNavigationPosition], false);
            }
        });

        invalidateOptionsMenu();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getSupportActionBar().setTitle(mNavigationFragments[mNavigationPosition].getActionbarTitle());
        return super.onCreateOptionsMenu(menu);
    }

    public void setCurrentFragment(int position,Bundle bundle)
    {
        if(bundle != null)
            mNavigationFragments[position].setArguments(bundle);

        mNavigationPosition = position;
        ((NavigationRowAdapter) mNavigationListView.getAdapter()).setSelectedElement(position);
        setCurrentFragment(mNavigationFragments[mNavigationPosition], false);
        invalidateOptionsMenu();
    }

    public void setCurrentFragment(Fragment fragment, boolean backStack)
    {
        FragmentTransaction fragmentTransaction;
        if (backStack)
        {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);


            fragmentTransaction.replace(R.id.frame_container, fragment)
                    .addToBackStack(null)
                    .commit();
        } else
        {
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.frame_container, fragment)
                    .commit();
        }

    }

    public boolean popBackStack()
    {
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        if (getSupportFragmentManager().getBackStackEntryCount() > 0)
        {
            getSupportFragmentManager().popBackStack();
            return true;
        }
        return false;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        if (item.getItemId() == android.R.id.home)
        {
            return popBackStack();
        } else
            return mNavigationFragments[mNavigationPosition].onOptionsItemSelected(item);
    }

    public ScheduleManager getScheduleManager(){ return mScheduleManager;}
}