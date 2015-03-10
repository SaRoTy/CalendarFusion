package com.saroty.ter.adapter;

import com.saroty.ter.adapter.exception.AdapterException;
import com.saroty.ter.schedule.Schedule;

/**
 * Created by Arthur on 09/03/2015.
 */
public interface IAdapter
{
    public Schedule adapt() throws AdapterException;
}
