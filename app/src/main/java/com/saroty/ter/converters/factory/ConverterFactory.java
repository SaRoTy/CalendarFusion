package com.saroty.ter.converters.factory;

import com.saroty.ter.converters.Converter;
import com.saroty.ter.converters.cellcat.CelcatConverter;
import com.saroty.ter.converters.exception.NoConverterFoundException;
import com.saroty.ter.converters.factory.enums.TrustedCelcatHostsEnum;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Arthur on 09/03/2015.
 */

public class ConverterFactory implements IConverterFactory
{
    @Override
    public Converter makeConverter(URL url) throws NoConverterFoundException
    {
        if (TrustedCelcatHostsEnum.contains(url.getHost()))
            return makeCelcat(url, true);

        throw new NoConverterFoundException(url);
    }

    private Converter makeCelcat(URL url, boolean trusted) throws NoConverterFoundException
    {
        if (url.getPath().endsWith(".xml"))
            return new CelcatConverter(url, trusted);
        else if (url.getPath().endsWith(".html"))
        {
            try
            {
                return new CelcatConverter(new URL(url.getPath().replaceAll(".html$", ".xml")), trusted);
            } catch (MalformedURLException e)
            {
            }
        }
        throw new NoConverterFoundException(url);
    }
}
