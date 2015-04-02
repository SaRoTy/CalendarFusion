package com.saroty.ter.time;

import java.util.Calendar;

public enum DayOfWeek
{
    MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY;

    public static DayOfWeek getById(int id)
    {
        if (id < DayOfWeek.values().length)
            return DayOfWeek.values()[id];
        return MONDAY;
    }

    public static DayOfWeek getByCalendar(Calendar c)
    {
        return getById((c.get(Calendar.DAY_OF_WEEK) - Calendar.MONDAY) % 7);
    }
}

