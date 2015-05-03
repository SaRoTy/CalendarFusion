package com.saroty.ter.fragments.navigation;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.saroty.ter.R;
import com.saroty.ter.adapters.CoursesViewPagerAdapter;

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
        mViewPager = (ViewPager) rootView.findViewById(R.id.pager);

        myViewPagerAdapter =
                new CoursesViewPagerAdapter(getChildFragmentManager(), this);

        mViewPager.setAdapter(myViewPagerAdapter);

        this.mBaseDay = DateTime.today(TimeZone.getDefault());

        mViewPager.setCurrentItem(0);//TODO:Check

        return rootView;
    }

    public DateTime getBaseDay()
    {
        return this.mBaseDay;
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
