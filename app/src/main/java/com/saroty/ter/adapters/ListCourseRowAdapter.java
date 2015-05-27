package com.saroty.ter.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.saroty.ter.R;
import com.saroty.ter.models.list.CourseRowModel;
import com.saroty.ter.models.list.ManagerCourseRowModel;

/**
 * Created by Romain on 27/05/2015.
 */
public class ListCourseRowAdapter extends ArrayAdapter<ManagerCourseRowModel> {

    private final ManagerCourseRowModel[] DATA;
    private LayoutInflater mInflater;

    public ListCourseRowAdapter(Context context, ManagerCourseRowModel[] modelsArrayList) {
        super(context, R.layout.manager_course_row,modelsArrayList);

        DATA = modelsArrayList;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        ViewHolder holder;
        if (convertView == null)
        {
            convertView = mInflater.inflate(R.layout.manager_course_row, null);

            holder = new ViewHolder();
            holder.name = (TextView) convertView.findViewById(R.id.text_name);
            holder.color = convertView.findViewById(R.id.course_color);

            convertView.setTag(holder);
        } else
        {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.name.setText(DATA[position].getTitle());
        holder.color.setBackgroundColor(DATA[position].getColor());

        return convertView;
    }

    private static class ViewHolder
    {
        View color;
        TextView name;
    }
}
