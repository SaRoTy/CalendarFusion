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
import java.util.Calendar;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.ExecutionException;

/**
 * Created by Romain on 24/03/2015.
 */
public class ListCoursesOfDayFragment extends Fragment{

    private ListView mList;
    private TreeMap<LocalTimeInterval,Course> mListDay;
    private int mWeek;
    private int mDay;
    private int mYear;
    private ListCourseOfDayRowAdapter mAdapter;
    private  Schedule mSchedule = null;
    private TextView mTextView;

    public ListCoursesOfDayFragment() {
        this.mListDay = null;
        this.mAdapter = null;
        this.mWeek = Calendar.WEEK_OF_YEAR;
        this.mDay = Calendar.DAY_OF_WEEK;
        this.mYear = Calendar.YEAR;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_list_courses_of_day, container, false);

        this.mList = (ListView) rootView.findViewById(R.id.list_courses_of_day);
        this.mTextView = new TextView(getActivity().getApplicationContext());
        mList.addHeaderView(mTextView);

        AdaptScheduleTask a = new AdaptScheduleTask(getActivity().getApplicationContext());

        try {
            a.execute(new URL("https://celcatfsi.ups-tlse.fr/FSIpargroupes/g558.xml"));
            this.mSchedule = a.get();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        loadEDT();

        /*rootView.setOnTouchListener(new MyListener(getActivity().getApplicationContext()){
            @Override
            public void onSwipeLeft() {
                jourApres();
                loadEDT();
            }

            @Override
            public void onSwipeRight() {
                jourAvant();
                loadEDT();
            }
        });*/

        return rootView;
    }

    private int jourAvant(){
        this.mDay = (this.mDay -1) % 7;
        return this.mDay;
    }

    private int jourApres(){
        this.mDay = (this.mDay +1) % 7;
        return this.mDay;
    }

    private void loadEDT(){
        CourseRowModel[] model;
        int i=0;

        //TODO : changer de hardcode
        mListDay = mSchedule.getWeekByWeekNumber(33).getDay(DayOfWeek.getById(this.mDay)).getCourses();
        model = new CourseRowModel[mListDay.size()];

        for(Map.Entry<LocalTimeInterval,Course> cours : mListDay.entrySet()){
            model[i] = new CourseRowModel(cours.getValue().getTitle(),cours.getKey().toString());
            Log.v("debug romain - Model",cours.getValue().getTitle()+" "+cours.getKey().toString());
            i++;
        }

        mAdapter = new ListCourseOfDayRowAdapter(getActivity().getApplicationContext(),model);

        Calendar calendar = Calendar.getInstance();

        calendar.set(Calendar.WEEK_OF_YEAR,this.mWeek);
        calendar.set(Calendar.DAY_OF_WEEK,this.mDay);

        setTitleListView(calendar.getTime().toString());

        mList.setAdapter(mAdapter);

    }

    public void setTitleListView(String title){
        this.mTextView.setText(title);
    }

    public void onTaskFinished(Schedule s) {
        //...


        //Course[] mListDay = s.getDayByDate(new Date()).toArray();

        //CourseDay list  = s.getDayByDate(new Date());

        /*Log.v("debug task finished",""+mListDay.length);
        ListCourseOfDayRowAdapter adapter = new ListCourseOfDayRowAdapter(this.getActivity().getApplicationContext(), ModelToRowModel.CourseToRow(mListDay));

        mList.setAdapter(adapter);*/
    }
}
