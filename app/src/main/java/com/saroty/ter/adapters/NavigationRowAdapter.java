package com.saroty.ter.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.saroty.ter.R;
import com.saroty.ter.models.list.NavigationRowModel;

/**
 * Created by Arthur on 21/03/2015.
 */
public class NavigationRowAdapter extends ArrayAdapter<NavigationRowModel>
{

    private final NavigationRowModel[] DATA;
    private LayoutInflater mInflater;

    public NavigationRowAdapter(Context context, NavigationRowModel[] modelsArrayList)
    {
        super(context, R.layout.drawer_list_row, modelsArrayList);
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        DATA = modelsArrayList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        ViewHolder holder;

        if (convertView == null)
        {
            if(position == 1)//TODO:Remove this (test)
                convertView = mInflater.inflate(R.layout.drawer_list_row_selected, null);
            else
                convertView = mInflater.inflate(R.layout.drawer_list_row, null);

            holder = new ViewHolder();
            holder.title = (TextView) convertView.findViewById(R.id.drawer_list_row_title);

            convertView.setTag(holder);
        } else
        {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.title.setText(DATA[position].getTitle());

        return convertView;
    }

    private static class ViewHolder
    {
        TextView title;
    }
}
