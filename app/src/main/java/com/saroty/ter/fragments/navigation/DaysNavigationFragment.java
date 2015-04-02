package com.saroty.ter.fragments.navigation;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.saroty.ter.R;
import com.saroty.ter.adapters.CoursesViewPagerAdapter;

import java.util.Calendar;

/**
 * Created by Romain on 31/03/2015.
 */
public class DaysNavigationFragment extends NavigationFragment
{

    private CoursesViewPagerAdapter myViewPagerAdapter;
    private ViewPager mViewPager;
    private int mDay;
    private int mWeek;

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


    @Override
    public String getNavigationTitle()
    {
        return "Cours";
    }

    @Override
    public String getActionbarTitle()
    {
        return "Liste des cours";
    }
}
