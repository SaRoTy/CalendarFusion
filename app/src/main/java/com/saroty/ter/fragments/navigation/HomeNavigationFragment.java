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
import com.saroty.ter.time.DayOfWeek;
import com.saroty.ter.time.LocalTimeInterval;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;

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

        Calendar cal = Calendar.getInstance();
        int weekNumber = cal.get(Calendar.WEEK_OF_YEAR);
        String day = new SimpleDateFormat("EEEE", Locale.FRENCH).format(cal.getTime());

        mDayText.setText(Character.toUpperCase(day.charAt(0)) + day.substring(1));
        updateDate();


        if (mainActivity.hasCurrentSchedule() && mainActivity.getCurrentSchedule().getWeekByWeekNumber(weekNumber) != null &&
                mainActivity.getCurrentSchedule().getWeekByWeekNumber(weekNumber).getDay(DayOfWeek.getByCalendar(cal)) != null)
        {


            TreeMap<LocalTimeInterval, Course> courses = mainActivity.getCurrentSchedule().getWeekByWeekNumber(weekNumber).getDay(DayOfWeek.getByCalendar(cal)).getCourses();
            int i = 0;
            CourseRowModel[] data = new CourseRowModel[courses.size()];

            for (Map.Entry<LocalTimeInterval, Course> entry : courses.entrySet())
            {
                data[i] = new CourseRowModel(entry.getValue().getTitle(), entry.getKey(), entry.getValue().getRoom());
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
