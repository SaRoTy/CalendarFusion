package com.saroty.ter.tasks;

import android.content.Context;
import android.os.AsyncTask;

import com.saroty.ter.converters.factory.ConverterFactory;
import com.saroty.ter.schedule.Schedule;

import java.net.URL;

/**
 * Created by Arthur on 09/03/2015.
 */
public class AdaptScheduleTask extends AsyncTask<URL, Void, Schedule>
{
    private static ConverterFactory factory = new ConverterFactory();
    protected Context context;
    private Exception exception;

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
