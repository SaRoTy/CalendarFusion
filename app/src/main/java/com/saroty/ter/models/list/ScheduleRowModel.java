package com.saroty.ter.models.list;

import hirondelle.date4j.DateTime;

/**
 * Created by Arthur on 12/03/2015.
 */
public class ScheduleRowModel
{
    private String mTitle;
    private int mEventCount;
    private DateTime mLastModification;

    public ScheduleRowModel(String title, int eventCount, DateTime lastModification)
    {
        mTitle = title;
        mEventCount = eventCount;
        mLastModification = lastModification;
    }

    public String getTitle()
    {
        return mTitle;
    }

    public int getEventCount()
    {
        return mEventCount;
    }

    public DateTime getLastModification()
    {
        return mLastModification;
    }
}
