package com.saroty.ter.schedule;

import com.saroty.ter.time.LocalTimeInterval;

import java.io.Serializable;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by Arthur on 09/03/2015.
 */
public class CourseDay implements Serializable
{
    private static final long serialVersionUID = -8175003173226603925L;
    //On prend une TreeMap vu qu'on a implémenté Comparable sur LocalTime.
    private TreeMap<LocalTimeInterval, Course> courseList;

    public CourseDay()
    {
        courseList = new TreeMap<>();
    }

    public void addCourse(LocalTimeInterval interval, Course course)
    {
        courseList.put(interval, course);

    }

    //TODO : à changer
    /*public Course[] toArray(){
        return courseList.values().toArray(new Course[courseList.values().size()]);
    }*/

    public TreeMap<LocalTimeInterval, Course> getCourses(){
        return courseList;
    }

    @Override
    public String toString()
    {
        String result = "[CourseDay]\n";
        for (Map.Entry<LocalTimeInterval, Course> entry : courseList.entrySet())
        {
            result += "\t(" + entry.getKey().toString() + ")" + entry.getValue().toString() + "\n";
        }
        return result;
    }
}
