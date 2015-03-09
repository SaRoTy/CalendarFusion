package com.saroty.ter.adapter.exception;

import java.net.URL;

/**
 * Created by Arthur on 10/03/2015.
 */
public class AdapterConnectionException extends AdapterException
{
    public AdapterConnectionException(URL url)
    {
        super(url);
    }
}
