package com.saroty.ter.adapter.factory;

import com.saroty.ter.adapter.Adapter;
import com.saroty.ter.adapter.cellcat.CellcatXmlAdapter;
import com.saroty.ter.adapter.exception.NoAdapterFoundException;
import com.saroty.ter.adapter.factory.enums.VerifiedCellcatHostsEnum;

import java.net.URL;

/**
 * Created by Arthur on 09/03/2015.
 */

public class AdapterFactory implements IAdapterFactory
{
    @Override
    public Adapter makeAdapter(URL url) throws NoAdapterFoundException
    {
        if(VerifiedCellcatHostsEnum.contains(url.getHost()))
            return makeCellcat(url);
        throw new NoAdapterFoundException(url); //TODO: Exceptions plus spécifique
    }

    private Adapter makeCellcat(URL url) throws NoAdapterFoundException
    {
        if(url.getPath().endsWith(".xml"))
            return new CellcatXmlAdapter(url);

        throw new NoAdapterFoundException(url);
    }
}
