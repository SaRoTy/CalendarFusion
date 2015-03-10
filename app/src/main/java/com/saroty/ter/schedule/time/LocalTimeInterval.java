package com.saroty.ter.schedule.time;

import java.io.Serializable;

/**
 * Created by Arthur on 10/03/2015.
 */
public class LocalTimeInterval implements Comparable<LocalTimeInterval>, Serializable
{
    private static final long serialVersionUID = -7554035215853832772L;
    LocalTime start;
    LocalTime end;

    public LocalTimeInterval(LocalTime start, LocalTime end)
    {
        this.start = start;
        this.end = end;
    }

    public LocalTime getStart()
    {
        return start;
    }

    public LocalTime getEnd()
    {
        return end;
    }

    @Override
    public int compareTo(LocalTimeInterval another)
    {
        return start.compareTo(another.getStart());
    }

    @Override
    public String toString()
    {
        return start.toString() + " - " + end.toString();
    }
}
