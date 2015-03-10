package com.saroty.ter.schedule;

import com.saroty.ter.schedule.time.LocalTime;
import com.saroty.ter.schedule.time.LocalTimeInterval;

import java.util.TreeMap;

/**
 * Created by Arthur on 09/03/2015.
 */
public class CourseDay
{
    //On prend une TreeMap vu qu'on a implémenté Comparable sur LocalTime.
    private TreeMap<LocalTimeInterval, Course> courseList;

    public CourseDay()
    {
        courseList = new TreeMap<LocalTimeInterval, Course>();
    }

    public void addCourse(LocalTimeInterval interval, Course course)
    {
        courseList.put(interval, course);
    }
}
