package com.ropulva.CalendarManagement.event.requests;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.sql.Time;

@Getter
@Setter
public class CreateEventRequest {

    private String description;
    @NotEmpty
    private String title;
    private String colorId;
    @Email
    @NotEmpty
    private String creatorEmail;
    @NotEmpty
    private String creatorName;
    @FutureOrPresent
    private Date startDate;
    @FutureOrPresent
    private Date endDate;
    private Time startTime;
    private Time endTime;
}
