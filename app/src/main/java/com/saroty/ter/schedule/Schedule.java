package com.saroty.ter.schedule;

import com.saroty.ter.time.LocalTimeInterval;

import java.io.Serializable;
import java.util.Map;
import java.util.TreeMap;

import hirondelle.date4j.DateTime;

/**
 * Created by Arthur on 09/03/2015.
 */
public class Schedule implements Serializable
{
    private static final long serialVersionUID = -7151757052818244043L;
    private String name = "defaultSchedule";

    private Map<DateTime, TreeMap<LocalTimeInterval, Course>> mSchedule;

    public Schedule()
    {
        mSchedule = new TreeMap<>();
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void addCourse(Course course, DateTime date, LocalTimeInterval interval)
    {
        for (Map.Entry<DateTime, TreeMap<LocalTimeInterval, Course>> c : mSchedule.entrySet())
        {
            if (c.getKey().isSameDayAs(date))
            {
                c.getValue().put(interval, course);
                return;
            }
        }
        TreeMap<LocalTimeInterval, Course> newMap = new TreeMap<>();
        newMap.put(interval, course);
        mSchedule.put(date, newMap);
    }

    public Map<LocalTimeInterval, Course> getDailyCourses(DateTime day)
    {
        for (Map.Entry<DateTime, TreeMap<LocalTimeInterval, Course>> c : mSchedule.entrySet())
        {
            if (c.getKey().isSameDayAs(day))
                return c.getValue();
        }
        return new TreeMap<>();
    }

    @Override
    public String toString()
    {
        String result = "[Schedule]\n";

        return result;
    }
}