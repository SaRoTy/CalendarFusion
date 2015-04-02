package com.saroty.ter.adapters;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.saroty.ter.fragments.CourseListFragment;
import com.saroty.ter.fragments.DayListFragment;

import java.util.Calendar;

/**
 * Created by Romain on 31/03/2015.
 */
public class CoursesViewPagerAdapter extends FragmentStatePagerAdapter
{

    private int mDecalWeek = -1;
    private DayListFragment mPager;

    public CoursesViewPagerAdapter(FragmentManager fm, DayListFragment pager)
    {
        super(fm);
        this.mPager = pager;
    }

    @Override
    public Fragment getItem(int i)
    {
        Fragment fragment = new CourseListFragment();

        Bundle args = new Bundle();
        args.putInt("day", i%7);
        args.putInt("week", this.mPager.getmWeek()+this.mDecalWeek+i/7);

        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public int getCount()
    {
        return 100;
    }

    public int getmDecal()
    {
        return this.mDecalWeek;
    }

    @Override
    public CharSequence getPageTitle(int position)
    {
        Calendar calendar = Calendar.getInstance();
        int week = this.mPager.getmWeek() + this.mDecalWeek + position / 7;
        int day = position % 7;
        calendar.set(Calendar.WEEK_OF_YEAR, week);
        calendar.set(Calendar.DAY_OF_WEEK, day);

        return day + " " + week;
        //return new SimpleDateFormat("E-d").format(calendar.getTime());
    }
}