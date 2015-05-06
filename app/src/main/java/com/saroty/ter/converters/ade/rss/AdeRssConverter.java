package com.saroty.ter.converters.ade.rss;

import com.saroty.ter.converters.Converter;
import com.saroty.ter.converters.ade.rss.element.AdeRssElement;
import com.saroty.ter.converters.ade.rss.element.factory.AdeRssElementFactory;
import com.saroty.ter.converters.exception.ConverterException;
import com.saroty.ter.converters.exception.ConverterParsingException;
import com.saroty.ter.schedule.Schedule;

import org.apache.http.HttpResponse;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.net.URL;

/**
 * Created by Arthur on 04/05/2015.
 */
public class AdeRssConverter extends Converter
{
    public AdeRssConverter(URL url, boolean trusted)
    {
        super(url, trusted);
    }

    @Override
    public Schedule convert() throws ConverterException
    {
        HttpResponse response = loadUrl();

        if (!response.getEntity().getContentType().getValue().endsWith("/rss+xml"))
            throw new ConverterParsingException(url);

        Schedule table = new Schedule();

        try
        {
            XmlPullParser xpp = XmlPullParserFactory.newInstance().newPullParser();
            AdeRssElementFactory elementFactory = new AdeRssElementFactory();
            String encoding = "UTF-8";
            if (response.getEntity().getContentEncoding() != null)
                encoding = response.getEntity().getContentEncoding().getValue();

            xpp.setInput(response.getEntity().getContent(), encoding);

            int eventType = xpp.getEventType();

            while (eventType != XmlPullParser.END_DOCUMENT)
            {
                if (eventType == XmlPullParser.START_TAG)
                {
                    AdeRssElement element = elementFactory.make(xpp.getName().toLowerCase());

                    if (element.parse(xpp))
                        element.convert(table);
                }
                eventType = xpp.next();
            }

        } catch (Exception e)
        {
            e.printStackTrace();
            throw new ConverterParsingException(url);
        }
        return table;
    }
}
