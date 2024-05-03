package com.ropulva.CalendarManagement.event.responses;

import com.ropulva.CalendarManagement.event.EventModel;
import com.ropulva.CalendarManagement.event.dto.EventDto;
import com.ropulva.CalendarManagement.util.GenericResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AllEventsResponse extends GenericResponse implements Serializable {
    List<EventModel> eventsList;
}
