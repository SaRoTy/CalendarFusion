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
import com.saroty.ter.schedule.ScheduleManager;
import com.saroty.ter.time.LocalTimeInterval;

import java.util.Map;
import java.util.TreeMap;

import hirondelle.date4j.DateTime;

/**
 * Created by Romain on 24/03/2015.
 */
public class CourseListFragment extends Fragment
{

    private ListView mList;
    private ListCourseOfDayRowAdapter mAdapter;
    private DateTime mDay;

    public CourseListFragment()
    {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View rootView = inflater.inflate(R.layout.fragment_list_courses_of_day, container, false);

        Bundle bundle = getArguments();

        if(bundle != null)
            this.mDay = new DateTime(bundle.getString("day"));


        this.mList = (ListView) rootView.findViewById(R.id.list_courses_of_day);
        loadDailyCourses();

        this.mList.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                DetailCourseFragment fragment = DetailCourseFragment.newInstance(mAdapter.getModel()[position].getInterval(), mAdapter.getModel()[position].getCourse());
                ((MainActivity) getActivity()).setCurrentFragment(fragment, true);
            }
        });

        return rootView;
    }

    private void loadDailyCourses()
    {
        CourseRowModel[] model;
        int i = 0;

        TreeMap<LocalTimeInterval, Course> listDay = ScheduleManager.getInstance().getDailyCourses(mDay);

        model = new CourseRowModel[listDay.size()];

        for (Map.Entry<LocalTimeInterval, Course> course : listDay.entrySet())
        {
            model[i] = new CourseRowModel(course.getKey(), course.getValue());
            i++;
        }

        mAdapter = new ListCourseOfDayRowAdapter(getActivity().getApplicationContext(), model);

        mList.setAdapter(mAdapter);

    }
}
