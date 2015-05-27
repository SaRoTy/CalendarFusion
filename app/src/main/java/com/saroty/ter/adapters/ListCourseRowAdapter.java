package com.saroty.ter.adapters;

import android.content.Context;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.saroty.ter.R;
import com.saroty.ter.models.list.CourseRowModel;
import com.saroty.ter.models.list.ManagerCourseRowModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Romain on 27/05/2015.
 * Classe réponsable de la liste avec recherche pour crée les filtres
 */
public class ListCourseRowAdapter extends ArrayAdapter<ManagerCourseRowModel> implements Filterable {

    private final List<ManagerCourseRowModel> DATA;
    private List<ManagerCourseRowModel> FILTERED_DATA;
    private LayoutInflater mInflater;
    private ModelFilter mFilter;

    public ListCourseRowAdapter(Context context, List<ManagerCourseRowModel> modelList) {
        super(context, R.layout.manager_course_row,modelList);

        DATA = new ArrayList<>(modelList);
        FILTERED_DATA = new ArrayList<>(modelList);
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

        ManagerCourseRowModel model = FILTERED_DATA.get(position);

        holder.name.setText(model.getTitle());
        holder.color.setBackgroundColor(model.getColor());

        return convertView;
    }


    @Override
    public Filter getFilter() {
        if (mFilter == null){
            mFilter  = new ModelFilter();
        }
        return mFilter;
    }

    private static class ViewHolder
    {
        View color;
        TextView name;
    }

    private class ModelFilter extends Filter
    {

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {

            constraint = constraint.toString().toLowerCase();
            FilterResults result = new FilterResults();
            if(constraint != null && constraint.toString().length() > 0)
            {
                List<ManagerCourseRowModel> filteredItems = new ArrayList<>();

                for(int i = 0, l = DATA.size(); i < l; i++)
                {
                    ManagerCourseRowModel m = DATA.get(i);
                    if(m.getTitle().toLowerCase().contains(constraint))
                        filteredItems.add(m);
                }
                result.count = filteredItems.size();
                result.values = filteredItems;
            }
            else
            {
                synchronized(this)
                {
                    result.values = DATA;
                    result.count = DATA.size();
                }
            }
            return result;
        }

        @SuppressWarnings("unchecked")
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {

            FILTERED_DATA = (ArrayList<ManagerCourseRowModel>)results.values;
            notifyDataSetChanged();
            clear();
            for(int i = 0, l = FILTERED_DATA.size(); i < l; i++)
                add(FILTERED_DATA.get(i));
            notifyDataSetInvalidated();
        }
    }
}

