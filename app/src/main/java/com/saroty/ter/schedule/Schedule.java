package com.saroty.ter.schedule;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Map;
import java.util.Observable;
import java.util.TreeMap;

/**
 * Created by Arthur on 09/03/2015.
 */
public class Schedule implements Serializable
{
    private static final long serialVersionUID = -7151757052818244043L;
    private String name = "defaultSchedule";
    private TreeMap<Integer, CourseWeek> weeks;

    public Schedule()
    {
        weeks = new TreeMap<Integer, CourseWeek>();
    }

    public static Schedule loadSchedule(File loadFile) throws IOException, ClassNotFoundException
    {
        FileInputStream fis = new FileInputStream(loadFile);
        ObjectInputStream is = new ObjectInputStream(fis);
        Schedule schedule = (Schedule) is.readObject();
        is.close();
        fis.close();
        return schedule;
    }

    public void addWeek(int weekNumber, CourseWeek week)
    {
        weeks.put(weekNumber, week);
    }

    public int weekCount()
    {
        return weeks.size();
    }

    public CourseWeek getWeekByWeekNumber(int weekNumber)
    {
        return weeks.get(weekNumber);
    }

    @Override
    public String toString()
    {
        String result = "[Schedule]\n";
        for (Map.Entry<Integer, CourseWeek> entry : weeks.entrySet())
        {
            result += "(" + entry.getKey() + ")\n" + entry.getValue();
        }
        return result;
    }

    public void saveSchedule(File saveFile) throws IOException
    {
        FileOutputStream fos = new FileOutputStream(saveFile);
        ObjectOutputStream os = new ObjectOutputStream(fos);
        os.writeObject(this);
        os.close();
        fos.close();
    }
}
