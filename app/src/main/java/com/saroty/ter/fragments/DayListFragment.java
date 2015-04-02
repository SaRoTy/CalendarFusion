package com.saroty.ter.fragments;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.saroty.ter.R;
import com.saroty.ter.ScheduleApplication;
import com.saroty.ter.activities.MainActivity;
import com.saroty.ter.adapters.CoursesViewPagerAdapter;
import com.saroty.ter.schedule.Course;
import com.saroty.ter.schedule.Schedule;
import com.saroty.ter.tasks.AdaptScheduleTask;
import com.saroty.ter.time.DayOfWeek;
import com.saroty.ter.time.LocalTimeInterval;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Calendar;
import java.util.TreeMap;
import java.util.concurrent.ExecutionException;

/**
 * Created by Romain on 31/03/2015.
 */
public class DayListFragment extends Fragment
{

    private CoursesViewPagerAdapter myViewPagerAdapter;
    private ViewPager mViewPager;
    private int mDay;
    private int mWeek;

    public DayListFragment()
    {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.courses_view_pager, container, false);
        mViewPager = (ViewPager) rootView.findViewById(R.id.pager);

        myViewPagerAdapter =
                new CoursesViewPagerAdapter(getChildFragmentManager(),this);

        mViewPager.setAdapter(myViewPagerAdapter);

        this.mWeek = Calendar.getInstance().get(Calendar.WEEK_OF_YEAR);
        this.mDay = Calendar.getInstance().get(Calendar.DAY_OF_WEEK);

        mViewPager.setCurrentItem(mDay+6-1);

        return rootView;
    }

    public int getmWeek()
    {
        return this.mWeek;
    }

    public void setmWeek(int week)
    {
        this.mWeek = week;
    }

    public int getmDay()
    {
        return this.mDay;
    }

    public void setmDay(int day)
    {
        this.mDay = day;
    }


}
