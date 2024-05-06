package com.ropulva.CalendarManagement.event;

import com.ropulva.CalendarManagement.creator.CreatorModel;
import com.ropulva.CalendarManagement.creator.CreatorService;
import com.ropulva.CalendarManagement.event.requests.CreateEventRequest;
import com.ropulva.CalendarManagement.event.requests.UpdateEventRequest;
import com.ropulva.CalendarManagement.event.responses.AllEventsResponse;
import com.ropulva.CalendarManagement.google.GoogleService;
import com.ropulva.CalendarManagement.util.DateTimeFormatterUtil;
import com.ropulva.CalendarManagement.util.GenericResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class EventService {

    private final CreatorService creatorService;
    private final EventRepository eventRepository;
    private final EventCacheService eventCacheService;
    private final GoogleService googleService;

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
                eventCacheService.clearAllCachedEvents();
                publishEvents();
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
            Timestamp startStamp = DateTimeFormatterUtil.combineDateAndTime(request.getStartDate(),request.getStartTime());
            Timestamp endStamp = DateTimeFormatterUtil.combineDateAndTime(request.getEndDate(),request.getEndTime());
            EventModel event = EventModel.builder()
                    .created(DateTimeFormatterUtil.getCurrentTimestamp())
                    .creator(eventCreator)
                    .description(request.getDescription())
                    .title(request.getTitle())
                    .colorId(request.getColorId())
                    .status("Pending")
                    .startDate(startStamp)
                    .endDate(endStamp)
                    .updates(null)
                    .build();
            eventRepository.save(event);
            return true;
        }catch (Exception e){
            logger.error(e.getMessage());
            return false;
        }

    }




    public ResponseEntity<GenericResponse> deleteEvent(String id) {
        GenericResponse response = new GenericResponse();
        try{
            long eventId = eventRepository.getEventIdById(Long.parseLong(id));
            if(eventId == 0){
                response.setEventDoesNotExist();
            }else {
                eventRepository.deleteById(eventId);
                response.setSuccessful();
                eventCacheService.clearAllCachedEvents();
            }

        }catch (Exception e){
            logger.error(e.getMessage());
            response.setServerErrorHappened();
        }
        return ResponseEntity.status(response.getHttpStatus()).body(response);
    }

    public ResponseEntity<AllEventsResponse> getAllEvents() {
        AllEventsResponse response = new AllEventsResponse();
        try{
             response.setEventsList(eventCacheService.getAllEvents());
             response.setSuccessful();
        }catch (Exception e){
            logger.error(e.getMessage());
            e.printStackTrace();
            response.setServerErrorHappened();
        }
        return ResponseEntity.status(response.getHttpStatus()).body(response);
    }


    // It will run automatically every 30 minutes
    @Scheduled(cron = "0 0/30 * * * ?")
    @Transactional
    public void publishEvents(){
        try {
            List<EventModel> pendingEventsList = eventRepository.getPendingEventsId();
            List<Long> publishedEventsId = new ArrayList<>();
            for(EventModel event: pendingEventsList){
                boolean success = googleService.addEventToCalendar(event);
                if(success){
                    publishedEventsId.add(event.getId());
                }
            }
            logger.info("Publishing Events with size if " + publishedEventsId.size() + " event");
            if(publishedEventsId.size() > 0){

                eventRepository.updateEventStatus(publishedEventsId);
            }


        }catch (Exception e){
            logger.error(e.getMessage());
        }
    }

    public ResponseEntity<GenericResponse> updateEvent(UpdateEventRequest request) {
        GenericResponse response = new GenericResponse();
        try {
            EventModel event =  eventRepository.getEventById(request.getEventId());
            if(request.getTitle() != null) event.setTitle(request.getTitle());
            if(request.getDescription() != null) event.setDescription(request.getDescription());
            if(request.getStartDate() != null) event.setStartDate(request.getStartDate());
            if(request.getEndDate() != null) event.setEndDate(request.getEndDate());
            event.setUpdates(DateTimeFormatterUtil.getCurrentTimestamp());
            eventRepository.save(event);
            response.setSuccessful();
        }catch (Exception e){
            logger.error(e.getMessage());
            response.setServerErrorHappened();
        }

        return ResponseEntity.status(response.getHttpStatus()).body(response);
    }
}
