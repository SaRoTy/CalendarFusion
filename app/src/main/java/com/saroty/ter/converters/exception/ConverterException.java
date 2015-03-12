package com.saroty.ter.converters.exception;

import java.net.URL;

/**
 * Created by root on 09/03/15.
 */
public abstract class ConverterException extends Exception
{
    protected URL url; //TODO: Url possiblement inutile.

    public ConverterException(URL url)
    {
        this.url = url;
    }
}
