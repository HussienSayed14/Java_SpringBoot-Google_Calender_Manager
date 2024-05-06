package com.ropulva.CalendarManagement.event;


import com.ropulva.CalendarManagement.event.requests.CreateEventRequest;
import com.ropulva.CalendarManagement.event.requests.UpdateEventRequest;
import com.ropulva.CalendarManagement.event.responses.AllEventsResponse;
import com.ropulva.CalendarManagement.util.GenericResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@RequestMapping("/ropulva/ap1/v1/event")
@RequiredArgsConstructor
@Tag(name = "Event" , description = "Apis that are responsible for Creating and Modifying Events")
public class EventController {

    private final EventService eventService;
    private static final String CLIENT_ID =  "253188536351-ilpfr4e8cdeobctbgvlhdfj5nf4s221p.apps.googleusercontent.com";


    @PostMapping("/create")
    @Operation(summary = "Create Event", description = "Endpoint to create a new event, then publish it to Google Calendar")
    ResponseEntity<GenericResponse> createEvent(@Valid @RequestBody CreateEventRequest request){
        return eventService.createEvent(request);
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Delete Event", description = "Endpoint to create a new event, then publish it to Google Calendar")
    ResponseEntity<GenericResponse> deleteEvent(@PathVariable String id){
        return eventService.deleteEvent(id);
    }

    @GetMapping("/getAll")
    @Operation(summary = "Get All Events", description = "Endpoint to get all the events, then add result to Redis Cache")
    ResponseEntity<AllEventsResponse> getAllEvents(){
        return eventService.getAllEvents();
    }

    @PutMapping("/update")
    @Operation(summary = "Update Event", description = "Endpoint to update event based on the event id")
    ResponseEntity<GenericResponse> updateEvent(@RequestBody UpdateEventRequest request){
        return eventService.updateEvent(request);
    }
}
