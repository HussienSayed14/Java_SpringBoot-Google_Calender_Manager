package com.ropulva.CalendarManagement.event;

import com.ropulva.CalendarManagement.creator.CreatorModel;
import com.ropulva.CalendarManagement.creator.CreatorService;
import com.ropulva.CalendarManagement.event.requests.CreateEventRequest;
import com.ropulva.CalendarManagement.util.DateTimeFormatter;
import com.ropulva.CalendarManagement.util.GenericResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EventService {

    private final CreatorService creatorService;
    private final EventRepository eventRepository;

    private static final Logger logger = LoggerFactory.getLogger(EventService.class);

    public ResponseEntity<GenericResponse> createEvent(CreateEventRequest request) {
        GenericResponse response = new GenericResponse();

        try{
            CreatorModel eventCreator = creatorService.
                    createAndGetCreator(request.getCreatorEmail(), request.getCreatorName());
            if(eventCreator == null){
                response.setServerErrorHappened();
                return  ResponseEntity.status(response.getHttpStatus()).body(response);
            }

            if(createEvent(request,eventCreator)){
                response.setSuccessful();
            }else {
                response.setServerErrorHappened();
            }

        }catch (Exception e){
            logger.error(e.getMessage());
            response.setServerErrorHappened();
        }
        return  ResponseEntity.status(response.getHttpStatus()).body(response);
    }

    private boolean createEvent(CreateEventRequest request,CreatorModel eventCreator){

        try{

            EventModel event = EventModel.builder()
                    .created(DateTimeFormatter.getCurrentTimestamp())
                    .creator(eventCreator)
                    .description(request.getDescription())
                    .summary(request.getSummary())
                    .colorId(request.getColorId())
                    .status("Pending")
                    .startDate(request.getStartDate())
                    .endDate(request.getEndDate())
                    .startTime(request.getStartTime())
                    .endTime(request.getEndTime())
                    .updates(null)
                    .build();
            eventRepository.save(event);
            return true;
        }catch (Exception e){
            logger.error(e.getMessage());
            return false;
        }

    }


}
