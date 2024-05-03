package com.ropulva.CalendarManagement.event.dto;

import java.sql.Date;
import java.sql.Time;

public interface EventDto {

    long getId();
    String getDescription();
    String getTitle();
    Date getStart_date();
    Date getEnd_date();
    Time getStart_time();
    Time getEnd_time();
}
