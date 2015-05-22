package com.saroty.ter.schedule.filter;

import com.saroty.ter.schedule.Course;
import com.saroty.ter.time.LocalTimeInterval;

/**
 * Created by Arthur on 19/05/2015.
 */
public interface IScheduleFilter
{
    public boolean filter(LocalTimeInterval interval, Course course);
}
