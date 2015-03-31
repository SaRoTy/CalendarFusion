package com.saroty.ter.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.saroty.ter.Listener.MyListener;
import com.saroty.ter.R;
import com.saroty.ter.adapters.ListCourseOfDayRowAdapter;
import com.saroty.ter.models.list.CourseRowModel;
import com.saroty.ter.schedule.Course;
import com.saroty.ter.schedule.Schedule;
import com.saroty.ter.tasks.AdaptScheduleTask;
import com.saroty.ter.time.DayOfWeek;
import com.saroty.ter.time.LocalTimeInterval;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.ExecutionException;

/**
 * Created by Romain on 24/03/2015.
 */
public class ListCoursesOfDayFragment extends Fragment{

    private ListView mList;
    private TreeMap<LocalTimeInterval,Course> listDay;
    private int semaine;
    private int jour;
    private ListCourseOfDayRowAdapter adapter;
    private  Schedule schedule = null;
    private TextView textView;

    public ListCoursesOfDayFragment() {
        this.listDay = null;
        this.semaine = 33;
        this.adapter = null;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_list_courses_of_day, container, false);

        this.mList = (ListView) rootView.findViewById(R.id.list_courses_of_day);
        this.textView = new TextView(getActivity().getApplicationContext());
        mList.addHeaderView(textView);

        AdaptScheduleTask a = new AdaptScheduleTask(getActivity().getApplicationContext());

        try {
            a.execute(new URL("https://celcatfsi.ups-tlse.fr/FSIpargroupes/g558.xml"));
            this.schedule = a.get();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        loadEDT();

        rootView.setOnTouchListener(new MyListener(getActivity().getApplicationContext()){
            @Override
            public void onSwipeLeft() {
                jourAvant();
                loadEDT();
            }

            @Override
            public void onSwipeRight() {
                jourApres();
                loadEDT();
            }
        });

        return rootView;
    }

    private int jourAvant(){
        this.jour = (this.jour-1) % 7;
        return this.jour;
    }

    private int jourApres(){
        this.jour = (this.jour+1) % 7;
        return this.jour;
    }

    private void loadEDT(){
        CourseRowModel[] model;
        int i=0;

        listDay = schedule.getWeekByWeekNumber(this.semaine).getDay(DayOfWeek.getById(this.jour)).getCourses();
        model = new CourseRowModel[listDay.size()];

        for(Map.Entry<LocalTimeInterval,Course> cours : listDay.entrySet()) {
            model[i] = new CourseRowModel(cours.getValue().getTitle(),cours.getKey().toString());
            Log.v("debug romain - Model",cours.getValue().getTitle()+" "+cours.getKey().toString());
            i++;
        }

        adapter = new ListCourseOfDayRowAdapter(getActivity().getApplicationContext(),model);

        mList.setAdapter(adapter);

    }

    public void setTitleListView(String title){
        this.textView.setText(title);
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
