package com.saroty.ter.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.saroty.ter.Listener.MyListener;
import com.saroty.ter.R;
import com.saroty.ter.adapters.ListCourseOfDayRowAdapter;
import com.saroty.ter.adapters.MyViewPagerAdapter;
import com.saroty.ter.schedule.Course;
import com.saroty.ter.schedule.Schedule;
import com.saroty.ter.tasks.AdaptScheduleTask;
import com.saroty.ter.time.LocalTimeInterval;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Calendar;
import java.util.TreeMap;
import java.util.concurrent.ExecutionException;

/**
 * Created by Romain on 31/03/2015.
 */
public class MyViewPager extends Fragment {

    private MyViewPagerAdapter myViewPagerAdapter;
    private ViewPager mViewPager;

    public MyViewPager() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_my_view_pager, container, false);

        myViewPagerAdapter =
                new MyViewPagerAdapter(getActivity().getSupportFragmentManager());

        mViewPager = (ViewPager) rootView.findViewById(R.id.pager);
        mViewPager.setAdapter(myViewPagerAdapter);

        return rootView;
    }

}
