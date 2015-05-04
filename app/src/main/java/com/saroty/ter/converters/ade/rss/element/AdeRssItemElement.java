package com.saroty.ter.converters.ade.rss.element;

import com.saroty.ter.converters.cellcat.element.enums.CelcatElementEnum;
import com.saroty.ter.schedule.Schedule;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

/**
 * Created by Arthur on 04/05/2015.
 */
public class AdeRssItemElement extends AdeRssElement
{
    @Override
    public boolean parse(XmlPullParser pullParser) throws IOException, XmlPullParserException
    {
        int event = pullParser.getEventType();

        String curText = "";

        while (!(event == XmlPullParser.END_TAG && pullParser.getName().equalsIgnoreCase(CelcatElementEnum.OPTION.getTag())))
        {
            if (event == XmlPullParser.TEXT)
                curText = pullParser.getText();
            else if (event == XmlPullParser.END_TAG)
            {

            }

            event = pullParser.next();
        }

        return true;
    }

    @Override
    public void convert(Schedule table)
    {

    }
}
