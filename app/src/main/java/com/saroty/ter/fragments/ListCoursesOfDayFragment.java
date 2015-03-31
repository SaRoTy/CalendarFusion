package com.saroty.ter.fragments;

import android.support.v4.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.saroty.ter.R;
import com.saroty.ter.adapters.ListCourseOfDayRowAdapter;
import com.saroty.ter.converters.exception.ConverterException;
import com.saroty.ter.converters.exception.NoConverterFoundException;
import com.saroty.ter.converters.factory.ConverterFactory;
import com.saroty.ter.models.list.CourseRowModel;
import com.saroty.ter.schedule.Course;
import com.saroty.ter.schedule.CourseDay;
import com.saroty.ter.schedule.Schedule;
import com.saroty.ter.schedule.util.ModelToRowModel;
import com.saroty.ter.tasks.AdaptScheduleTask;

import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutionException;

/**
 * Created by Romain on 24/03/2015.
 */
public class ListCoursesOfDayFragment extends Fragment {

    private ListView mList;

    public ListCoursesOfDayFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_list_courses_of_day, container, false);

        mList = (ListView) rootView.findViewById(R.id.list_courses_of_day);
        Context context = getActivity().getApplicationContext();

        //AdaptScheduleTask task = new AdaptScheduleTask(context);
        Schedule schedule = null;
        Course[] listDay = null;
        ListCourseOfDayRowAdapter adapter = null;


        Log.v("Hello debug ", "probleme");

        AdaptScheduleTask a = new AdaptScheduleTask(this.getActivity().getApplicationContext());

        try {
            a.execute(new URL("https://celcatfsi.ups-tlse.fr/FSIpargroupes/g558.xml"));
            schedule = a.get();
            Log.v("debug",schedule.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }


        return rootView;
    }

    public void onTaskFinished(Schedule s) {
        //...


        //Course[] listDay = s.getDayByDate(new Date()).toArray();

        //CourseDay list  = s.getDayByDate(new Date());

        /*Log.v("debug task finished",""+listDay.length);
        ListCourseOfDayRowAdapter adapter = new ListCourseOfDayRowAdapter(this.getActivity().getApplicationContext(), ModelToRowModel.CourseToRow(listDay));

        mList.setAdapter(adapter);*/
    }

}
