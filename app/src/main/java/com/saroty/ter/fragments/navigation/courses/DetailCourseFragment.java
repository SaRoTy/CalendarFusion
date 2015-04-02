package com.saroty.ter.fragments.navigation.courses;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.saroty.ter.R;
import com.saroty.ter.activities.MainActivity;

import java.io.Serializable;

/**
 * Created by Romain on 02/04/2015.
 */
public class DetailCourseFragment extends Fragment {

    private static final String DESCRIBABLE_KEY_COURSE = "describable_key_course";
    private static final String DESCRIBABLE_KEY_LOCALTIME = "describable_key_localtime";

    private Button mBack;

    public static DetailCourseFragment newInstance(Serializable course, Serializable localtime) {
        DetailCourseFragment fragment = new DetailCourseFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(DESCRIBABLE_KEY_COURSE, course);
        bundle.putSerializable(DESCRIBABLE_KEY_LOCALTIME, localtime);
        fragment.setArguments(bundle);

        return fragment;

    }

    public DetailCourseFragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_detail_course, container, false);
        //this.mBack = (Button)rootView.findViewById(R.id.back);
        Bundle bundle = getArguments();

        this.mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)getActivity()).fragmentBack();
            }
        });

        //((TextView) rootView.findViewById(R.id.detail_title)).setText((String) bundle.get("title"));
        ((TextView)rootView.findViewById(R.id.detail_time)).setText((String)bundle.get("time"));
        ((TextView)rootView.findViewById(R.id.detail_room)).setText((String)bundle.get("room"));

        setHasOptionsMenu(true);

        return rootView;
    }





}
