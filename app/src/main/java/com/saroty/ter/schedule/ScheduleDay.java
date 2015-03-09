package com.saroty.ter.schedule;

import com.saroty.ter.schedule.time.LocalTime;

import java.util.TreeMap;

/**
 * Created by Arthur on 09/03/2015.
 */
public class ScheduleDay
{
    //On prend une TreeMap vu qu'on a implémenté Comparable sur LocalTime.
    private TreeMap<LocalTime, ScheduleCourse> courseList;

    public ScheduleDay()
    {
        courseList = new TreeMap<LocalTime, ScheduleCourse>();
    }

    public void addCourse(LocalTime time, ScheduleCourse course)
    {
        courseList.put(time, course);
    }

    public LocalTime getFirstLocalTime()
    {
        return courseList.firstKey();
    }

    public ScheduleCourse getFirstCourse()
    {
        return courseList.firstEntry().getValue();
    }
}
