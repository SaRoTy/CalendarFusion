package com.saroty.ter.fragments;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.saroty.ter.R;
import com.saroty.ter.ScheduleApplication;
import com.saroty.ter.adapters.ScheduleRowAdapter;
import com.saroty.ter.models.list.ScheduleRowModel;


public class ScheduleListFragment extends Fragment
{

    public ScheduleListFragment()
    {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View rootView = inflater.inflate(R.layout.fragment_schedule_list, container, false);
        //setContentView(R.layout.fragment_schedule_list);
        final ScheduleRowModel[] list = {new ScheduleRowModel("Emplois du temps L3 2.1", "C", null)};
        ScheduleRowAdapter adapter = new ScheduleRowAdapter(ScheduleApplication.getContext(), list);

        ListView listView = ((ListView) rootView.findViewById(R.id.schedule_list_view));

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                Log.v("OnClick", "okok");
            }
        });

        return rootView;
    }
}
