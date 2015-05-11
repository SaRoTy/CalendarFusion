package com.saroty.ter.adapters;

import android.app.DialogFragment;
import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.idunnololz.widgets.AnimatedExpandableListView;
import com.saroty.ter.R;
import com.saroty.ter.ScheduleApplication;
import com.saroty.ter.fragments.dialog.OptionsScheduleDialogFragment;
import com.saroty.ter.schedule.Schedule;
import com.saroty.ter.schedule.ScheduleGroup;
import com.saroty.ter.schedule.ScheduleManager;

import java.util.Locale;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by Arthur on 12/03/2015.
 */
public class ScheduleGroupAdapter extends AnimatedExpandableListView.AnimatedExpandableListAdapter implements Observer
{
    private final ScheduleGroup[] DATA;
    private LayoutInflater mInflater;
    private Context mContext;

    public ScheduleGroupAdapter(Context context, ScheduleGroup[] groupList)
    {
        super();
        ScheduleManager.getInstance().addObserver(this);
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        DATA = groupList;
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
        return DATA[groupPosition].getScheduleList().get(childPosition);
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

        holder.title.setText(DATA[groupPosition].getGroupName());

        return convertView;
    }

    @Override
    public View getRealChildView(final int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent)
    {
        ChildViewHolder holder;

        final Schedule schedule = DATA[groupPosition].getScheduleList().get(childPosition);

        if (convertView == null)
        {
            convertView = mInflater.inflate(R.layout.schedules_list_row, null);

            holder = new ChildViewHolder();
            holder.title = (TextView) convertView.findViewById(R.id.schedule_title);
            holder.eventCount = (TextView) convertView.findViewById(R.id.schedule_event_number);
            holder.lastUpdate = (TextView) convertView.findViewById(R.id.schedule_last_update);
            holder.iconOptions = (ImageView) convertView.findViewById(R.id.icon_dropdown);

            convertView.setTag(holder);
        } else
        {
            holder = (ChildViewHolder) convertView.getTag();
        }
        if (!DATA[groupPosition].getScheduleList().get(childPosition).isEnabled())
            holder.title.setTextColor(mContext.getResources().getColor(android.R.color.darker_gray));
        else
            holder.title.setTextColor(mContext.getResources().getColor(android.R.color.black));

        holder.title.setText(DATA[groupPosition].getScheduleList().get(childPosition).getName());
        holder.eventCount.setText(ScheduleApplication.getContext().getText(R.string.label_schedule_course_number) + " " + schedule.getEventCount());
        holder.lastUpdate.setText(ScheduleApplication.getContext().getText(R.string.label_schedule_last_update) + " " + schedule.getLastUpdate().format("DD/MM hh:mm", Locale.getDefault()));

        if (schedule.isUpdating())
        {
            holder.iconOptions.setImageResource(R.drawable.ic_updating);
        } else
        {
            holder.iconOptions.setImageResource(R.drawable.ic_dropdown);
            holder.iconOptions.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    DialogFragment fragment = OptionsScheduleDialogFragment.newInstance(schedule);
                    fragment.show(((FragmentActivity) mContext).getFragmentManager(), "options");
                }
            });
        }
        if (isLastChild)
            convertView.setBackgroundDrawable(mContext.getResources().getDrawable(R.drawable.shadow)); //Obligatoire en API 15, la nouvelle fonction est dans l'API 16

        return convertView;
    }

    @Override
    public int getRealChildrenCount(int groupPosition)
    {
        return DATA[groupPosition].getScheduleList().size();
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition)
    {
        return true;
    }

    @Override
    public void update(Observable observable, Object data)
    {
        notifyDataSetChanged();
    }

    private static class GroupViewHolder
    {
        TextView title;
    }

    private static class ChildViewHolder
    {
        TextView title;
        TextView eventCount;
        TextView lastUpdate;
        ImageView iconOptions;
    }
}
