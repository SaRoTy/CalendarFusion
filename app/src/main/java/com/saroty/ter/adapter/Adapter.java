package com.saroty.ter.adapter;

import java.net.URL;

/**
 * Created by Arthur on 09/03/2015.
 */
public abstract class Adapter implements IAdapter
{
    protected URL url;

    public Adapter(URL url)
    {
        this.url = url;
    }
}
