package com.saroty.ter.schedule;

import android.util.Log;

import com.saroty.ter.time.DayOfWeek;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
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

    public void setName(String name)
    {
        this.name = name;
    }

    public CourseWeek getWeekByWeekNumber(int weekNumber){ return weeks.get(weekNumber); }

    public CourseDay getDayByDate(Date date)
    {
        CourseWeek week;
        DayOfWeek day;
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);

        if( !weeks.containsKey(cal.get(Calendar.WEEK_OF_YEAR)) )
            Log.v("debug romain","nok contain");

        if( (week = getWeekByWeekNumber(cal.get(Calendar.WEEK_OF_YEAR))) == null )
            Log.v("debug romain","probleme week");

        if( (day = DayOfWeek.getById(cal.get(Calendar.DAY_OF_WEEK))) == null )
            Log.v("deug romain","probleme day");

        return week.getDay(day);

    }

    @Override
    public String toString()
    {
        String result = "[Schedule]\n";
        for (Map.Entry<Integer, CourseWeek> entry : weeks.entrySet())
        {
            result += "(" + entry.getKey() + ")\n" + entry.getValue();
            //result += "(" + entry.getKey()+ ")\n";
        }
        return result;
    }
}