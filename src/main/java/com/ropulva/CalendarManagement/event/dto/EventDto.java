package com.ropulva.CalendarManagement.event.dto;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;

public class EventDto implements Serializable {
    private long id;
    private String description;
    private String title;
    private Date start_date;
    private Date end_date;
    private Time start_time;
    private Time end_time;

    // Default constructor needed for JPA/Hibernate
    public EventDto() {
    }

    // Constructor used for query results mapping
    public EventDto(long id, String description, String title, Date start_date, Date end_date, Time start_time, Time end_time) {
        this.id = id;
        this.description = description;
        this.title = title;
        this.start_date = start_date;
        this.end_date = end_date;
        this.start_time = start_time;
        this.end_time = end_time;
    }

    // Getters and setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getStart_date() {
        return start_date;
    }

    public void setStart_date(Date start_date) {
        this.start_date = start_date;
    }

    public Date getEnd_date() {
        return end_date;
    }

    public void setEnd_date(Date end_date) {
        this.end_date = end_date;
    }

    public Time getStart_time() {
        return start_time;
    }

    public void setStart_time(Time start_time) {
        this.start_time = start_time;
    }

    public Time getEnd_time() {
        return end_time;
    }

    public void setEnd_time(Time end_time) {
        this.end_time = end_time;
    }
}
