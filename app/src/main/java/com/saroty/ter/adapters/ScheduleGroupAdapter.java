package com.saroty.ter.adapters;

import android.app.DialogFragment;
import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.idunnololz.widgets.AnimatedExpandableListView;
import com.saroty.ter.R;
import com.saroty.ter.ScheduleApplication;
import com.saroty.ter.fragments.dialog.OptionsScheduleDialogFragment;
import com.saroty.ter.models.list.ScheduleGroupModel;
import com.saroty.ter.models.list.ScheduleRowModel;
import com.saroty.ter.schedule.Schedule;

import java.util.ArrayList;

/**
 * Created by Arthur on 12/03/2015.
 */
public class ScheduleGroupAdapter extends AnimatedExpandableListView.AnimatedExpandableListAdapter
{
    private final ScheduleGroupModel[] DATA;
    private final ArrayList<Schedule> mSchedules;
    private LayoutInflater mInflater;
    private Context mContext;

    public ScheduleGroupAdapter(Context context, ArrayList<Schedule> schedules)
    {
        super();

        final ScheduleRowModel[] rowList = new ScheduleRowModel[schedules.size()];
        for (int i = 0; i < schedules.size(); i++)
            rowList[i] = new ScheduleRowModel(schedules.get(i).getName(), schedules.get(i).getEventCount(), schedules.get(i).getLastUpdate());

        final ScheduleGroupModel groupList[] = {new ScheduleGroupModel("Général", rowList)};

        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        DATA = groupList;
        mSchedules = schedules;
        mContext = context;
    }

    @Override
    public int getGroupCount()
    {
        return DATA.length;
    }

    @Override
    public Object getGroup(int groupPosition)
    {
        return DATA[groupPosition];
    }

    @Override
    public Object getChild(int groupPosition, int childPosition)
    {
        return DATA[groupPosition].getRow(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition)
    {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition)
    {
        return childPosition;
    }

    @Override
    public boolean hasStableIds()
    {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent)
    {
        GroupViewHolder holder;

        if (convertView == null)
        {
            convertView = mInflater.inflate(R.layout.schedules_list_group, null);

            holder = new GroupViewHolder();
            holder.title = (TextView) convertView.findViewById(R.id.schedule_list_group_title);

            convertView.setTag(holder);
        } else
        {
            holder = (GroupViewHolder) convertView.getTag();
        }

        holder.title.setText(DATA[groupPosition].getName());

        return convertView;
    }

    @Override
    public View getRealChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent)
    {
        ChildViewHolder holder;

        if (convertView == null)
        {
            convertView = mInflater.inflate(R.layout.schedules_list_row, null);

            holder = new ChildViewHolder();
            holder.title = (TextView) convertView.findViewById(R.id.schedule_title);
            holder.eventCount = (TextView) convertView.findViewById(R.id.schedule_event_number);
            holder.iconOptions = convertView.findViewById(R.id.icon_dropdown);

            convertView.setTag(holder);
        } else
        {
            holder = (ChildViewHolder) convertView.getTag();
        }

        holder.title.setText(DATA[groupPosition].getRow(childPosition).getTitle());
        holder.eventCount.setText(ScheduleApplication.getContext().getText(R.string.label_schedule_course_number) + " " + DATA[groupPosition].getRow(childPosition).getEventCount());

        holder.iconOptions.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                DialogFragment fragment = new OptionsScheduleDialogFragment();
                fragment.show(((FragmentActivity) mContext).getFragmentManager(), "options");
            }
        });

        return convertView;
    }

    @Override
    public int getRealChildrenCount(int groupPosition)
    {
        return DATA[groupPosition].getRowCount();
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition)
    {
        return true;
    }

    private static class GroupViewHolder
    {
        TextView title;
    }

    private static class ChildViewHolder
    {
        TextView title;
        TextView eventCount;
        View iconOptions;
    }
}
