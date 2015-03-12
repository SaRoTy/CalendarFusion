package com.saroty.ter.converters.factory;

import com.saroty.ter.converters.Converter;
import com.saroty.ter.converters.cellcat.CelcatConverter;
import com.saroty.ter.converters.exception.NoConverterFoundException;
import com.saroty.ter.converters.factory.enums.TrustedCelcatHostsEnum;

import java.net.URL;

/**
 * Created by Arthur on 09/03/2015.
 */

public class ConverterFactory implements IConverterFactory
{
    @Override
    public Converter makeAdapter(URL url) throws NoConverterFoundException
    {
        if (TrustedCelcatHostsEnum.contains(url.getHost()))
            return makeCelcat(url, true);
        throw new NoConverterFoundException(url);
    }

    private Converter makeCelcat(URL url, boolean trusted) throws NoConverterFoundException
    {
        if (url.getPath().endsWith(".xml"))
            return new CelcatConverter(url, true);
        else if (url.getPath().endsWith(".html"))
            ;//TODO: .html -> .xml
        throw new NoConverterFoundException(url);
    }
}
