package com.saroty.ter.fragments.navigation;

import android.content.pm.ActivityInfo;
import android.graphics.RectF;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alamkanak.weekview.WeekView;
import com.alamkanak.weekview.WeekViewEvent;
import com.saroty.ter.R;
import com.saroty.ter.ScheduleApplication;

import java.util.ArrayList;
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


        return rootView;
    }

    @Override
    public String getNavigationTitle()
    {
        return  "Affichage semaine";
    }

    @Override
    public String getActionbarTitle()
    {
        return ScheduleApplication.getContext().getString(R.string.title_days);
    }

    @Override
    public void onEventClick(WeekViewEvent event, RectF eventRect) {

    }

    @Override
    public void onEventLongPress(WeekViewEvent event, RectF eventRect) {

    }

    @Override
    public List<WeekViewEvent> onMonthChange(int newYear, int newMonth) {
        List<WeekViewEvent> events = new ArrayList<WeekViewEvent>();

        return events;
    }
}
