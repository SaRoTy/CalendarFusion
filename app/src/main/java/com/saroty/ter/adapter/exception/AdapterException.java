package com.saroty.ter.adapter.exception;

import java.net.URL;

/**
 * Created by root on 09/03/15.
 */
public abstract class AdapterException extends Exception
{
    protected URL url;

    public AdapterException(URL url)
    {
        this.url = url;
    }
}
