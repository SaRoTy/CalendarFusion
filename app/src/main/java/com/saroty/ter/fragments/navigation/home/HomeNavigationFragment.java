package com.saroty.ter.fragments.navigation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.saroty.ter.R;
import com.saroty.ter.ScheduleApplication;
import com.saroty.ter.adapters.CourseRowAdapter;
import com.saroty.ter.models.list.CourseRowModel;
import com.saroty.ter.schedule.Course;
import com.saroty.ter.schedule.ScheduleManager;
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
    private CalendarDialogFragment mCalendarDialog;
    private Thread mThread;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        Button CalendarButton = (Button)rootView.findViewById(R.id.calendar);
        mCalendarDialog = CalendarDialogFragment.newInstance(this);

        mCourseList = (ListView) rootView.findViewById(R.id.course_list);
        mDayText = (TextView) rootView.findViewById(R.id.text_day);
        mDateText = (TextView) rootView.findViewById(R.id.text_date);

        DateTime today = DateTime.today(TimeZone.getDefault());

        String dayString = today.format("WWWW", Locale.getDefault());

        mDayText.setText(Character.toUpperCase(dayString.charAt(0)) + dayString.substring(1));
        CalendarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getActivity().getSupportFragmentManager();

                mCalendarDialog.show(fm, "Calendar");
            }
        });


        updateDate();

        Map<LocalTimeInterval, Course> courses = ScheduleManager.getInstance().getDailyCourses(today);

        if (courses.size() > 0)
        {
            CourseRowModel[] data = new CourseRowModel[courses.size()];

            int i = 0;

            for (Map.Entry<LocalTimeInterval, Course> c : courses.entrySet())
            {
                data[i] = new CourseRowModel(c.getKey(), c.getValue());
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
        return ScheduleApplication.getContext().getString(R.string.title_navigation_home);
    }

    @Override
    public String getActionbarTitle()
    {
        return ScheduleApplication.getContext().getString(R.string.title_home);
    }

    @Override
    public void onButtonOkClick() {
        ((MainActivity) getActivity()).setCurrentFragment(2,mCalendarDialog.getBundle());
        mCalendarDialog.getDialog().dismiss();
    }
}
