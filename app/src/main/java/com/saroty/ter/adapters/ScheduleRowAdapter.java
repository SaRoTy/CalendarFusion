package com.saroty.ter.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.saroty.ter.R;
import com.saroty.ter.models.list.ScheduleRowModel;

import java.util.ArrayList;

/**
 * Created by Arthur on 12/03/2015.
 */
public class ScheduleRowAdapter extends ArrayAdapter<ScheduleRowModel>
{
    private LayoutInflater mInflater;
    private final ScheduleRowModel[] DATA;

    public ScheduleRowAdapter(Context context, ScheduleRowModel[] modelsArrayList)
    {
        super(context, R.layout.schedules_list_row, modelsArrayList);
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        DATA = modelsArrayList;
    }

    //TODO : Implement color if whe keep it
    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        ViewHolder holder;

        if (convertView == null)
        {
            convertView = mInflater.inflate(R.layout.schedules_list_row, null);

            holder = new ViewHolder();
            holder.title = (TextView) convertView.findViewById(R.id.schedule_list_row_title);
            holder.type = (TextView) convertView.findViewById(R.id.schedule_list_row_type);

            convertView.setTag(holder);
        } else
        {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.title.setText(DATA[position].getTitle());
        holder.type.setText(DATA[position].getType());

        return convertView;
    }

    private static class ViewHolder //Lié à la performence, trick refilé par google.
    {
        TextView title;
        TextView type;
    }
}
