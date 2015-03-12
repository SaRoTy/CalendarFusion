package com.saroty.ter.converters.exception;

import java.net.URL;

/**
 * Created by Arthur on 10/03/2015.
 */
public class ConverterConnectionException extends ConverterException
{
    public ConverterConnectionException(URL url)
    {
        super(url);
    }
}
