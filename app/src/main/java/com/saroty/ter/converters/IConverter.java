package com.saroty.ter.converters;

import com.saroty.ter.converters.exception.ConverterException;
import com.saroty.ter.schedule.Schedule;

/**
 * Created by Arthur on 09/03/2015.
 */
public interface IConverter
{
    public Schedule convert() throws ConverterException;
}
