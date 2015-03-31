package com.saroty.ter.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ExpandableListView;

import com.idunnololz.widgets.AnimatedExpandableListView;
import com.saroty.ter.R;
import com.saroty.ter.ScheduleApplication;
import com.saroty.ter.activities.MainActivity;
import com.saroty.ter.adapters.ScheduleGroupAdapter;
import com.saroty.ter.models.list.ScheduleGroupModel;
import com.saroty.ter.models.list.ScheduleRowModel;


public class ScheduleListFragment extends Fragment
{
    public ScheduleListFragment()
    {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        ((MainActivity) getActivity()).getSupportActionBar().setTitle("Liste des EDT"); //TODO:String support + generalisation de nos fragments

        View rootView = inflater.inflate(R.layout.fragment_schedule_list, container, false);

        //setContentView(R.layout.fragment_schedule_list);
        final ScheduleRowModel[] rowList = {new ScheduleRowModel("Emplois du temps L3 2.1", "C", null), new ScheduleRowModel("Emplois du temps L3 4.1", "A", null)};
        final ScheduleRowModel[] rowList2 = {new ScheduleRowModel("Emplois du temps L1 1.1", "1", null), new ScheduleRowModel("Emplois du temps L1 2.1", "12", null)};
        final ScheduleGroupModel[] list = {new ScheduleGroupModel("L1", rowList2), new ScheduleGroupModel("L3", rowList)};
        ScheduleGroupAdapter adapter = new ScheduleGroupAdapter(ScheduleApplication.getContext(), list);

        final AnimatedExpandableListView listView = ((AnimatedExpandableListView) rootView.findViewById(R.id.schedule_list_view));

        listView.setAdapter(adapter);
        int count =  adapter.getGroupCount(); //Moche, mais le seul moyen acctuel.
        for (int i = 0; i <count ; i++)
            listView.expandGroup(i);

        listView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener()
        {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id)
            {
                if (listView.isGroupExpanded(groupPosition))
                {
                    listView.collapseGroupWithAnimation(groupPosition);
                } else
                {
                    listView.expandGroupWithAnimation(groupPosition);
                }
                return true;
            }
        });

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
