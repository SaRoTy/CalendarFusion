package com.saroty.ter.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.saroty.ter.database.schedule.ScheduleTable;

/**
 * Created by Arthur on 07/05/2015.
 */
public class DatabaseHelper extends SQLiteOpenHelper
{
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "ScheduleDB";
    private static DatabaseHelper mInstance;
    private final DatabaseTable[] mTables = {new ScheduleTable()};

    private DatabaseHelper(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static DatabaseHelper getInstance(Context context)
    {
        if (mInstance == null)
            mInstance = new DatabaseHelper(context.getApplicationContext());

        return mInstance;
    }

    public DatabaseTable getTable(Class<? extends DatabaseTable> c)
    {
        for (DatabaseTable table : mTables)
        {
            if (table.getClass().equals(c))
                return table;
        }
        return null;
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        Log.d("Database", "Creating database");
        for (DatabaseTable table : mTables)
        {
            db.execSQL(table.getTableCreateSQL());
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {

    }
}
