package com.saroty.ter.models.list;

import com.saroty.ter.schedule.Course;
import com.saroty.ter.time.LocalTimeInterval;

/**
 * Created by Romain on 24/03/2015.
 */
public class CourseRowModel
{

    private Course mCourse;
    private LocalTimeInterval mInterval;

    public CourseRowModel(LocalTimeInterval interval, Course course)
    {
        mInterval = interval;
        mCourse = course;
    }

    public LocalTimeInterval getInterval()
    {
        return mInterval;
    }

    public Course getCourse()
    {
        return mCourse;
    }
}
