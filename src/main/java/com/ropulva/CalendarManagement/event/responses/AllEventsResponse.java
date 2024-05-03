package com.ropulva.CalendarManagement.event.responses;

import com.ropulva.CalendarManagement.event.dto.EventDto;
import com.ropulva.CalendarManagement.util.GenericResponse;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AllEventsResponse extends GenericResponse {
    List<EventDto> eventsList;
}
