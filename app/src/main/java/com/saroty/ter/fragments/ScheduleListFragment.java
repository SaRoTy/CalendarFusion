package com.saroty.ter.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import com.idunnololz.widgets.AnimatedExpandableListView;
import com.saroty.ter.R;
import com.saroty.ter.ScheduleApplication;
import com.saroty.ter.activities.MainActivity;
import com.saroty.ter.adapters.ScheduleGroupAdapter;
import com.saroty.ter.fragments.dialog.AddScheduleDialogFragment;
import com.saroty.ter.models.list.ScheduleGroupModel;
import com.saroty.ter.models.list.ScheduleRowModel;
import com.saroty.ter.schedule.Schedule;


public class ScheduleListFragment extends Fragment implements AddScheduleDialogFragment.AddScheduleDialogListener
{
    private AnimatedExpandableListView mListView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        ((MainActivity) getActivity()).getSupportActionBar().setTitle("Liste des EDT"); //TODO:String support + generalisation de nos fragments

        View rootView = inflater.inflate(R.layout.fragment_schedule_list, container, false);

        mListView = ((AnimatedExpandableListView) rootView.findViewById(R.id.schedule_list_view));

        refreshList();

        return rootView;
    }

    private void refreshList()
    {
        //setContentView(R.layout.fragment_schedule_list);
        final ScheduleRowModel[] rowList = new ScheduleRowModel[((MainActivity) getActivity()).getSchedules().size()];
        for (int i = 0; i < ((MainActivity) getActivity()).getSchedules().size(); i++)
            rowList[i] = new ScheduleRowModel(((MainActivity) getActivity()).getSchedules().get(i).getName(), "0", null);

        final ScheduleGroupModel groupList[] = {new ScheduleGroupModel("Général", rowList)};

        ScheduleGroupAdapter adapter = new ScheduleGroupAdapter(ScheduleApplication.getContext(), groupList);

        mListView.setAdapter(adapter);
        int count = adapter.getGroupCount();
        for (int i = 0; i <count ; i++)
            mListView.expandGroup(i);//Moche, mais le seul moyen acctuel.

        mListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener()
        {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id)
            {
                if (mListView.isGroupExpanded(groupPosition))
                {
                    mListView.collapseGroupWithAnimation(groupPosition);
                } else
                {
                    mListView.expandGroupWithAnimation(groupPosition);
                }
                return true;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int id = item.getItemId();

        if (id == R.id.action_add_schedule)
        {

            AddScheduleDialogFragment f = AddScheduleDialogFragment.newInstance(this);
            f.show(getFragmentManager(), "AddScheduleDialogFragment");
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onScheduleDownloaded(Schedule schedule)
    {
        ((MainActivity) getActivity()).addSchedule(schedule);
        refreshList();
        Log.v("Downloaded", "ok");
    }

    @Override
    public void onScheduleDownloadError(Exception e)
    {
        Log.v("Downloaded", e.toString());
    }
}
