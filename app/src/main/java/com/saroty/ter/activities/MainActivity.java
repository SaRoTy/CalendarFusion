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
import com.saroty.ter.fragments.MyViewPager;
import com.saroty.ter.fragments.ScheduleListFragment;
import com.saroty.ter.models.list.NavigationRowModel;
import com.saroty.ter.schedule.Course;
import com.saroty.ter.schedule.CourseWeek;
import com.saroty.ter.schedule.Schedule;
import com.saroty.ter.tasks.AdaptScheduleTask;
import com.saroty.ter.time.DayOfWeek;
import com.saroty.ter.time.LocalTimeInterval;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Calendar;
import java.util.TreeMap;
import java.util.concurrent.ExecutionException;

public class MainActivity extends ActionBarActivity
{
    private final NavigationRowModel[] mNavigationModel = {new NavigationRowModel("Accueil"), new NavigationRowModel("Calendriers"), new NavigationRowModel("Options")};
    private final Fragment[] mNavigationFragments;
    private ListView mNavigationListView;
    private DrawerLayout mDrawerLayout;
    private Schedule mSchedule;
    private int mDay;
    private int mWeek;

    public MainActivity(){
        this.mWeek = 32;
        this.mDay = Calendar.DAY_OF_WEEK_IN_MONTH;
        Fragment[] tab = {new ListCoursesOfDayFragment(), new ScheduleListFragment(), null};
        this.mNavigationFragments = tab;

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
                onDrawerListItemClick(parent, view, position, id);
            }
        });

        AdaptScheduleTask a = new AdaptScheduleTask(getApplicationContext());

        try {
            a.execute(new URL("https://celcatfsi.ups-tlse.fr/FSIpargroupes/g558.xml"));
            this.mSchedule = a.get();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        if(savedInstanceState == null)
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.frame_container,new MyViewPager())
                    .commit();
    }

    private void onDrawerListItemClick(AdapterView<?> parent, View view, int position, long id)
    {
        ((NavigationRowAdapter) mNavigationListView.getAdapter()).setSelectedElement(position);
        mDrawerLayout.closeDrawers();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.frame_container, mNavigationFragments[position])
                .commit();
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

    public TreeMap<LocalTimeInterval,Course> getCours(int day,int week){
        CourseWeek coureweek;

        /*if((coureweek = this.mSchedule.getWeekByWeekNumber(week)) == null)
            return null;

        if(coureweek.getDay(DayOfWeek.getById(day)) == null)
            return null;*/

        try{
            return this.mSchedule.getWeekByWeekNumber(week).getDay(DayOfWeek.getById(day)).getCourses();
        }catch(NullPointerException e){
            return null;
        }

    }

    public void setmWeek(int week){
        this.mWeek = week;
    }

    public void setmDay(int day){
        this.mDay = day;
    }

    public int getmWeek(){
        return this.mWeek;
    }

    public int getmDay(){
        return this.mDay;
    }


}
