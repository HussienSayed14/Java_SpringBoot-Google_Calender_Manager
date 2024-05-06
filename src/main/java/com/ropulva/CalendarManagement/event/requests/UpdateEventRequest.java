package com.ropulva.CalendarManagement.event.requests;

;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class UpdateEventRequest {
    @NotNull
    private long eventId;
    private Timestamp startDate;
    private Timestamp endDate;
    private String description;
    private String title;
}
