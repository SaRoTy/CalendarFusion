package com.saroty.ter.adapters;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.saroty.ter.fragments.CoursesViewPager;
import com.saroty.ter.fragments.ListCoursesOfDayFragment;

import java.util.Calendar;

/**
 * Created by Romain on 31/03/2015.
 */
public class CoursesViewPagerAdapter extends FragmentStatePagerAdapter {

    private int mDecalWeek=-1;
    private CoursesViewPager mPager;

    public CoursesViewPagerAdapter(FragmentManager fm, CoursesViewPager pager) {
        super(fm);
        this.mPager = pager;
    }

    @Override
    public Fragment getItem(int i) {
        Fragment fragment = new ListCoursesOfDayFragment();

        Bundle args = new Bundle();
        args.putInt("position", i);
        args.putInt("decal", this.mDecalWeek);

        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public int getCount() {
        return 100;
    }

    public int getmDecal(){
        return this.mDecalWeek;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        Calendar calendar = Calendar.getInstance();
        int week = this.mPager.getmWeek()+this.mDecalWeek+position/7;
        int day = position%7;
        calendar.set(Calendar.WEEK_OF_YEAR,week);
        calendar.set(Calendar.DAY_OF_WEEK,day);

        return day+" "+week;
        //return new SimpleDateFormat("E-d").format(calendar.getTime());
    }
}