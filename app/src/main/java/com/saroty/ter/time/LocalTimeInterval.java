package com.saroty.ter.time;

import android.support.annotation.NonNull;

import java.io.Serializable;
import java.util.Objects;

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
    public boolean equals(Object o){
        if(!(o instanceof LocalTimeInterval)){
            return false;
        }
        LocalTimeInterval inter = (LocalTimeInterval)o;
        return (start == inter.getStart() && end == inter.getEnd());
    }

    @Override
    public int compareTo(@NonNull LocalTimeInterval another)
    {
        if (start.compareTo(another.getStart()) == 0)
        {
            if (end.compareTo(another.getEnd()) == 0)
                return -1;
            else
                return 1;
        }
        return start.compareTo(another.getStart());
    }

    @Override
    public String toString()
    {
        return start.toString() + " - " + end.toString();
    }
}
