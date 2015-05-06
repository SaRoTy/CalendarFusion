package com.saroty.ter.schedule;

import android.util.Pair;

import com.saroty.ter.time.LocalTimeInterval;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
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

    private Map<DateTime, List<Pair<LocalTimeInterval, Course>>> mSchedule;

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
        for (Map.Entry<DateTime, List<Pair<LocalTimeInterval, Course>>> c : mSchedule.entrySet())
        {
            if (c.getKey().isSameDayAs(date))
            {
                c.getValue().add(new Pair<>(interval, course));
                return;
            }
        }
        mSchedule.put(date, new ArrayList<Pair<LocalTimeInterval, Course>>());
    }

    public List<Pair<LocalTimeInterval, Course>> getDailyCourses(DateTime day)
    {
        for (Map.Entry<DateTime, List<Pair<LocalTimeInterval, Course>>> c : mSchedule.entrySet())
        {
            if (c.getKey().isSameDayAs(day))
                return c.getValue();
        }
        return new ArrayList<>();
    }

    @Override
    public String toString()
    {
        String result = "[Schedule]\n";

        return result;
    }
}