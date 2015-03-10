package com.saroty.ter.time;

public enum DayOfWeek
{
    MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY;

    public static DayOfWeek getById(int id)
    {
        if (id < DayOfWeek.values().length)
            return DayOfWeek.values()[id];
        return MONDAY;
    }
}

