package com.saroty.ter.adapter.exception;

import java.net.URL;

/**
 * Created by root on 09/03/15.
 */
public class NoAdapterFoundException extends AdapterException
{
    public NoAdapterFoundException(URL url)
    {
        super(url);
    }
}
