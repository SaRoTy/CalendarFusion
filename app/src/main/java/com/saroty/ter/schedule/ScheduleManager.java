package com.saroty.ter.schedule;

import android.os.AsyncTask;

import com.saroty.ter.R;
import com.saroty.ter.ScheduleApplication;
import com.saroty.ter.converters.factory.ConverterFactory;
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

    public Schedule getSchedule(UUID uuid)
    {
        for (ScheduleGroup group : mScheduleGroups.values())
        {
            Iterator<Schedule> iter = group.getScheduleList().iterator();
            while (iter.hasNext())
            {
                Schedule curSchedule = iter.next();
                if (curSchedule.getUUID().equals(uuid))
                {
                    return curSchedule;
                }
            }
        }
        return null;
    }

    public void disableSchedule(UUID uuid)
    {
        Schedule schedule = getSchedule(uuid);
        schedule.setEnabled(true);
        ((ScheduleTable) DatabaseHelper.getInstance().getTable(ScheduleTable.class)).updateOne(schedule);

        setChanged();
        notifyObservers();
    }

    public void enableSchedule(UUID uuid)
    {
        Schedule schedule = getSchedule(uuid);
        schedule.setEnabled(true);
        ((ScheduleTable) DatabaseHelper.getInstance().getTable(ScheduleTable.class)).updateOne(schedule);

        setChanged();
        notifyObservers();
    }

    public void renameSchedule(UUID uuid, String text)
    {
        getSchedule(uuid).setName(text);

        setChanged();
        notifyObservers();
    }

    public void updateSchedule(UUID uuid)
    {
        UpdateScheduleTask updateScheduleTask = new UpdateScheduleTask();
        Schedule schedule = getSchedule(uuid);
        schedule.setUpdating(true);
        updateScheduleTask.execute(schedule);

        setChanged();
        notifyObservers();
    }

    private void onUpdateSchedule(Schedule oldSchedule, Schedule newSchedule)
    {
        newSchedule.setName(oldSchedule.getName());
        newSchedule.setUUID(oldSchedule.getUUID());

        for (ScheduleGroup group : mScheduleGroups.values())
        {
            if (group.getScheduleList().contains(oldSchedule))
            {
                group.getScheduleList().set(group.getScheduleList().indexOf(oldSchedule), newSchedule);
            }
        }

        setChanged();
        notifyObservers();
    }

    private class UpdateScheduleTask extends AsyncTask<Schedule, Void, Schedule>
    {
        private Schedule mOldSchedule;
        private ConverterFactory mFactory = new ConverterFactory();
        private Exception mException;

        @Override
        protected Schedule doInBackground(Schedule... schedule)
        {
            try
            {
                mOldSchedule = schedule[0];
                return mFactory.makeConverter(schedule[0].getBaseUrl()).convert();
            } catch (Exception e)
            {
                this.mException = e;
            }
            return null;
        }

        @Override
        public void onPostExecute(Schedule table)
        {
            if (mException == null)
                onUpdateSchedule(mOldSchedule, table);
            else
            {
                mOldSchedule.setUpdating(false);
                setChanged();
                notifyObservers();
            }
        }
    }
}
