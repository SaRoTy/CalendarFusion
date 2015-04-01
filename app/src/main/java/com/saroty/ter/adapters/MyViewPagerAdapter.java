package com.saroty.ter.adapters;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.saroty.ter.activities.MainActivity;
import com.saroty.ter.fragments.ListCoursesOfDayFragment;

/**
 * Created by Romain on 31/03/2015.
 */
public class MyViewPagerAdapter extends FragmentStatePagerAdapter {

    private int mlastPosition;
    private MainActivity mActivity;

    public MyViewPagerAdapter(FragmentManager fm,MainActivity activity) {
        super(fm);
        this.mActivity = activity;
    }

    @Override
    public Fragment getItem(int i) {
        Fragment fragment = new ListCoursesOfDayFragment(i%7
                ,this.mActivity.getmWeek()-1+i/7);

        Bundle args = new Bundle();
        // Our object is just an integer :-P
        args.putInt("objet", i + 1);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public int getCount() {
        return 100;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return ((ListCoursesOfDayFragment)this.getItem(position)).getTitle();
    }
}