package com.saroty.ter.schedule;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Arthur on 24/03/2015.
 */
public class ScheduleGroup implements Serializable
{
    private static final long serialVersionUID = 2624645387219206093L;

    private String mGroupName;
    private List<Schedule> mScheduleList;
    private boolean mEnabled = true;

    public ScheduleGroup(String name)
    {
        this.mGroupName = name;
        this.mScheduleList = new ArrayList<>();
    }

    public String getGroupName()
    {
        return mGroupName;
    }

    public List<Schedule> getScheduleList()
    {
        return mScheduleList;
    }

    public void addSchedule(Schedule schedule)
    {
        schedule.setGroupName(mGroupName);
        mScheduleList.add(schedule);
    }

    public boolean isEnabled()
    {
        return mEnabled;
    }

    public void setEnabled(boolean mEnabled)
    {
        this.mEnabled = mEnabled;
    }
}
