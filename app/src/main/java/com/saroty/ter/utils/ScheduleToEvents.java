package com.saroty.ter.utils;

import android.util.Log;

import com.alamkanak.weekview.WeekView;
import com.alamkanak.weekview.WeekViewEvent;
import com.saroty.ter.schedule.Course;
import com.saroty.ter.schedule.Schedule;
import com.saroty.ter.schedule.ScheduleManager;
import com.saroty.ter.time.LocalTime;
import com.saroty.ter.time.LocalTimeInterval;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import biweekly.property.Timezone;
import hirondelle.date4j.DateTime;

/**
 * Created by Romain on 11/05/2015.
 */
public class ScheduleToEvents {

    /**
     * Converti nos events en events gérer par l'almanak
     * @return liste des events contenu dans le schedule
     *
     *
     */
    //TODO : demander à Arthur pour la gestion de l'id
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



        Log.d("debug romain",date.toString());

       while(date.getMonth() == month) {

           for (Map.Entry entry : ScheduleManager.getInstance().getDailyCourses(date).entrySet()) {
               start = ((LocalTimeInterval) entry.getKey()).getStart();
               end = ((LocalTimeInterval) entry.getKey()).getEnd();
               e = new WeekViewEvent(id, ((Course) entry.getValue()).getTitle(),
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

               events.add(e);
               id++;
           }

           date = date.plusDays(1);

       }

        return events;
    }
}
