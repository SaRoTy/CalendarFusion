package com.saroty.ter.models.list;

import com.saroty.ter.time.LocalTimeInterval;

/**
 * Created by Romain on 24/03/2015.
 */
public class CourseRowModel {

    private String mName;
    private LocalTimeInterval mInterval;
    private String mRoom;
    private int mColor;

    public CourseRowModel(String name, LocalTimeInterval interval, String room, int color)
    {
        mName = name;
        mInterval = interval;
        mRoom = room;
        mColor = color;
    }

    public String getName()
    {
        return mName;
    }

    public void setName(String mName)
    {
        this.mName = mName;
    }

    public LocalTimeInterval getInterval()
    {
        return mInterval;
    }

    public String getRoom()
    {
        return mRoom;
    }

    public int getColor()
    {
        return mColor;
    }
}
