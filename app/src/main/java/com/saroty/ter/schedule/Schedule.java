package com.saroty.ter.schedule;

import android.util.Log;

import com.saroty.ter.R;
import com.saroty.ter.ScheduleApplication;
import com.saroty.ter.models.list.ManagerCourseRowModel;
import com.saroty.ter.schedule.filter.IScheduleFilter;
import com.saroty.ter.time.LocalTimeInterval;

import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;
import java.util.TreeMap;
import java.util.UUID;

import hirondelle.date4j.DateTime;

/**
 * Created by Arthur on 09/03/2015.
 */
public class Schedule implements Serializable
{
    private static final long serialVersionUID = -7151757052818244043L;
    private final String DEFAULT_GROUP_NAME = ScheduleApplication.getContext().getString(R.string.schedule_group_default);
    private String mGroupName = DEFAULT_GROUP_NAME;
    private String mName = "defaultSchedule";
    private DateTime mLastUpdate;

    private UUID mUUID;
    private boolean mEnabled = true;
    private URL mBaseUrl;
    private transient boolean mUpdating;

    private TreeMap<DateTime, TreeMap<LocalTimeInterval, List<Course>>> mSchedule;

    public Schedule(){
        mUUID = UUID.randomUUID();
        mSchedule = new TreeMap<>();
        mLastUpdate = DateTime.now(TimeZone.getDefault());
        mBaseUrl = null;
    }

    public Schedule(URL baseUrl)
    {
        mBaseUrl = baseUrl;
        mUUID = UUID.randomUUID();
        mSchedule = new TreeMap<>();
        mLastUpdate = DateTime.now(TimeZone.getDefault());
    }

    public String getName()
    {
        return mName;
    }

    public void setName(String mName)
    {
        this.mName = mName;
    }

    public void addCourse(Course course, DateTime date, LocalTimeInterval interval)
    {
        for (Map.Entry<DateTime, TreeMap<LocalTimeInterval, List<Course>>> c : mSchedule.entrySet())
        {
            if (c.getKey().isSameDayAs(date))
            {
                if (c.getValue().containsKey(interval))
                    c.getValue().get(interval).add(course);
                else
                {
                    Log.v("debug romain","nouvelle liste");
                    List<Course> newList = new ArrayList<>();
                    newList.add(course);
                    c.getValue().put(interval, newList);
                }
                return;
            }
        }

        Log.v("debug romain","nouvelle treemap");
        TreeMap<LocalTimeInterval, List<Course>> newMap = new TreeMap<>();
        List<Course> newList = new ArrayList<>();
        newList.add(course);
        newMap.put(interval, newList);
        mSchedule.put(date, newMap);
        mLastUpdate = DateTime.now(TimeZone.getDefault());
    }

    public Map<LocalTimeInterval, List<Course>> getDailyCourses(DateTime day)
    {
        for (Map.Entry<DateTime, TreeMap<LocalTimeInterval, List<Course>>> c : mSchedule.entrySet())
        {
            if (c.getKey().isSameDayAs(day))
                return c.getValue();
        }
        return new TreeMap<>();
    }

    public Map<LocalTimeInterval, List<Course>> getDailyCourses(DateTime day, IScheduleFilter filter)
    {
        for (Map.Entry<DateTime, TreeMap<LocalTimeInterval, List<Course>>> c : mSchedule.entrySet())
        {
            if (c.getKey().isSameDayAs(day))
            {
                Map<LocalTimeInterval, List<Course>> newMap = new TreeMap<>();

                for (Map.Entry<LocalTimeInterval, List<Course>> course : c.getValue().entrySet())
                {
                    List<Course> filteredCourses = new ArrayList<>();

                    for (Course i : course.getValue())
                        if (filter.filter(course.getKey(), i))
                            filteredCourses.add(i);
                    newMap.put(course.getKey(), filteredCourses);

                }

                return newMap;
            }
        }
        return new TreeMap<>();
    }

    public int getEventCount()
    {
        int size = 0;

        for(Map.Entry<DateTime,TreeMap<LocalTimeInterval,List<Course>>> entry : mSchedule.entrySet()){
            for(Map.Entry<LocalTimeInterval,List<Course>> entrymap : entry.getValue().entrySet()){
                size = size + entrymap.getValue().size();
            }
        }

        return size;
    }

    public Set<ManagerCourseRowModel> getSetEvent(){
        Set<ManagerCourseRowModel> set = new HashSet<>();

        for(Map.Entry<DateTime,TreeMap<LocalTimeInterval,List<Course>>> entry : mSchedule.entrySet()){
            for(Map.Entry<LocalTimeInterval,List<Course>> entrymap : entry.getValue().entrySet()){
                for(Course course : entrymap.getValue()){
                    set.add(new ManagerCourseRowModel(course.getTitle(),course.getColor()));
                }
            }
        }

        return set;
    }

    @Override
    public String toString()
    {
        String result = "[Schedule]\n";

        return result;
    }

    public DateTime getLastUpdate()
    {
        return mLastUpdate;
    }

    public UUID getUUID()
    {
        return mUUID;
    }

    public void setUUID(UUID mUUID)
    {
        this.mUUID = mUUID;
    }

    public boolean isEnabled()
    {
        return mEnabled;
    }

    public void setEnabled(boolean mEnabled)
    {
        this.mEnabled = mEnabled;
    }

    public String getGroupName()
    {
        return mGroupName;
    }

    public void setGroupName(String mGroupName)
    {
        this.mGroupName = mGroupName;
    }

    public URL getBaseUrl()
    {
        return mBaseUrl;
    }

    public boolean isUpdating()
    {
        return mUpdating;
    }

    public void setUpdating(boolean mUpdating)
    {
        this.mUpdating = mUpdating;
    }
}