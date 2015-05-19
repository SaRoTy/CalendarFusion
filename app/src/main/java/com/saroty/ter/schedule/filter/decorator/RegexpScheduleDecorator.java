package com.saroty.ter.schedule.filter.decorator;

import com.saroty.ter.schedule.filter.IScheduleFilter;

/**
 * Created by Arthur on 19/05/2015.
 */
public class RegexpScheduleDecorator extends ScheduleFilterDecorator
{
    public RegexpScheduleDecorator(IScheduleFilter filter)
    {
        super(filter);
    }
}
