package com.ropulva.CalendarManagement.util;

import com.google.api.client.util.DateTime;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class DateTimeFormatterUtil {

    public static Date getCurrentDate(){
        return Date.valueOf(LocalDate.now());
    }

    public static Time getCurrentTime(){
        return  Time.valueOf(LocalTime.now());
    }

    public static Timestamp getCurrentTimestamp(){
        return Timestamp.valueOf(LocalDateTime.now());
    }

    public static Timestamp combineDateAndTime(Date date, Time time) {
        Timestamp timestamp = new Timestamp(date.getTime());
        timestamp.setTime(timestamp.getTime() + time.getTime() % (24 * 60 * 60 * 1000));

        return timestamp;
    }

    public static DateTime timestampToGoogleDateTime(Timestamp timestamp, String timeZoneId) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssXXX")
                .withZone(ZoneId.of(timeZoneId));
        String formattedDateTime = formatter.format(timestamp.toInstant());
        return new DateTime(formattedDateTime);
    }
}
