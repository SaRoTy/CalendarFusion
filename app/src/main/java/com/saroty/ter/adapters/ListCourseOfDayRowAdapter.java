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
 * Created by Romain on 24/03/2015.
 */
public class ListCourseOfDayRowAdapter extends ArrayAdapter<CourseRowModel>
{

    private final CourseRowModel[] DATA;
    private LayoutInflater mInflater;

    public ListCourseOfDayRowAdapter(Context context, CourseRowModel[] modelsArrayList)
    {
        super(context, R.layout.home_course_row, modelsArrayList);
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        DATA = modelsArrayList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        ViewHolder holder;
        if (convertView == null)
        {
            convertView = mInflater.inflate(R.layout.home_course_row, null);

            holder = new ViewHolder();
            holder.name = (TextView) convertView.findViewById(R.id.text_name);
            holder.interval = (TextView) convertView.findViewById(R.id.text_interval);
            holder.room = (TextView) convertView.findViewById(R.id.text_room);
            holder.color = convertView.findViewById(R.id.course_color);

            convertView.setTag(holder);
        } else
        {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.interval.setText(DATA[position].getInterval().toString());
        holder.name.setText(DATA[position].getCourse().getTitle());
        holder.room.setText(DATA[position].getCourse().getRoom());
        holder.color.setBackgroundColor(DATA[position].getCourse().getColor());

        return convertView;
    }

    public CourseRowModel[] getModel()
    {
        return DATA;
    }

    private static class ViewHolder //Lié à la performence, trick refilé par google.
    {
        View color;
        TextView name;
        TextView interval;
        TextView room;
    }
}
