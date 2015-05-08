package com.saroty.ter.schedule;

import com.saroty.ter.R;
import com.saroty.ter.ScheduleApplication;
import com.saroty.ter.database.DatabaseHelper;
import com.saroty.ter.database.schedule.ScheduleTable;
import com.saroty.ter.time.LocalTimeInterval;

import java.util.Iterator;
import java.util.Map;
import java.util.Observable;
import java.util.TreeMap;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import hirondelle.date4j.DateTime;

/**
 * Created by Arthur on 08/05/2015.
 */
public class ScheduleManager extends Observable
{
    private static ScheduleManager INSTANCE = new ScheduleManager();

    private final String DEFAULT_GROUP_NAME = ScheduleApplication.getContext().getString(R.string.schedule_group_default);

    private Map<String, ScheduleGroup> mScheduleGroups = new ConcurrentHashMap<>();

    private ScheduleManager()
    {
        createScheduleGroup(DEFAULT_GROUP_NAME);
        loadSchedules();
    }

    public static ScheduleManager getInstance()
    {
        return INSTANCE;
    }

    public void deleteSchedule(UUID scheduleUUID)
    {
        for (ScheduleGroup group : mScheduleGroups.values())
        {
            Iterator<Schedule> iter = group.getScheduleList().iterator();
            while (iter.hasNext())
            {
                Schedule curSchedule = iter.next();
                if (curSchedule.getUUID().equals(scheduleUUID))
                {
                    ((ScheduleTable) DatabaseHelper.getInstance().getTable(ScheduleTable.class)).deleteOne(curSchedule);
                    iter.remove();
                }
            }
        }
        setChanged();
        notifyObservers();
    }

    public void createScheduleGroup(String name)
    {
        if (mScheduleGroups.containsKey(name))
            return;
        mScheduleGroups.put(name, new ScheduleGroup(name));
        setChanged();
        notifyObservers();
    }

    public void addSchedule(Schedule schedule, boolean save)
    {
        createScheduleGroup(schedule.getGroupName());
        mScheduleGroups.get(schedule.getGroupName()).addSchedule(schedule);
        if (save)
            ((ScheduleTable) DatabaseHelper.getInstance().getTable(ScheduleTable.class)).insertOne(schedule);
        setChanged();
        notifyObservers();
    }

    public TreeMap<LocalTimeInterval, Course> getDailyCourses(DateTime day)
    {
        TreeMap<LocalTimeInterval, Course> map = new TreeMap<>();
        for (ScheduleGroup group : mScheduleGroups.values())
        {
            if (group.isEnabled())
            {
                for (Schedule schedule : group.getScheduleList())
                {
                    if (schedule.isEnabled())
                        map.putAll(schedule.getDailyCourses(day));
                }
            }
        }
        return map;
    }

    public void disableGroup(String groupName)
    {
        mScheduleGroups.get(groupName).setEnabled(false);
        setChanged();
        notifyObservers();
    }

    public void enableGroup(String groupName)
    {
        mScheduleGroups.get(groupName).setEnabled(true);
        setChanged();
        notifyObservers();
    }

    private void loadSchedules()
    {
        for (Schedule s : ((ScheduleTable) DatabaseHelper.getInstance().getTable(ScheduleTable.class)).readAll())
        {
            addSchedule(s, false);
        }
    }

    public ScheduleGroup[] getGroups()
    {
        return mScheduleGroups.values().toArray(new ScheduleGroup[mScheduleGroups.values().size()]);
    }

    public void disableSchedule(UUID uuid)
    {
        for (ScheduleGroup group : mScheduleGroups.values())
        {
            Iterator<Schedule> iter = group.getScheduleList().iterator();
            while (iter.hasNext())
            {
                Schedule curSchedule = iter.next();
                if (curSchedule.getUUID().equals(uuid))
                {
                    curSchedule.setEnabled(false);
                    ((ScheduleTable) DatabaseHelper.getInstance().getTable(ScheduleTable.class)).updateOne(curSchedule);
                }
            }
        }
        setChanged();
        notifyObservers();
    }
}
