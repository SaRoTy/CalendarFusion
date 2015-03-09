package com.saroty.ter.schedule;

import com.saroty.ter.schedule.time.DayOfWeek;

import java.util.TreeMap;

/**
 * Created by Arthur on 09/03/2015.
 */
public class ScheduleTable
{
    private TreeMap<DayOfWeek, ScheduleDay> days;

    public ScheduleTable()
    {
        days = new TreeMap<DayOfWeek, ScheduleDay>();
    }

    public void addDay(DayOfWeek weekDay, ScheduleDay day)
    {
        days.put(weekDay, day);
    }
}
