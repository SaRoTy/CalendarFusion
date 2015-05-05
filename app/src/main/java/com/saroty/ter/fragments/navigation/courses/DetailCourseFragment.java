package com.saroty.ter.fragments.navigation.courses;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import com.saroty.ter.R;
import com.saroty.ter.activities.MainActivity;
import com.saroty.ter.schedule.Course;
import com.saroty.ter.time.LocalTimeInterval;

/**
 * Created by Romain on 02/04/2015.
 */
public class DetailCourseFragment extends Fragment
{

    private static final String DESCRIBABLE_KEY_COURSE = "describable_key_course";
    private static final String DESCRIBABLE_KEY_INTERVAL = "describable_key_interval";

    private Bundle mBundle;

    private LocalTimeInterval mInterval;
    private Course mCourse;

    public static DetailCourseFragment newInstance(Pair<LocalTimeInterval, Course> course)
    {
        DetailCourseFragment fragment = new DetailCourseFragment();
        Bundle bundle = new Bundle();
        Log.d("a", course.first.toString());
        bundle.putSerializable(DESCRIBABLE_KEY_INTERVAL, course.first);
        bundle.putSerializable(DESCRIBABLE_KEY_COURSE, course.second);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View rootView = inflater.inflate(R.layout.fragment_detail_course, container, false);

        mBundle = getArguments();

        mCourse = (Course) mBundle.getSerializable(DESCRIBABLE_KEY_COURSE);
        mInterval = (LocalTimeInterval) mBundle.getSerializable(DESCRIBABLE_KEY_INTERVAL);

        //((TextView) rootView.findViewById(R.id.detail_title)).setText((String) bundle.get("title"));
        //((TextView) rootView.findViewById(R.id.detail_time)).setText(mInterval.toString());
        //((TextView) rootView.findViewById(R.id.detail_room)).setText(mCourse.getRoom());

        setHasOptionsMenu(true);

        return rootView;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater)
    {
        ((MainActivity) getActivity()).getSupportActionBar().setTitle((String) mBundle.get("title"));
        super.onCreateOptionsMenu(menu, inflater);
    }
}
