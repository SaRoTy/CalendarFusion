package com.saroty.ter.fragments.navigation.courses;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Pair;
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
import com.saroty.ter.time.LocalTimeInterval;

import java.util.List;

import hirondelle.date4j.DateTime;

/**
 * Created by Romain on 24/03/2015.
 */
public class CourseListFragment extends Fragment
{

    private ListView mList;
    private List<Pair<LocalTimeInterval, Course>> mListDay;
    private ListCourseOfDayRowAdapter mAdapter;
    private DateTime mDay;
    private Schedule mSchedule;


    public CourseListFragment()
    {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View rootView = inflater.inflate(R.layout.fragment_list_courses_of_day, container, false);

        Bundle bundle = getArguments();

        this.mDay = new DateTime(bundle.getString("day"));

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
                DetailCourseFragment fragment = DetailCourseFragment.newInstance(mListDay.get(position));
                ((MainActivity) getActivity()).setCurrentFragment(fragment, true);
            }
        });

        return rootView;
    }

    private void loadDailyCourses()
    {
        CourseRowModel[] model;
        int i = 0;

        mListDay = mSchedule.getDailyCourses(mDay);

        model = new CourseRowModel[mListDay.size()];

        for (Pair<LocalTimeInterval, Course> course : mListDay)
        {
            model[i] = new CourseRowModel(course.second.getTitle(), course.first, course.second.getRoom(), course.second.getColor());
            i++;
        }

        mAdapter = new ListCourseOfDayRowAdapter(getActivity().getApplicationContext(), model);

        mList.setAdapter(mAdapter);

    }
}
