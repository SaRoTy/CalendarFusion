package com.saroty.ter.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.saroty.ter.R;
import com.saroty.ter.models.list.CourseRowModel;
import com.saroty.ter.models.list.ScheduleRowModel;

/**
 * Created by Romain on 24/03/2015.
 */
public class ListCourseOfDayRowAdapter extends ArrayAdapter<CourseRowModel> {

    private final CourseRowModel[] DATA;
    private LayoutInflater mInflater;

    public ListCourseOfDayRowAdapter(Context context, CourseRowModel[] modelsArrayList)
    {
        super(context, R.layout.course_of_day_list_row, modelsArrayList);
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        DATA = modelsArrayList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        ViewHolder holder;

        if (convertView == null)
        {
            convertView = mInflater.inflate(R.layout.course_of_day_list_row, null);

            holder = new ViewHolder();
            holder.nom = (TextView) convertView.findViewById(R.id.course_list_row_title);
            holder.type = (TextView) convertView.findViewById(R.id.course_list_row_type);

            convertView.setTag(holder);
        } else
        {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.type.setText(DATA[position].getInter());
        holder.nom.setText(DATA[position].getNom());

        return convertView;
    }

    private static class ViewHolder //Lié à la performence, trick refilé par google.
    {
        TextView nom;
        TextView type;
    }
}
