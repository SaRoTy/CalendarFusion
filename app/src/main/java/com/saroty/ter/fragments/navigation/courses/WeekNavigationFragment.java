package com.saroty.ter.fragments.navigation.courses;

import android.graphics.RectF;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alamkanak.weekview.DateTimeInterpreter;
import com.alamkanak.weekview.WeekView;
import com.alamkanak.weekview.WeekViewEvent;
import com.saroty.ter.R;
import com.saroty.ter.ScheduleApplication;
import com.saroty.ter.activities.MainActivity;
import com.saroty.ter.fragments.navigation.NavigationFragment;
import com.saroty.ter.utils.ScheduleToEventsUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Romain on 09/05/2015.
 */
public class WeekNavigationFragment extends NavigationFragment implements WeekView.MonthChangeListener,
        WeekView.EventClickListener, WeekView.EventLongPressListener
{
    private static final int TYPE_DAY_VIEW = 1;
    private static final int TYPE_THREE_DAY_VIEW = 2;
    private static final int TYPE_WEEK_VIEW = 3;
    private int mWeekViewType = TYPE_WEEK_VIEW;
    private WeekView mWeekView;

    private final int LENGTH_SHORT = 1;
    private final int LENGTH_LONG = 2;
    private int mDayNameLength = LENGTH_SHORT;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {

        View rootView = inflater.inflate(R.layout.fragment_week_navigation, container, false);

        mWeekView = (WeekView) rootView.findViewById(R.id.weekView);

        // Show a toast message about the touched event.
        mWeekView.setOnEventClickListener(this);

        // The week view has infinite scrolling horizontally. We have to provide the events of a
        // month every time the month changes on the week view.
        mWeekView.setMonthChangeListener(this);

        // Set long press listener for events.
        mWeekView.setEventLongPressListener(this);

        mWeekView.goToHour(6);


        //TODO : peut etre passer ça en anglais à voir
        mWeekView.setDateTimeInterpreter(new DateTimeInterpreter() {
            @Override
            public String interpretDate(Calendar date) {
                SimpleDateFormat sdf;
                sdf = mDayNameLength == LENGTH_SHORT ? new SimpleDateFormat("EEE") : new SimpleDateFormat("EEEE");
                try
                {
                    String dayName = sdf.format(date.getTime()).toUpperCase();
                    return String.format("%s %d/%02d", dayName, date.get(Calendar.MONTH) + 1, date.get(Calendar.DAY_OF_MONTH));
                } catch (Exception e)
                {
                    e.printStackTrace();
                    return "";
                }
            }

            @Override
            public String interpretTime(int hour) {
                return String.format("%02d h", hour);
            }
        });


        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public String getNavigationTitle()
    {
        return  ScheduleApplication.getContext().getString(R.string.title_navigation_week);
    }

    @Override
    public String getActionbarTitle()
    {
        return ScheduleApplication.getContext().getString(R.string.title_navigation_week);
    }

    @Override
    public void onEventClick(WeekViewEvent event, RectF eventRect) {
        DetailCourseFragment fragment = ((ScheduleToEventsUtils)event).goToDetail();
        ((MainActivity) getActivity()).setCurrentFragment(fragment, true);
    }

    @Override
    public void onEventLongPress(WeekViewEvent event, RectF eventRect) {

    }

    @Override
    public List<WeekViewEvent> onMonthChange(int newYear, int newMonth) {
        List<WeekViewEvent> events = new ArrayList<WeekViewEvent>();

        events = ScheduleToEventsUtils.getEvents(newMonth, newYear);

        return events;
    }
}
