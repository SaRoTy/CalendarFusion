package com.saroty.ter.models.list;

import android.graphics.Color;

/**
 * Created by Arthur on 12/03/2015.
 */
public class ScheduleRowModel
{
    private String mTitle;
    private String mType;
    private Color mTypeColor;

    public ScheduleRowModel(String title, String type, Color typeColor)
    {
        mTitle = title;
        mTypeColor = typeColor;
        mType = type;
    }

    public Color getTypeColor()
    {
        return mTypeColor;
    }

    public String getTitle()
    {
        return mTitle;
    }

    public String getType()
    {
        return mType;
    }
}
