package com.saroty.ter.schedule;

import com.saroty.ter.schedule.time.DayOfWeek;
import com.saroty.ter.schedule.time.LocalTimeInterval;

import java.util.Map;
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

    @Override
    public String toString()
    {
        String result = "[Course]\n";
        for(Map.Entry<DayOfWeek, CourseDay> entry : days.entrySet())
        {
            result += "(" + entry.getKey().toString() +")\n" + entry.getValue().toString() + "\n";
        }
        return result;
    }
}
