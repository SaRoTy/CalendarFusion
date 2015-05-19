package com.saroty.ter.adapters;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.saroty.ter.fragments.navigation.courses.CourseListFragment;
import com.saroty.ter.fragments.navigation.day.DaysNavigationFragment;

import java.util.Locale;

/**
 * Created by Romain on 31/03/2015.
 */
public class CoursesViewPagerAdapter extends FragmentStatePagerAdapter
{
    private DaysNavigationFragment mPager;

    public CoursesViewPagerAdapter(FragmentManager fm, DaysNavigationFragment pager)
    {
        super(fm);
        mPager = pager;
    }

    @Override
    public Fragment getItem(int i)
    {
        Fragment fragment = new CourseListFragment();

        Bundle args = new Bundle();
        args.putString("day", mPager.getBaseDay().plusDays(i).format("YYYY-MM-DD"));
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public int getCount()
    {
        return 100;
    }

    //Empêche le call du super par défaut, sinon pas d'affichage
    @Override
    public void restoreState(Parcelable state, ClassLoader loader)
    {
    }

    @Override
    public CharSequence getPageTitle(int position)
    {
        String dateString = mPager.getBaseDay().plusDays(position).format("WWWW DD/MM", Locale.getDefault());
        return Character.toUpperCase(dateString.charAt(0)) + dateString.substring(1);
    }
}