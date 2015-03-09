package com.saroty.ter.adapter.factory;

import com.saroty.ter.adapter.Adapter;
import com.saroty.ter.adapter.cellcat.CellcatAdapter;
import com.saroty.ter.adapter.exception.NoAdapterFoundException;
import com.saroty.ter.adapter.factory.enums.TrustedCellcatHostsEnum;

import java.net.URL;

/**
 * Created by Arthur on 09/03/2015.
 */

public class AdapterFactory implements IAdapterFactory
{
    @Override
    public Adapter makeAdapter(URL url) throws NoAdapterFoundException
    {
        if (TrustedCellcatHostsEnum.contains(url.getHost()))
            return makeCellcat(url, true);
        throw new NoAdapterFoundException(url); //TODO: Exceptions plus spécifiques
    }

    private Adapter makeCellcat(URL url, boolean trusted) throws NoAdapterFoundException
    {
        if(url.getPath().endsWith(".xml"))
            return new CellcatAdapter(url, true);
        else if (url.getPath().endsWith(".html"))
            ;//TODO: .html -> .xml
        throw new NoAdapterFoundException(url);
    }
}
