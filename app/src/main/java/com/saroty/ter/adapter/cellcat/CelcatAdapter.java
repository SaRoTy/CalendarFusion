package com.saroty.ter.adapter.cellcat;

import com.saroty.ter.adapter.Adapter;
import com.saroty.ter.adapter.exception.AdapterException;
import com.saroty.ter.adapter.exception.AdapterParsingException;
import com.saroty.ter.schedule.ScheduleCourse;
import com.saroty.ter.schedule.ScheduleDay;
import com.saroty.ter.schedule.ScheduleTable;
import com.saroty.ter.schedule.ScheduleWeek;

import org.apache.http.HttpResponse;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.net.URL;

/**
 * Created by Arthur on 09/03/2015.
 */
public class CelcatAdapter extends Adapter
{
    public CelcatAdapter(URL url, boolean trusted)
    {
        super(url, trusted);
    }


    @Override
    public ScheduleTable adapt() throws AdapterException
    {
        HttpResponse response = loadUrl();
        System.out.println(response.getEntity().getContentType());
        if (!response.getEntity().getContentType().getValue().endsWith("/xml"))
            throw new AdapterParsingException(url);

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

            ScheduleTable table = new ScheduleTable();
            ScheduleWeek curWeek = null;
            ScheduleDay curDay = null;
            ScheduleCourse curCourse = null;

            while (eventType != XmlPullParser.END_DOCUMENT)
            {
                if (eventType == XmlPullParser.START_TAG)
                {
                    if (xpp.getName().equals("span"))//TODO: Switch ?
                    {
                        curWeek = new ScheduleWeek();
                        table.addWeek(Integer.parseInt(xpp.getAttributeValue(null, "rawix")), curWeek);
                    } else if (curWeek != null)
                    {
                        if (xpp.getName().equals("day"))
                        {

                        }
                    }
                } else if (eventType == XmlPullParser.END_TAG)
                {
                    if (xpp.getName().equals("span"))
                        curWeek = null;

                } else if (eventType == XmlPullParser.TEXT)
                {

                }
                eventType = xpp.next();
            }
            System.out.println(table.weekCount());
            return table;
        } catch (Exception e)
        {
            throw new AdapterParsingException(url);
        }
    }
}
