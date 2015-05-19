package com.saroty.ter.fragments.navigation.day;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.saroty.ter.R;
import com.saroty.ter.ScheduleApplication;
import com.saroty.ter.adapters.CoursesViewPagerAdapter;
import com.saroty.ter.fragments.navigation.NavigationFragment;

import java.util.TimeZone;

import hirondelle.date4j.DateTime;

/**
 * Created by Romain on 31/03/2015.
 */
public class DaysNavigationFragment extends NavigationFragment
{

    private CoursesViewPagerAdapter myViewPagerAdapter;
    private ViewPager mViewPager;
    private DateTime mBaseDay;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {

        View rootView = inflater.inflate(R.layout.courses_view_pager, container, false);
        Bundle b;
        int current = 0;

        mViewPager = (ViewPager) rootView.findViewById(R.id.pager);

        this.mBaseDay = DateTime.today(TimeZone.getDefault());

        if ((b = getArguments()) != null)
        {
            current = mBaseDay.numDaysFrom(
                    DateTime.forDateOnly(b.getInt("year"), b.getInt("month"), b.getInt("dayOfMonth"))
            );
        }

        myViewPagerAdapter =
                new CoursesViewPagerAdapter(getChildFragmentManager(), this);

        mViewPager.setAdapter(myViewPagerAdapter);

        mViewPager.setCurrentItem(current);

        return rootView;
    }

    public DateTime getBaseDay()
    {
        return this.mBaseDay;
    }

    @Override
    public String getNavigationTitle()
    {
        return ScheduleApplication.getContext().getString(R.string.title_navigation_days);
    }

    @Override
    public String getActionbarTitle()
    {
        return ScheduleApplication.getContext().getString(R.string.title_days);
    }
}
