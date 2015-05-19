package com.saroty.ter.schedule.filter.decorator;

import com.saroty.ter.schedule.filter.IScheduleFilter;

/**
 * Created by Arthur on 19/05/2015.
 */
public abstract class ScheduleFilterDecorator implements IScheduleFilter
{
    IScheduleFilter mFilter;

    public ScheduleFilterDecorator(IScheduleFilter filter)
    {
        mFilter = filter;
    }


}
