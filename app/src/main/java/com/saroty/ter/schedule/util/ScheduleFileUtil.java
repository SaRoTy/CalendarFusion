package com.saroty.ter.schedule.util;

import com.saroty.ter.ScheduleApplication;
import com.saroty.ter.schedule.Schedule;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by root on 11/03/15.
 */
public class ScheduleFileUtil
{
    private static final String SCHEDULES_DIR = "/schedules";
    private static final String FILES_PREFIX = "s_";
    private static final String SCHEDULES_LIST_FILE = "list";
    private static ScheduleList scheduleList = new ScheduleList();


    public static Schedule loadSchedule(String name) throws IOException, ClassNotFoundException
    {
        return (Schedule) loadObject(ScheduleApplication.getContext().getFilesDir().getAbsolutePath() + SCHEDULES_DIR + name);
    }

    public static void saveSchedule(Schedule schedule) throws IOException
    {
        saveObject(schedule, ScheduleApplication.getContext().getFilesDir().getAbsolutePath() + SCHEDULES_DIR + schedule.getName());
    }

    public static String[] getScheduleList()
    {
        return ScheduleApplication.getContext().getFilesDir().list();
    }

    private static void saveObject(Serializable object, String path) throws IOException
    {
        FileOutputStream fos = new FileOutputStream(new File(path));
        ObjectOutputStream os = new ObjectOutputStream(fos);
        os.writeObject(object);
        os.close();
        fos.close();
    }

    private static Object loadObject(String path) throws IOException, ClassNotFoundException
    {
        FileInputStream fis = new FileInputStream(new File(path));
        ObjectInputStream is = new ObjectInputStream(fis);
        Object object = is.readObject();
        is.close();
        fis.close();
        return object;
    }

    private static class ScheduleList implements Serializable
    {
        private static final long serialVersionUID = 5922156061064170476L;

        private ArrayList<String> list = new ArrayList<String>();

        public void addList(String s)
        {
            try
            {
                list.add(s);
                save();
            } catch (IOException e)
            {
                e.printStackTrace();
            }
        }

        private void save() throws IOException
        {
            saveObject(this, ScheduleApplication.getContext().getFilesDir().getAbsolutePath() + SCHEDULES_DIR + "/" + SCHEDULES_LIST_FILE);
        }
    }
}
