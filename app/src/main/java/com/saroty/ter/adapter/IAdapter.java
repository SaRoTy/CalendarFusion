package com.saroty.ter.adapter;

import com.saroty.ter.adapter.exception.AdapterException;
import com.saroty.ter.schedule.ScheduleTable;

/**
 * Created by Arthur on 09/03/2015.
 */
public interface IAdapter
{
    public ScheduleTable adapt() throws AdapterException;
}
