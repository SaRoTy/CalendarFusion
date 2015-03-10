package com.saroty.ter.schedule.time;

import java.io.Serializable;

/**
 * Created by Arthur on 09/03/2015.
 */
public class LocalTime implements Comparable<LocalTime>, Serializable
{
    private static final long serialVersionUID = -1894035811638334418L;
    private int hour;
    private int minute;
    private int second;

    public LocalTime(int hour, int minute, int second)
    {
        this.hour = hour;
        this.minute = minute;
        this.second = second;
    }

    public LocalTime(int hour, int minute)
    {
        this(hour, minute, 0);
    }

    public LocalTime(String rawLocalTime, String token)
    {
        String[] array = rawLocalTime.split(token);

        if (array.length < 2 && array[0].matches("\\d+") && array[1].matches("\\d+")) //Check integer
            return;

        this.hour = Integer.parseInt(array[0]);
        this.minute = Integer.parseInt(array[1]);

        if (array.length > 2 && array[2].matches("\\d+"))
            this.second = Integer.parseInt(array[2]);
    }

    public int getHour()
    {
        return hour;
    }

    public int getMinute()
    {
        return minute;
    }

    public int getSecond()
    {
        return second;
    }

    public int convertToSecond()
    {
        return (hour * 3600 + minute * 60 + second);
    }

    @Override
    public int compareTo(LocalTime other)
    {
        return (this.convertToSecond() - other.convertToSecond());
    }

    @Override
    public String toString()
    {
        return hour + ":" + minute + ":" + second;
    }
}
