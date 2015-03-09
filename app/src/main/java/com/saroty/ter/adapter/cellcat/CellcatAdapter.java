package com.saroty.ter.adapter.cellcat;

import com.saroty.ter.adapter.Adapter;
import com.saroty.ter.schedule.ScheduleTable;

import org.apache.http.HttpResponse;

import java.net.URL;

/**
 * Created by Arthur on 09/03/2015.
 */
public class CellcatAdapter extends Adapter
{
    public CellcatAdapter(URL url, boolean trusted)
    {
        super(url, trusted);
    }


    @Override
    public ScheduleTable adapt()
    {
        ScheduleTable table = new ScheduleTable();
        HttpResponse response = loadUrl();

        System.out.println(response.getEntity().getContentType());

        return table;
    }
}
