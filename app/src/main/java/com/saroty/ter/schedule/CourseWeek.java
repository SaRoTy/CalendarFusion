package com.saroty.ter.schedule;

import com.saroty.ter.schedule.time.DayOfWeek;

import java.util.TreeMap;

/**
 * Created by Arthur on 10/03/2015.
 */
public class CourseWeek
{
    private TreeMap<DayOfWeek, CourseDay> days;

    public CourseWeek()
    {
        days = new TreeMap<DayOfWeek, CourseDay>();
    }

    public void addDay(DayOfWeek weekDay, CourseDay day)
    {
        days.put(weekDay, day);
    }

    public CourseDay getDay(DayOfWeek day)
    {
        return days.get(day);
    }
}
