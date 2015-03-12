package com.saroty.ter.schedule;

import java.io.Serializable;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by Arthur on 09/03/2015.
 */
public class Schedule implements Serializable
{
    private static final long serialVersionUID = -7151757052818244043L;
    private String name = "defaultSchedule";
    private TreeMap<Integer, CourseWeek> weeks;

    public Schedule()
    {
        weeks = new TreeMap<Integer, CourseWeek>();
    }

    public void addWeek(int weekNumber, CourseWeek week)
    {
        weeks.put(weekNumber, week);
    }

    public int weekCount()
    {
        return weeks.size();
    }

    public String getName()
    {
        return name;
    }

    public CourseWeek getWeekByWeekNumber(int weekNumber)
    {
        return weeks.get(weekNumber);
    }

    @Override
    public String toString()
    {
        String result = "[Schedule]\n";
        for (Map.Entry<Integer, CourseWeek> entry : weeks.entrySet())
        {
            result += "(" + entry.getKey() + ")\n" + entry.getValue();
        }
        return result;
    }
}