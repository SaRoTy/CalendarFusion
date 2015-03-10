package com.saroty.ter.schedule;


import javax.xml.datatype.Duration;

/**
 * Created by Arthur on 09/03/2015.
 */
public class ScheduleCourse
{
    private String title;
    private Duration duration;

    public ScheduleCourse()
    {
    }

    public ScheduleCourse(String title, Duration duration)
    {
        this.title = title;
        this.duration = duration;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public void setDuration(Duration duration)
    {
        this.duration = duration;
    }
}
