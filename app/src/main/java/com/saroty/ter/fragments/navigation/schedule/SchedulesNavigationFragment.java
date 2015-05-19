package com.saroty.ter.fragments.navigation.schedule;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import com.idunnololz.widgets.AnimatedExpandableListView;
import com.saroty.ter.R;
import com.saroty.ter.ScheduleApplication;
import com.saroty.ter.adapters.ScheduleGroupAdapter;
import com.saroty.ter.fragments.dialog.AddScheduleDialogFragment;
import com.saroty.ter.fragments.navigation.NavigationFragment;
import com.saroty.ter.schedule.Schedule;
import com.saroty.ter.schedule.ScheduleGroup;
import com.saroty.ter.schedule.ScheduleManager;

public class SchedulesNavigationFragment extends NavigationFragment implements AddScheduleDialogFragment.AddScheduleDialogListener
{
    private AnimatedExpandableListView mListView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {

        View rootView = inflater.inflate(R.layout.fragment_schedule_list, container, false);

        mListView = ((AnimatedExpandableListView) rootView.findViewById(R.id.schedule_list_view));

        refreshList();

        return rootView;
    }

    private void refreshList()
    {
        //setContentView(R.layout.fragment_schedule_list);

        ScheduleGroup[] groups = ScheduleManager.getInstance().getGroups();

        ScheduleGroupAdapter adapter = new ScheduleGroupAdapter(getActivity(), groups);

        mListView.setAdapter(adapter);
        int count = adapter.getGroupCount();

        for (int i = 0; i < count; i++)
            if (groups[i].isEnabled())
                mListView.expandGroup(i);

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

        setHasOptionsMenu(true);
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
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater)
    {
        if (menu.size() == 0)
            inflater.inflate(R.menu.menu_schedule_list, menu);
    }

    @Override
    public void onScheduleDownloaded(Schedule schedule)
    {
        ScheduleManager.getInstance().addSchedule(schedule, true);
        refreshList();
    }

    @Override
    public void onScheduleDownloadError(Exception e)
    {

    }

    @Override
    public String getNavigationTitle()
    {
        return ScheduleApplication.getContext().getString(R.string.title_navigation_schedule);
    }

    @Override
    public String getActionbarTitle()
    {
        return ScheduleApplication.getContext().getString(R.string.title_schedule);
    }
}
