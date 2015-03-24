package com.saroty.ter.converters.factory;

import com.saroty.ter.converters.Converter;
import com.saroty.ter.converters.exception.NoConverterFoundException;

import java.net.URL;

/**
 * Created by Arthur on 09/03/2015.
 */
public interface IConverterFactory
{
    Converter makeConverter(URL url) throws NoConverterFoundException;
}
