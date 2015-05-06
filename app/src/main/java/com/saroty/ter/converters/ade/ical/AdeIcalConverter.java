package com.saroty.ter.converters.ade.ical;

import android.util.Log;

import com.saroty.ter.converters.Converter;
import com.saroty.ter.converters.exception.ConverterException;
import com.saroty.ter.converters.exception.ConverterParsingException;
import com.saroty.ter.schedule.Course;
import com.saroty.ter.schedule.Schedule;
import com.saroty.ter.time.LocalTime;
import com.saroty.ter.time.LocalTimeInterval;

import org.apache.http.HttpResponse;

import java.net.URL;
import java.util.TimeZone;

import biweekly.Biweekly;
import biweekly.ICalendar;
import biweekly.component.VEvent;
import hirondelle.date4j.DateTime;

/**
 * Created by Arthur on 06/05/2015.
 */
public class AdeIcalConverter extends Converter
{

    public AdeIcalConverter(URL url, boolean trusted)
    {
        super(url, trusted);
    }

    @Override
    public Schedule convert() throws ConverterException
    {
        HttpResponse response = loadUrl();

        if (!response.getEntity().getContentType().getValue().contains("/calendar"))
            throw new ConverterParsingException(url);

        Schedule table = new Schedule();

        try
        {
            ICalendar ical = Biweekly.parse(response.getEntity().getContent()).first();
            table.setName("AdeWeb");
            for (VEvent event : ical.getEvents())
            {
                Course course = new Course(event.getSummary().getValue());
                course.setRoom(event.getLocation().getValue());
                table.addCourse(course, DateTime.forInstant(event.getDateStart().getValue().getTime(),
                        TimeZone.getDefault()), new LocalTimeInterval(new LocalTime(event.getDateStart().getValue()), new LocalTime(event.getDateEnd().getValue())));
            }
        } catch (Exception e)
        {
            Log.d("a", e.getMessage());
            e.printStackTrace();
            throw new ConverterParsingException(url);
        }

        return table;
    }
}
