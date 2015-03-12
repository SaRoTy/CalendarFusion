package com.saroty.ter.converters.exception;

import java.net.URL;

/**
 * Created by root on 09/03/15.
 */
public class NoConverterFoundException extends ConverterException
{
    public NoConverterFoundException(URL url)
    {
        super(url);
    }
}
