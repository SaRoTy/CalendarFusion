package com.saroty.ter.task;

import android.content.Context;
import android.content.ContextWrapper;
import android.os.AsyncTask;

import com.saroty.ter.adapter.factory.AdapterFactory;
import com.saroty.ter.schedule.Schedule;

import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * Created by Arthur on 09/03/2015.
 */
public class AdaptScheduleTask extends AsyncTask<URL, Integer, Schedule>
{
    private static AdapterFactory factory = new AdapterFactory();
    private Exception exception;

    protected Context context;

    public AdaptScheduleTask(Context context)
    {
        this.context = context.getApplicationContext();
    }

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
        /*try
        {
            //File f = new File(context.getFilesDir().getAbsolutePath() + "/a");
            //table.saveSchedule(f);
            //System.out.println(Schedule.loadSchedule(f));
        } catch (IOException e)
        {
            exception = e;
        } catch (ClassNotFoundException e)
        {
            exception = e;
        }*/

        if (exception != null)
            exception.printStackTrace();
    }
}
