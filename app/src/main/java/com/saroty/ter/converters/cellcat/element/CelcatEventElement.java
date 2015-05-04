package com.saroty.ter.converters.cellcat.element;

import android.graphics.Color;

import com.saroty.ter.converters.cellcat.element.enums.CelcatElementEnum;
import com.saroty.ter.schedule.Course;
import com.saroty.ter.schedule.Schedule;
import com.saroty.ter.time.LocalTime;
import com.saroty.ter.time.LocalTimeInterval;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.List;

import hirondelle.date4j.DateTime;

/**
 * Created by Arthur on 02/05/2015.
 */
public class CelcatEventElement extends CelcatElement
{
    private final List<CelcatSpanElement> mSpanList;

    private int mDay;
    private String mColor;
    private String mStartTime;
    private String mEndTime;
    private String mCategory;
    private String mRawWeeks;

    private CelcatItemListElement mModules = new CelcatItemListElement();
    private CelcatItemListElement mRooms = new CelcatItemListElement();
    private CelcatItemListElement mGroups = new CelcatItemListElement();

    public CelcatEventElement(List<CelcatSpanElement> spanList)
    {
        mSpanList = spanList;
    }

    @Override
    public boolean parse(XmlPullParser pullParser) throws IOException, XmlPullParserException
    {
        mColor = pullParser.getAttributeValue(null, "colour");

        int event = pullParser.getEventType();

        String curText = "";
        while (!(event == XmlPullParser.END_TAG && pullParser.getName().equalsIgnoreCase(CelcatElementEnum.EVENT.getTag())))
        {
            if (event == XmlPullParser.TEXT)
                curText = pullParser.getText();
            else if (event == XmlPullParser.START_TAG)
            {
                if (pullParser.getName().equalsIgnoreCase("module"))
                    mModules.parse(pullParser);
                else if (pullParser.getName().equalsIgnoreCase("room"))
                    mRooms.parse(pullParser);
                else if (pullParser.getName().equalsIgnoreCase("group"))
                    mGroups.parse(pullParser);
            } else if (event == XmlPullParser.END_TAG)
            {
                if (pullParser.getName().equalsIgnoreCase("day"))
                    mDay = Integer.parseInt(curText);
                else if (pullParser.getName().equalsIgnoreCase("starttime"))
                    mStartTime = curText;
                else if (pullParser.getName().equalsIgnoreCase("endtime"))
                    mEndTime = curText;
                else if (pullParser.getName().equalsIgnoreCase("category"))
                    mCategory = curText;
                else if (pullParser.getName().equalsIgnoreCase("rawweeks"))
                    mRawWeeks = curText;
            }
            event = pullParser.next();
        }
        return true;
    }

    @Override
    public void convert(Schedule table)
    {
        int rawix = mRawWeeks.indexOf('Y') + 1;
        for (CelcatSpanElement span : mSpanList)
        {
            if (rawix == span.getRawix())
            {
                String[] rawDate = span.getDate().split("/");
                DateTime date = new DateTime(rawDate[2] + '-' + rawDate[1] + '-' + rawDate[0]);
                date.plusDays(mDay);

                LocalTimeInterval interval = new LocalTimeInterval(new LocalTime(Integer.parseInt(mStartTime.split(":")[0]), Integer.parseInt(mStartTime.split(":")[1])), new LocalTime(Integer.parseInt(mEndTime.split(":")[0]), Integer.parseInt(mEndTime.split(":")[1])));
                Course course = new Course(mModules.hasItems() ? mModules.getItems().get(0) : "");

                if (mRooms.hasItems())
                    course.setRoom(mRooms.getItems().get(0));

                course.setColor(Color.parseColor(mColor));

                table.addCourse(course, date, interval);
            }
        }
    }
}