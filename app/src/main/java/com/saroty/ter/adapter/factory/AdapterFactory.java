package com.saroty.ter.adapter.factory;

import com.saroty.ter.adapter.Adapter;
import com.saroty.ter.adapter.cellcat.CelcatAdapter;
import com.saroty.ter.adapter.exception.NoAdapterFoundException;
import com.saroty.ter.adapter.factory.enums.TrustedCelcatHostsEnum;

import java.net.URL;

/**
 * Created by Arthur on 09/03/2015.
 */

public class AdapterFactory implements IAdapterFactory
{
    @Override
    public Adapter makeAdapter(URL url) throws NoAdapterFoundException
    {
        if (TrustedCelcatHostsEnum.contains(url.getHost()))
            return makeCelcat(url, true);
        throw new NoAdapterFoundException(url);
    }

    private Adapter makeCelcat(URL url, boolean trusted) throws NoAdapterFoundException
    {
        if (url.getPath().endsWith(".xml"))
            return new CelcatAdapter(url, true);
        else if (url.getPath().endsWith(".html"))
            ;//TODO: .html -> .xml
        throw new NoAdapterFoundException(url);
    }
}
