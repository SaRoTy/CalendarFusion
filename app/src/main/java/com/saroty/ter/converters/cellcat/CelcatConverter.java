package com.saroty.ter.converters.cellcat;

import com.saroty.ter.converters.Converter;
import com.saroty.ter.converters.exception.ConverterException;
import com.saroty.ter.converters.exception.ConverterParsingException;
import com.saroty.ter.schedule.Course;
import com.saroty.ter.schedule.CourseDay;
import com.saroty.ter.schedule.CourseWeek;
import com.saroty.ter.schedule.Schedule;
import com.saroty.ter.time.DayOfWeek;
import com.saroty.ter.time.LocalTime;
import com.saroty.ter.time.LocalTimeInterval;

import org.apache.http.HttpResponse;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Arthur on 09/03/2015.
 */
public class CelcatConverter extends Converter
{
    public CelcatConverter(URL url, boolean trusted)
    {
        super(url, trusted);
    }


    @Override
    public Schedule convert() throws ConverterException
    {
        HttpResponse response = loadUrl();
        if (!response.getEntity().getContentType().getValue().endsWith("/xml"))
            throw new ConverterParsingException(url);

        XmlPullParserFactory factory = null;
        try
        {
            factory = XmlPullParserFactory.newInstance();
            factory.setNamespaceAware(true);
            XmlPullParser xpp = factory.newPullParser();
            String encoding = "UTF-8";
            if (response.getEntity().getContentEncoding() != null)
                encoding = response.getEntity().getContentEncoding().getValue();

            xpp.setInput(response.getEntity().getContent(), encoding);

            int eventType = xpp.getEventType();

            Schedule table = new Schedule();

            DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            int weekShift = -1;

            CourseWeek curWeek = null;

            Course curCourse = null;
            LocalTime curCourseStart = null;
            LocalTime curCourseEnd = null;
            DayOfWeek curCourseDay = null;

            String text = "";

            while (eventType != XmlPullParser.END_DOCUMENT)
            {
                if (eventType == XmlPullParser.START_TAG)
                {
                    if (xpp.getName().equalsIgnoreCase("span"))//TODO: Switch ?
                    {
                        int rawix = Integer.parseInt(xpp.getAttributeValue(null, "rawix"));
                        curWeek = new CourseWeek();
                        if (weekShift == -1)
                        {

                            Date date = format.parse(xpp.getAttributeValue(null, "date"));
                            Calendar cal = Calendar.getInstance();
                            cal.setTime(date);
                            weekShift = rawix - cal.get(Calendar.WEEK_OF_YEAR);
                        }
                        table.addWeek((rawix - weekShift) % 52, curWeek);
                    } else if (xpp.getName().equalsIgnoreCase("event"))
                    {
                        curCourse = new Course();
                    }
                    if (curWeek != null && xpp.getName().equalsIgnoreCase("day"))
                    {
                        curWeek.addDay(DayOfWeek.getById(Integer.parseInt(xpp.getAttributeValue(null, "id"))), new CourseDay());
                    }

                } else if (eventType == XmlPullParser.END_TAG)
                {
                    if (xpp.getName().equalsIgnoreCase("span"))
                        curWeek = null;
                    else if (xpp.getName().equalsIgnoreCase("subheading"))
                        table.setName(text);
                    else if (xpp.getName().equalsIgnoreCase("event"))
                    {
                        curCourse = null;
                        curCourseStart = null;
                        curCourseEnd = null;
                        curCourseDay = null;
                    } else if (curCourse != null)
                    {
                        if (xpp.getName().equalsIgnoreCase("day"))
                            curCourseDay = DayOfWeek.getById(Integer.parseInt(text));
                        else if (xpp.getName().equalsIgnoreCase("category"))
                            curCourse.setCategory(text);
                        else if (xpp.getName().equalsIgnoreCase("module"))
                            curCourse.setTitle(text);
                        else if (xpp.getName().equalsIgnoreCase("starttime"))
                            curCourseStart = new LocalTime(text, ":");
                        else if (xpp.getName().equalsIgnoreCase("endtime"))
                            curCourseEnd = new LocalTime(text, ":");
                        else if (xpp.getName().equalsIgnoreCase("rawweeks") && curCourseDay != null && curCourseEnd != null && curCourseStart != null)
                        {
                            //TODO : Je suis pas sûr a 100%, mais je pense qu'il peut y avoir plusieurs "Y"
                            for (int index = text.indexOf("Y"); index >= 0; index = text.indexOf("Y", index + 1))
                            {
                                table.getWeekByWeekNumber((index + 1 - weekShift) % 52).getDay(curCourseDay).addCourse(new LocalTimeInterval(curCourseStart, curCourseEnd), curCourse);
                            }
                        }
                    }

                } else if (eventType == XmlPullParser.TEXT)
                {
                    text = xpp.getText();
                }
                eventType = xpp.next();
            }
            return table;
        } catch (Exception e)
        {
            e.printStackTrace();
            throw new ConverterParsingException(url);
        }
    }
}
