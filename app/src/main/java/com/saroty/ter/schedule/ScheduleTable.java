package com.saroty.ter.schedule;

import android.util.SparseArray;

import com.saroty.ter.schedule.time.DayOfWeek;

import java.util.TreeMap;

/**
 * Created by Arthur on 09/03/2015.
 */
public class ScheduleTable
{
    private SparseArray<ScheduleWeek> weeks;

    public ScheduleTable()
    {
        weeks = new SparseArray<ScheduleWeek>();
    }

    public void addWeek(int weekNumber, ScheduleWeek week)
    {
        weeks.append(weekNumber, week);
    }

    public int weekCount()
    {
        return weeks.size();
    }
}
