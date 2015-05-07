package com.saroty.ter.fragments.navigation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.saroty.ter.R;
import com.saroty.ter.activities.MainActivity;
import com.saroty.ter.adapters.CourseRowAdapter;
import com.saroty.ter.models.list.CourseRowModel;
import com.saroty.ter.schedule.Course;
import com.saroty.ter.time.LocalTimeInterval;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

import hirondelle.date4j.DateTime;

/**
 * Created by Arthur on 02/04/2015.
 */
public class HomeNavigationFragment extends NavigationFragment
{
    private ListView mCourseList;
    private TextView mDayText;
    private TextView mDateText;
    private Thread mThread;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        MainActivity mainActivity = (MainActivity) getActivity();

        mCourseList = (ListView) rootView.findViewById(R.id.course_list);
        mDayText = (TextView) rootView.findViewById(R.id.text_day);
        mDateText = (TextView) rootView.findViewById(R.id.text_date);

        DateTime today = DateTime.today(TimeZone.getDefault());

        String dayString = today.format("WWWW", Locale.getDefault());

        mDayText.setText(Character.toUpperCase(dayString.charAt(0)) + dayString.substring(1));
        updateDate();

        if (mainActivity.hasCurrentSchedule())
        {
            Map<LocalTimeInterval, Course> courses = mainActivity.getCurrentSchedule().getDailyCourses(today);

            CourseRowModel[] data = new CourseRowModel[courses.size()];

            int i = 0;

            for (Map.Entry<LocalTimeInterval, Course> c : courses.entrySet())
            {
                data[i] = new CourseRowModel(c.getValue().getTitle(), c.getKey(), c.getValue().getRoom(), c.getValue().getColor());
                i++;
            }

            mCourseList.setAdapter(new CourseRowAdapter(getActivity().getApplicationContext(), data));
        }

        mThread = new Thread()
        {

            @Override
            public void run()
            {
                try
                {
                    while (!isInterrupted())
                    {
                        Thread.sleep(1000);
                        getActivity().runOnUiThread(new Runnable()
                        {
                            @Override
                            public void run()
                            {
                                updateDate();
                            }
                        });
                    }
                } catch (InterruptedException e)
                {
                }
            }
        };

        mThread.start();

        return rootView;
    }

    private void updateDate()
    {
        final SimpleDateFormat dateTextFormat = new SimpleDateFormat("h:mm, dd/MM/yyyy", Locale.FRENCH);
        mDateText.setText(dateTextFormat.format(Calendar.getInstance().getTime()));
    }

    @Override
    public void onPause()
    {
        super.onPause();
        mThread.interrupt();
    }

    @Override
    public String getNavigationTitle()
    {
        return "Accueil";
    }

    @Override
    public String getActionbarTitle()
    {
        return "Accueil";
    }

}
