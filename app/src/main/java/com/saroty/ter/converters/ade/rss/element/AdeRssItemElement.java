package com.saroty.ter.converters.ade.rss.element;

import android.util.Log;

import com.saroty.ter.converters.ade.rss.element.enums.AdeRssElementEnum;
import com.saroty.ter.schedule.Schedule;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

/**
 * Created by Arthur on 04/05/2015.
 */
public class AdeRssItemElement extends AdeRssElement
{
    private int mGuid;
    private String mTitle;
    private String mDescription;
    private String mPubDate;

    @Override
    public boolean parse(XmlPullParser pullParser) throws IOException, XmlPullParserException
    {
        int event = pullParser.getEventType();

        String curText = "";

        while (!(event == XmlPullParser.END_TAG && pullParser.getName().equalsIgnoreCase(AdeRssElementEnum.ITEM.getTag())))
        {
            if (event == XmlPullParser.TEXT)
                curText = pullParser.getText();
            else if (event == XmlPullParser.END_TAG)
            {
                if (pullParser.getName().equalsIgnoreCase("guid"))
                    mGuid = Integer.parseInt(curText);
                else if (pullParser.getName().equalsIgnoreCase("title"))
                    mTitle = curText;
                else if (pullParser.getName().equalsIgnoreCase("description"))
                    mDescription = curText;
                else if (pullParser.getName().equalsIgnoreCase("pubDate"))
                    mPubDate = curText;
            }

            event = pullParser.next();
        }

        return true;
    }

    @Override
    public void convert(Schedule table)
    {
        Log.d("AdeRssItemElement", mDescription);
    }
}
