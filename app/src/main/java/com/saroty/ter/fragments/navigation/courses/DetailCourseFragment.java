package com.saroty.ter.fragments.navigation.courses;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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

    private TextView mTimeStartView;
    private TextView mTimeEndView;
    private TextView mRoomView;
    private TextView mDescriptionView;
    private TextView mCourseTitleView;
    private View mColorView;

    private LocalTimeInterval mInterval;
    private Course mCourse;

    public static DetailCourseFragment newInstance(LocalTimeInterval interval, Course course)
    {
        DetailCourseFragment fragment = new DetailCourseFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(DESCRIBABLE_KEY_INTERVAL, interval);
        bundle.putSerializable(DESCRIBABLE_KEY_COURSE, course);
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

        mCourseTitleView = (TextView) rootView.findViewById(R.id.text_name);
        mTimeStartView = (TextView) rootView.findViewById(R.id.text_time_start);
        mTimeEndView = (TextView) rootView.findViewById(R.id.text_time_end);
        mRoomView = (TextView) rootView.findViewById(R.id.text_room);
        mDescriptionView = (TextView) rootView.findViewById(R.id.text_desc);
        mColorView = rootView.findViewById(R.id.course_color);

        mCourseTitleView.setText(mCourse.getTitle());
        mTimeStartView.setText(mInterval.getStart().toString());
        mTimeEndView.setText(mInterval.getEnd().toString());
        mRoomView.setText(mCourse.getRoom());
        mDescriptionView.setText(mCourse.getDescription());
        mColorView.setBackgroundColor(mCourse.getColor());

        setHasOptionsMenu(true);

        return rootView;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater)
    {
        ((MainActivity) getActivity()).getSupportActionBar().setTitle(R.string.title_course_details);
        super.onCreateOptionsMenu(menu, inflater);
    }
}
