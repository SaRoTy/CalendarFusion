package com.saroty.ter.schedule.filter.decorator;

import com.saroty.ter.schedule.Course;
import com.saroty.ter.schedule.filter.IScheduleFilter;
import com.saroty.ter.time.LocalTimeInterval;

/**
 * Created by Arthur on 19/05/2015.
 */
public class RegexpScheduleFilterDecorator extends ScheduleFilterDecorator
{
    private String mRegExp;

    public RegexpScheduleFilterDecorator(IScheduleFilter filter, String regExp)
    {
        super(filter);
        mRegExp = regExp;
    }

    @Override
    public boolean filter(LocalTimeInterval interval, Course course)
    {
        return (mFilter.filter(interval, course) && !course.getTitle().matches(mRegExp));
    }
}
