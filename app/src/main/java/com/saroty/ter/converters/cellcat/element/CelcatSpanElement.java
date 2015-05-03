package com.saroty.ter.converters.cellcat.element;

import com.saroty.ter.converters.cellcat.element.enums.CelcatElementEnum;
import com.saroty.ter.schedule.Schedule;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.Map;

/**
 * Created by Arthur on 30/04/2015.
 */
public class CelcatSpanElement extends CelcatElement
{
    private int mRawix;
    private String mDate;

    private String mDescription;
    private String mTitle;
    private Map<Integer, String> mDays;

    @Override
    public boolean parse(XmlPullParser pullParser) throws IOException, XmlPullParserException
    {
        int event = pullParser.getEventType();

        mRawix = Integer.parseInt(pullParser.getAttributeValue(null, "rawix"));
        mDate = pullParser.getAttributeValue(null, "date");

        while (!(event == XmlPullParser.END_TAG && pullParser.getName().equalsIgnoreCase(CelcatElementEnum.SPAN.getTag())))
        {
            event = pullParser.next();
        }

        return false;
    }

    public int getRawix()
    {
        return mRawix;
    }

    public String getDate()
    {
        return mDate;
    }

    @Override
    public void convert(Schedule table)
    {

    }
}
