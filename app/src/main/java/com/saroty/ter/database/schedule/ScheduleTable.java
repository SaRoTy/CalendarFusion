package com.saroty.ter.database.schedule;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.saroty.ter.ScheduleApplication;
import com.saroty.ter.database.DatabaseHelper;
import com.saroty.ter.database.DatabaseTable;
import com.saroty.ter.schedule.Schedule;
import com.saroty.ter.utils.SerializableUtils;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Arthur on 07/05/2015.
 */
public class ScheduleTable extends DatabaseTable<Schedule>
{
    private final String KEY_SCHEDULE = "schedule";

    public ScheduleTable()
    {
        super("schedules", "CREATE TABLE " + "schedules" + " ( " + "schedule" + " BLOB )");
    }

    @Override
    public void insertOne(Schedule schedule)
    {
        try
        {
            SQLiteDatabase db = DatabaseHelper.getInstance(ScheduleApplication.getContext()).getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(KEY_SCHEDULE, SerializableUtils.serializableToByteArray(schedule));
            db.insert(TABLE_NAME, null, values);
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public List<Schedule> readAll()
    {
        List<Schedule> schedules = new LinkedList<Schedule>();
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = DatabaseHelper.getInstance(ScheduleApplication.getContext()).getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        try
        {
            if (cursor.moveToFirst())
            {
                do
                {
                    schedules.add((Schedule) SerializableUtils.serializableFromByteArray(cursor.getBlob(0)));
                } while (cursor.moveToNext());
            }

            return schedules;
        } catch (IOException | ClassNotFoundException e)
        {
            e.printStackTrace();
        }

        return schedules;
    }
}
