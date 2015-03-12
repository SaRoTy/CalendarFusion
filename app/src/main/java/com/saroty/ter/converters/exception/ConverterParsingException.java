package com.saroty.ter.converters.exception;

import java.net.URL;

/**
 * Created by Arthur on 10/03/2015.
 */
public class ConverterParsingException extends ConverterException
{
    public ConverterParsingException(URL url)
    {
        super(url);
    }
}
