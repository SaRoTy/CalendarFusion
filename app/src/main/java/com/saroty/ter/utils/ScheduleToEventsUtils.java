package com.saroty.ter.utils;

import android.util.Log;

import com.alamkanak.weekview.WeekView;
import com.alamkanak.weekview.WeekViewEvent;
import com.saroty.ter.activities.MainActivity;
import com.saroty.ter.fragments.navigation.courses.DetailCourseFragment;
import com.saroty.ter.schedule.Course;
import com.saroty.ter.schedule.Schedule;
import com.saroty.ter.schedule.ScheduleManager;
import com.saroty.ter.time.LocalTime;
import com.saroty.ter.time.LocalTimeInterval;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import biweekly.property.Timezone;
import hirondelle.date4j.DateTime;

/**
 * Created by Romain on 11/05/2015.
 */
public class ScheduleToEventsUtils extends WeekViewEvent {

    private Course mCourse;
    private LocalTimeInterval mInter;
    private DateTime mTime;

    public ScheduleToEventsUtils(long id, String name, int startYear, int startMonth, int startDay, int startHour, int startMinute, int endYear, int endMonth, int endDay, int endHour, int endMinute){
        super(id, name,  startYear,  startMonth,  startDay,  startHour,  startMinute,  endYear,  endMonth,  endDay,  endHour,  endMinute);
    }

    public void setDetails(Course course, LocalTimeInterval inter, DateTime time){
        this.mCourse = course;
        this.mInter = inter;
        this.mTime = time;
    }

    public DetailCourseFragment goToDetail(){
        return DetailCourseFragment.newInstance(mInter,mCourse,mTime);
    }




    /**
     * Converti nos events en events gerer par l'almanak
     * @return liste des events contenu dans le schedule
     *
     *
     */
    //TODO : demander a Arthur pour la gestion de l'id
    public static List<WeekViewEvent> getEvents(int _month, int _year){
        List<WeekViewEvent> events = new ArrayList<>();
        DateTime date = DateTime.forDateOnly(_year, _month,1);
        Integer id = 0;
        WeekViewEvent e;
        LocalTime start;
        LocalTime end;
        int month;

        //date = date.minusDays(date.getNumDaysInMonth());
        month = date.getMonth();

       while(date.getMonth() == month) {

           for (Map.Entry entry : ScheduleManager.getInstance().getDailyCourses(date).entrySet()) {
               start = ((LocalTimeInterval) entry.getKey()).getStart();
               end = ((LocalTimeInterval) entry.getKey()).getEnd();
               e = new ScheduleToEventsUtils(id, ((Course) entry.getValue()).getTitle(),
                       date.getYear(),
                       date.getMonth(),
                       date.getDay(),
                       start.getHour(),
                       start.getMinute(),
                       date.getYear(),
                       date.getMonth(),
                       date.getDay(),
                       end.getHour(),
                       end.getMinute()
               );

               ((ScheduleToEventsUtils)e).setDetails((Course)entry.getValue(),(LocalTimeInterval)entry.getKey(),date);
               e.setColor(((Course) entry.getValue()).getColor());

               events.add(e);
               id++;
           }

           date = date.plusDays(1);

       }

        return events;
    }
}
