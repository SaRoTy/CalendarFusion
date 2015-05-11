package com.saroty.ter.schedule;

import com.saroty.ter.R;
import com.saroty.ter.ScheduleApplication;
import com.saroty.ter.time.LocalTimeInterval;

import java.io.Serializable;
import java.net.URL;
import java.util.Map;
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

    private TreeMap<DateTime, TreeMap<LocalTimeInterval, Course>> mSchedule;

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
        for (Map.Entry<DateTime, TreeMap<LocalTimeInterval, Course>> c : mSchedule.entrySet())
        {
            if (c.getKey().isSameDayAs(date))
            {
                c.getValue().put(interval, course);
                return;
            }
        }
        TreeMap<LocalTimeInterval, Course> newMap = new TreeMap<>();
        newMap.put(interval, course);
        mSchedule.put(date, newMap);
        mLastUpdate = DateTime.now(TimeZone.getDefault());
    }

    public Map<LocalTimeInterval, Course> getDailyCourses(DateTime day)
    {
        for (Map.Entry<DateTime, TreeMap<LocalTimeInterval, Course>> c : mSchedule.entrySet())
        {
            if (c.getKey().isSameDayAs(day))
                return c.getValue();
        }
        return new TreeMap<>();
    }

    public int getEventCount()
    {
        return mSchedule.size();
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