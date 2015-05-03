package com.saroty.ter.converters.cellcat.element;

import com.saroty.ter.converters.cellcat.element.enums.CelcatElementEnum;
import com.saroty.ter.schedule.Schedule;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

/**
 * Created by Arthur on 30/04/2015.
 */
public class CelcatOptionElement extends CelcatElement
{
    private String mSubHeading;

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
                if (pullParser.getName().equalsIgnoreCase("subheading"))
                    mSubHeading = curText;
            }

            event = pullParser.next();
        }

        return true;
    }

    @Override
    public void convert(Schedule table)
    {
        table.setName(mSubHeading);
    }
}
