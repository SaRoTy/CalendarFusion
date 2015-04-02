package com.saroty.ter.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.saroty.ter.R;
import com.saroty.ter.activities.MainActivity;
import com.saroty.ter.schedule.Course;
import com.saroty.ter.schedule.Schedule;
import com.saroty.ter.time.LocalTime;

import java.io.Serializable;

/**
 * Created by Romain on 02/04/2015.
 */
public class DetailCourse extends Fragment {

    private static final String DESCRIBABLE_KEY_COURSE = "describable_key_course";
    private static final String DESCRIBABLE_KEY_LOCALTIME = "describable_key_localtime";

    public static DetailCourse newInstance(Serializable course, Serializable localtime) {
        DetailCourse fragment = new DetailCourse();
        Bundle bundle = new Bundle();
        bundle.putSerializable(DESCRIBABLE_KEY_COURSE, course);
        bundle.putSerializable(DESCRIBABLE_KEY_LOCALTIME, localtime);
        fragment.setArguments(bundle);

        return fragment;

    }

    public DetailCourse(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_detail_course, container, false);
        Bundle bundle = getArguments();

        Schedule schedule = ((MainActivity)getActivity()).getCurrentSchedule();

        Course course = (Course)bundle.getSerializable("describable_key_course");
        LocalTime localTime = (LocalTime)bundle.getSerializable("describable_key_localtime");

        ((TextView)rootView.findViewById(R.id.detail_title)).setText(course.getTitle());
        ((TextView)rootView.findViewById(R.id.detail_time)).setText(localTime.toString());
        ((TextView)rootView.findViewById(R.id.detail_room)).setText(course.getRoom());

        return rootView;
    }



}
