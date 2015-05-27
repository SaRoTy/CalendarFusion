package com.saroty.ter.fragments.navigation.filter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TabHost;

import com.saroty.ter.R;
import com.saroty.ter.adapters.ListCourseRowAdapter;
import com.saroty.ter.models.list.ManagerCourseRowModel;
import com.saroty.ter.schedule.Course;
import com.saroty.ter.schedule.Schedule;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by Romain on 27/05/2015.
 */
public class CreateFilterFragment extends Fragment {

    private ListView mList;
    private Schedule mSchedule;
    private EditText mSearchEditText;
    private ListCourseRowAdapter mAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_filter_create, container, false);

        mSchedule = (Schedule)getArguments().getSerializable("schedule");

        mList = (ListView)rootView.findViewById(R.id.list_course);

        mSearchEditText = (EditText)rootView.findViewById(R.id.search_text);


        Set<ManagerCourseRowModel> set = mSchedule.getSetEvent();

        List<ManagerCourseRowModel> tab = new ArrayList<>(set);

        mAdapter = new ListCourseRowAdapter(getActivity(),tab);

        mList.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);

        mList.setAdapter(mAdapter);

        mSearchEditText.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
                // When user changed the Text
                mAdapter.getFilter().filter(cs);
            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
                                          int arg3) {
                // TODO Auto-generated method stub

            }

            @Override
            public void afterTextChanged(Editable arg0) {
                // TODO Auto-generated method stub
            }
        });

        return rootView;
    }


}
