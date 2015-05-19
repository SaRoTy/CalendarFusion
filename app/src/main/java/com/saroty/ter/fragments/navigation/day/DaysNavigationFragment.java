package com.saroty.ter.fragments.navigation;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.saroty.ter.R;
import com.saroty.ter.ScheduleApplication;
import com.saroty.ter.adapters.CoursesViewPagerAdapter;

import java.util.Date;
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
    private int current;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {

        View rootView = inflater.inflate(R.layout.courses_view_pager, container, false);
        Bundle b;
        current=0;

        mViewPager = (ViewPager) rootView.findViewById(R.id.pager);

        this.mBaseDay = DateTime.today(TimeZone.getDefault());

        if((b = getArguments()) != null) {
            current = mBaseDay.numDaysFrom(
                    DateTime.forDateOnly(b.getInt("year"), b.getInt("month"), b.getInt("dayOfMonth"))
            );
        }else{
            current = 0;
        }

        myViewPagerAdapter =
                new CoursesViewPagerAdapter(getChildFragmentManager(), this);

        mViewPager.setAdapter(myViewPagerAdapter);

        mViewPager.post(new Runnable() {
            @Override
            public void run() {
                mViewPager.setCurrentItem(current, true);
            }
        });

        setHasOptionsMenu(true);

        return rootView;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater)
    {
        if (menu.size() == 0)
            inflater.inflate(R.menu.menu_courses_day_list, menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int id = item.getItemId();

        if (id == R.id.action_add_schedule)
        {

            AddItemDialogFragment f = AddItemDialogFragment.newInstance(this);
            f.show(getFragmentManager(), "AddScheduleDialogFragment");
            return true;
        }

        return super.onOptionsItemSelected(item);
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

    @Override
    public void validate() {

    }
}
