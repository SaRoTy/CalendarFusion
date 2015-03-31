package com.saroty.ter.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.idunnololz.widgets.AnimatedExpandableListView;
import com.saroty.ter.R;
import com.saroty.ter.models.list.ScheduleGroupModel;

/**
 * Created by Arthur on 12/03/2015.
 */
public class ScheduleGroupAdapter extends AnimatedExpandableListView.AnimatedExpandableListAdapter
{
    private final ScheduleGroupModel[] DATA;
    private LayoutInflater mInflater;

    public ScheduleGroupAdapter(Context context, ScheduleGroupModel[] scheduleGroups)
    {
        super();
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        DATA = scheduleGroups;
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
            holder.title = (TextView) convertView.findViewById(R.id.schedule_list_row_title);
            holder.type = (TextView) convertView.findViewById(R.id.schedule_list_row_type);

            convertView.setTag(holder);
        } else
        {
            holder = (ChildViewHolder) convertView.getTag();
        }

        holder.title.setText(DATA[groupPosition].getRow(childPosition).getTitle());
        holder.type.setText(DATA[groupPosition].getRow(childPosition).getType());

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
        TextView type;
    }
}
