package com.saroty.ter.schedule;

import android.util.SparseArray;

/**
 * Created by Arthur on 09/03/2015.
 */
public class Schedule
{
    private SparseArray<CourseWeek> weeks;

    public Schedule()
    {
        weeks = new SparseArray<CourseWeek>();
    }

    public void addWeek(int weekNumber, CourseWeek week)
    {
        weeks.append(weekNumber, week);
    }

    public int weekCount()
    {
        return weeks.size();
    }

    public CourseWeek getWeekByWeekNumber(int weekNumber)
    {
        return weeks.get(weekNumber);
    }

    @Override
    public String toString()
    {
        String result = "[Schedule]\n";
        int key;
        for(int i = 0; i < weeks.size(); i++)
        {
            key = weeks.keyAt(i);
            result += "(" + key + ")\n" + weeks.get(key);
        }
        return result;
    }
}
