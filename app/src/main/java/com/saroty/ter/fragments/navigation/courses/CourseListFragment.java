package com.saroty.ter.fragments.navigation.courses;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
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
    private TreeMap<LocalTimeInterval, Course> mListDay;
    private ListCourseOfDayRowAdapter mAdapter;
    private int mDay;
    private int mWeek;
    private Schedule mSchedule;


    public CourseListFragment()
    {
    }

    public static CourseListFragment newInstance()
    {
        CourseListFragment f = new CourseListFragment();

        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View rootView = inflater.inflate(R.layout.fragment_list_courses_of_day, container, false);

        Bundle bundle = getArguments();

        this.mDay = (int) bundle.get("day");

        this.mWeek = (int) bundle.get("week");

        this.mList = (ListView) rootView.findViewById(R.id.list_courses_of_day);

        if (((MainActivity) getActivity()).hasCurrentSchedule())
        {
            this.mSchedule = ((MainActivity) getActivity()).getCurrentSchedule();
            loadDailyCourses();
        }

        this.mList.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                CourseRowModel courseModel = (CourseRowModel) mList.getItemAtPosition(position);
                DetailCourseFragment fragment = new DetailCourseFragment();
                Bundle bundle = new Bundle();

                bundle.putString("title", courseModel.getName());
                bundle.putString("time", courseModel.getInterval().toString());
                bundle.putString("room", courseModel.getRoom());

                fragment.setArguments(bundle);

                ((MainActivity) getActivity()).setCurrentFragment(fragment, true);
            }
        });

        return rootView;
    }

    private void loadDailyCourses()
    {
        CourseRowModel[] model;
        int i = 0;

        if (mSchedule.getWeekByWeekNumber(this.mWeek) == null)
        {
            return;
        }

        if (mSchedule.getWeekByWeekNumber(this.mWeek).getDay(DayOfWeek.getById(this.mDay)) == null)
            return;

        mListDay = mSchedule
                .getWeekByWeekNumber(this.mWeek).getDay(DayOfWeek.getById(this.mDay)).getCourses();


        model = new CourseRowModel[mListDay.size()];

        for (Map.Entry<LocalTimeInterval, Course> cours : mListDay.entrySet())
        {
            model[i] = new CourseRowModel(cours.getValue().getTitle()
                    , cours.getKey(), cours.getValue().getRoom());
            i++;
        }

        mAdapter = new ListCourseOfDayRowAdapter(getActivity().getApplicationContext(), model);

        mList.setAdapter(mAdapter);

    }
}
