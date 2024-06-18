package br.com.it.helpdesk.domain.services;

import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Component
public class ScheduleValidationService {

    public boolean validadeTicketSchedule(LocalDateTime dateTime){
        var dayOfWeek = dateTime.getDayOfWeek();

        if (dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY) {
            return false;
        }

        LocalTime time = dateTime.toLocalTime();
        LocalTime startTime = LocalTime.of(7, 30);
        LocalTime endTime = LocalTime.of(18, 0);

        return !time.isBefore(startTime) && !time.isAfter(endTime);
    }
}
