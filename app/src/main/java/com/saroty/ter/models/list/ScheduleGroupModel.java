package com.saroty.ter.models.list;

/**
 * Created by Arthur on 24/03/2015.
 */
public class ScheduleGroupModel
{
    private final ScheduleRowModel[] mScheduleRows;
    private String mName;

    public ScheduleGroupModel(String name, ScheduleRowModel[] scheduleRows)
    {
        this.mName = name;
        this.mScheduleRows = scheduleRows;
    }

    public int getRowCount()
    {
        return mScheduleRows.length;
    }

    public ScheduleRowModel getRow(int index)
    {
        return mScheduleRows[index];
    }

    public String getName()
    {
        return mName;
    }
}
