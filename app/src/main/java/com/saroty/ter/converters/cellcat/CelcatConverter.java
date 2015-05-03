package com.saroty.ter.converters.cellcat;

import com.saroty.ter.converters.Converter;
import com.saroty.ter.converters.cellcat.element.CelcatElement;
import com.saroty.ter.converters.cellcat.element.CelcatSpanElement;
import com.saroty.ter.converters.cellcat.element.factory.CelcatElementFactory;
import com.saroty.ter.converters.exception.ConverterException;
import com.saroty.ter.converters.exception.ConverterParsingException;
import com.saroty.ter.schedule.Schedule;

import org.apache.http.HttpResponse;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Arthur on 09/03/2015.
 */
public class CelcatConverter extends Converter
{
    public CelcatConverter(URL url, boolean trusted)
    {
        super(url, trusted);
    }

    public Schedule convert() throws ConverterException
    {
        HttpResponse response = loadUrl();

        if (!response.getEntity().getContentType().getValue().endsWith("/xml"))
            throw new ConverterParsingException(url);

        Schedule table = new Schedule();

        try
        {
            XmlPullParser xpp = XmlPullParserFactory.newInstance().newPullParser();
            CelcatElementFactory elementFactory = new CelcatElementFactory();
            String encoding = "UTF-8";
            if (response.getEntity().getContentEncoding() != null)
                encoding = response.getEntity().getContentEncoding().getValue();

            xpp.setInput(response.getEntity().getContent(), encoding);

            List<CelcatSpanElement> spans = new ArrayList<>();

            int eventType = xpp.getEventType();

            while (eventType != XmlPullParser.END_DOCUMENT)
            {
                if (eventType == XmlPullParser.START_TAG)
                {
                    CelcatElement element = elementFactory.make(xpp.getName().toLowerCase(), spans);

                    if (element instanceof CelcatSpanElement)
                    {
                        spans.add((CelcatSpanElement) element);
                    }
                    if (element.parse(xpp))
                        element.convert(table);
                }
                eventType = xpp.next();
            }

        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return table;
    }
}
