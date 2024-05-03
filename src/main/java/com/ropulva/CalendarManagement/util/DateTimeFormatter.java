package com.ropulva.CalendarManagement.util;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class DateTimeFormatter {

    public static Date getCurrentDate(){
        return Date.valueOf(LocalDate.now());
    }

    public static Time getCurrentTime(){
        return  Time.valueOf(LocalTime.now());
    }

    public static Timestamp getCurrentTimestamp(){
        return Timestamp.valueOf(LocalDateTime.now());
    }
}
