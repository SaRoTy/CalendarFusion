package com.saroty.ter.schedule;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Arthur on 24/03/2015.
 */
public class ScheduleGroup extends Schedule implements Serializable
{
    private static final long serialVersionUID = 2624645387219206093L;

    private String mGroupName;
    private List<Schedule> mScheduleList;

    public ScheduleGroup(String groupName, List<Schedule> scheduleList)
    {
        this.mGroupName = groupName;
        this.mScheduleList = scheduleList;
    }

    public String getGroupName()
    {
        return mGroupName;
    }

    public List<Schedule> getScheduleList()
    {
        return mScheduleList;
    }
}
