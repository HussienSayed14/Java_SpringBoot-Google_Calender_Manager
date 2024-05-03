package com.ropulva.CalendarManagement.event;

import com.ropulva.CalendarManagement.event.requests.CreateEventRequest;
import com.ropulva.CalendarManagement.util.GenericResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EventService {
    private static final Logger logger = LoggerFactory.getLogger(EventService.class);

    public ResponseEntity<GenericResponse> createEvent(CreateEventRequest request) {
        GenericResponse response = new GenericResponse();

        try{
            EventModel event = EventModel.builder()
                    .build();

        }catch (Exception e){
            logger.error(e.getMessage());
            response.setServerErrorHappened();

        }

        return  ResponseEntity.status(response.getHttpStatus()).body(response);
    }
}
