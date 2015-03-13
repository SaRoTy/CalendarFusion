package com.saroty.ter.schedule.util;

import com.saroty.ter.ScheduleApplication;
import com.saroty.ter.schedule.Schedule;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Created by root on 11/03/15.
 */
public class ScheduleFileUtil
{
    private static final String SCHEDULES_DIR = "/schedules";

    public static Schedule loadSchedule(String name) throws IOException, ClassNotFoundException
    {
        FileInputStream fis = new FileInputStream(new File(ScheduleApplication.getContext().getFilesDir().getAbsolutePath() + SCHEDULES_DIR + name));
        ObjectInputStream is = new ObjectInputStream(fis);
        Schedule schedule = (Schedule) is.readObject();
        is.close();
        fis.close();
        return schedule;
    }

    public static void saveSchedule(Schedule schedule) throws IOException
    {
        //TODO: Filtrer nom des fichiers
        FileOutputStream fos = new FileOutputStream(new File(ScheduleApplication.getContext().getFilesDir().getAbsolutePath() + SCHEDULES_DIR + schedule.getName()));
        ObjectOutputStream os = new ObjectOutputStream(fos);
        os.writeObject(schedule);
        os.close();
        fos.close();
    }

    public static String[] getScheduleList()
    {
        return ScheduleApplication.getContext().getFilesDir().list();
    }
}
