package com.saroty.ter.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.saroty.ter.R;
import com.saroty.ter.activities.MainActivity;
import com.saroty.ter.adapters.ListCourseOfDayRowAdapter;
import com.saroty.ter.models.list.CourseRowModel;
import com.saroty.ter.schedule.Course;
import com.saroty.ter.schedule.Schedule;
import com.saroty.ter.time.DayOfWeek;
import com.saroty.ter.time.LocalTimeInterval;

import java.util.Calendar;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by Romain on 24/03/2015.
 */
public class CourseListFragment extends Fragment
{

    private ListView mList;
    private TreeMap<LocalTimeInterval,Course> mListDay;
    private ListCourseOfDayRowAdapter mAdapter;
    private int mDay;
    private int mWeek;
    private Schedule mSchedule;


    public CourseListFragment()
    {
        this.mListDay = null;
        this.mAdapter = null;
    }

    public static CourseListFragment newInstance()
    {
        CourseListFragment f = new CourseListFragment();

        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_list_courses_of_day, container, false);

        Bundle bundle =  getArguments();

        this.mList = (ListView) rootView.findViewById(R.id.list_courses_of_day);

        mSchedule = ((MainActivity)getActivity()).getCurrentSchedule();

        this.mDay = (int)bundle.get("day");

        this.mWeek = (int)bundle.get("week");

        loadEDT();

        //setHasOptionsMenu(true);

        return rootView;
    }

    /*@Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_course_list, menu);
    }*/

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        return true;
    }


    private void loadEDT(){
        CourseRowModel[] model;
        int i=0;

        try{
            mListDay = mSchedule
                    .getWeekByWeekNumber(this.mWeek).getDay(DayOfWeek.getById(this.mDay)).getCourses();
        }catch(NullPointerException e){
            return;
        }

        model = new CourseRowModel[mListDay.size()];

        for(Map.Entry<LocalTimeInterval,Course> cours : mListDay.entrySet()){
            model[i] = new CourseRowModel(cours.getValue().getTitle(),cours.getKey().toString());
            i++;
        }

        mAdapter = new ListCourseOfDayRowAdapter(getActivity().getApplicationContext(),model);

        Calendar calendar = Calendar.getInstance();

        calendar.set(Calendar.WEEK_OF_YEAR,32);
        calendar.set(Calendar.DAY_OF_WEEK,0);

        mList.setAdapter(mAdapter);

    }
}
