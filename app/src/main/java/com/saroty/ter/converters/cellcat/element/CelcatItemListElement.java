package com.saroty.ter.converters.cellcat.element;

import com.saroty.ter.converters.cellcat.element.enums.CelcatElementEnum;
import com.saroty.ter.schedule.Schedule;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Arthur on 02/05/2015.
 */
public class CelcatItemListElement extends CelcatElement
{
    private List<String> mItems = new ArrayList<>();

    @Override
    public boolean parse(XmlPullParser pullParser) throws IOException, XmlPullParserException
    {
        int event = pullParser.getEventType();

        String curText = "";

        while (!(event == XmlPullParser.END_TAG && !pullParser.getName().equalsIgnoreCase(CelcatElementEnum.ITEM.getTag())))
        {
            if (event == XmlPullParser.TEXT)
                curText = pullParser.getText();
            else if (event == XmlPullParser.END_TAG)
            {
                if (pullParser.getName().equalsIgnoreCase("item"))
                    mItems.add(curText);
            }
            event = pullParser.next();
        }

        return false;
    }

    public List<String> getItems()
    {
        return mItems;
    }

    public boolean hasItems()
    {
        return !mItems.isEmpty();
    }

    @Override
    public void convert(Schedule table)
    {

    }
}
