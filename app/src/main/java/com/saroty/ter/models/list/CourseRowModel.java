package com.saroty.ter.models.list;

import com.saroty.ter.time.LocalTime;
import com.saroty.ter.time.LocalTimeInterval;

/**
 * Created by Romain on 24/03/2015.
 */
public class CourseRowModel {

    private String mName;
    private LocalTimeInterval mInterval;
    private String mRoom;

    public CourseRowModel(String name, LocalTimeInterval interval, String room)
    {
        mName = name;
        mInterval = interval;
        mRoom = room;
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

    public void setInterval(LocalTimeInterval mInterval)
    {
        this.mInterval = mInterval;
    }

    public String getRoom()
    {
        return mRoom;
    }

    public void setRoom(String mRoom)
    {
        this.mRoom = mRoom;
    }
}
