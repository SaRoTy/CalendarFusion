package com.saroty.ter.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.saroty.ter.R;
import com.saroty.ter.models.list.CourseRowModel;

/**
 * Created by Arthur on 02/04/2015.
 */
public class CourseRowAdapter extends ArrayAdapter<CourseRowModel>
{
    private final CourseRowModel[] DATA;
    private LayoutInflater mInflater;

    public CourseRowAdapter(Context context, CourseRowModel[] objects)
    {
        super(context, R.layout.home_course_row, objects);
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        DATA = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        ViewHolder holder;
        if (convertView == null)
        {
            convertView = mInflater.inflate(R.layout.home_course_row, null);

            holder = new ViewHolder();

            holder.color = convertView.findViewById(R.id.course_color);
            holder.interval = (TextView) convertView.findViewById(R.id.text_interval);
            holder.name = (TextView) convertView.findViewById(R.id.text_name);
            holder.room = (TextView) convertView.findViewById(R.id.text_room);

            convertView.setTag(holder);
        } else
        {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.interval.setText(DATA[position].getInterval().toString());
        holder.name.setText(DATA[position].getName());
        holder.room.setText(DATA[position].getRoom());
        holder.color.setBackgroundColor(DATA[position].getColor());

        return convertView;
    }

    private static class ViewHolder
    {
        View color;
        TextView interval;
        TextView name;
        TextView room;
    }
}
