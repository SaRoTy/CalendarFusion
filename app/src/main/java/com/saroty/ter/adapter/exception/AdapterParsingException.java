package com.saroty.ter.adapter.exception;

import java.net.URL;

/**
 * Created by Arthur on 10/03/2015.
 */
public class AdapterParsingException extends AdapterException
{
    public AdapterParsingException(URL url)
    {
        super(url);
    }
}
