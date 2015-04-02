package com.saroty.ter.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.saroty.ter.R;
import com.saroty.ter.adapters.CoursesViewPagerAdapter;
import com.saroty.ter.schedule.Course;
import com.saroty.ter.schedule.Schedule;
import com.saroty.ter.tasks.AdaptScheduleTask;
import com.saroty.ter.time.DayOfWeek;
import com.saroty.ter.time.LocalTimeInterval;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Calendar;
import java.util.Date;
import java.util.TreeMap;
import java.util.concurrent.ExecutionException;

/**
 * Created by Romain on 31/03/2015.
 */
public class CoursesViewPager extends Fragment {

    private CoursesViewPagerAdapter myViewPagerAdapter;
    private ViewPager mViewPager;
    private int mDay;
    private int mWeek;
    private Schedule mSchedule;

    public CoursesViewPager() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.courses_view_pager, container, false);

        myViewPagerAdapter =
                new CoursesViewPagerAdapter(getFragmentManager(),this);

        mViewPager = (ViewPager) rootView.findViewById(R.id.pager);
        mViewPager.setAdapter(myViewPagerAdapter);

        AdaptScheduleTask a = new AdaptScheduleTask(getActivity().getApplicationContext());

        this.mWeek = 33;
        this.mDay = Calendar.getInstance().get(Calendar.DAY_OF_WEEK);

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

        //TODO : HARDCODE DU decal pour le jour souhaite pas top
        mViewPager.setCurrentItem(this.mDay+6-1);


        return rootView;
    }


    public TreeMap<LocalTimeInterval, Course> getCours(int day, int week)
    {
        try{
            return this.mSchedule.getWeekByWeekNumber(week).getDay(DayOfWeek.getById(day)).getCourses();
        }catch(NullPointerException e){
            return null;
        }
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
