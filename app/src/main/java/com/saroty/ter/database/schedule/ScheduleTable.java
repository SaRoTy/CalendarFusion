package com.saroty.ter.database.schedule;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.saroty.ter.database.DatabaseHelper;
import com.saroty.ter.database.DatabaseTable;
import com.saroty.ter.schedule.Schedule;
import com.saroty.ter.utils.SerializableUtils;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

/**
 * Created by Arthur on 07/05/2015.
 */
public class ScheduleTable extends DatabaseTable<Schedule, UUID>
{
    private final String KEY_SCHEDULE = "schedule";
    private final String KEY_UUID = "uuid";

    public ScheduleTable()
    {
        super("schedules", "CREATE TABLE " + "schedules" + " ( " + "uuid STRING PRIMARY KEY, " + "schedule" + " BLOB )");
    }

    @Override
    public void insertOne(Schedule schedule)
    {
        try
        {
            SQLiteDatabase db = DatabaseHelper.getInstance().getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(KEY_SCHEDULE, SerializableUtils.serializableToByteArray(schedule));
            values.put(KEY_UUID, schedule.getUUID().toString());
            db.insert(TABLE_NAME, null, values);
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteOne(Schedule s)
    {
        String query = "DELETE FROM " + TABLE_NAME + " WHERE " + KEY_UUID + "=\"" + s.getUUID().toString() + "\"";
        SQLiteDatabase db = DatabaseHelper.getInstance().getReadableDatabase();
        db.execSQL(query);
    }

    @Override
    public void updateOne(Schedule schedule)
    {
        try
        {
            SQLiteDatabase db = DatabaseHelper.getInstance().getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(KEY_SCHEDULE, SerializableUtils.serializableToByteArray(schedule));
            db.update(TABLE_NAME, values, KEY_UUID + "=\"" + schedule.getUUID().toString() + "\"", null);
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public Schedule readOne(UUID i)
    {
        String query = "SELECT " + KEY_SCHEDULE + " FROM " + TABLE_NAME + " WHERE " + KEY_UUID + "=\"" + i.toString() + "\"";
        SQLiteDatabase db = DatabaseHelper.getInstance().getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        try
        {
            if (cursor.moveToFirst())
            {
                return (Schedule) SerializableUtils.serializableFromByteArray(cursor.getBlob(0));
            }
        } catch (IOException | ClassNotFoundException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Schedule> readAll()
    {
        List<Schedule> schedules = new LinkedList<>();
        String query = "SELECT " + KEY_SCHEDULE + " FROM " + TABLE_NAME;
        SQLiteDatabase db = DatabaseHelper.getInstance().getReadableDatabase();
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
