package com.saroty.ter.task;

import android.os.AsyncTask;

import com.saroty.ter.adapter.factory.AdapterFactory;
import com.saroty.ter.schedule.Schedule;

import java.net.URL;

/**
 * Created by Arthur on 09/03/2015.
 */
public class AdaptScheduleTask extends AsyncTask<URL, Integer, Schedule>
{
    private static AdapterFactory factory = new AdapterFactory();
    private Exception exception;

    @Override
    protected Schedule doInBackground(URL... urls)
    {
        //TODO: Implement for multiple URLS

        try
        {
            return factory.makeAdapter(urls[0]).adapt();
        } catch (Exception e)
        {
            this.exception = e;
        }
        return null;
    }

    @Override
    public void onPostExecute(Schedule table)
    {
        if (exception != null)
            exception.printStackTrace();
    }
}
