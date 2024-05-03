package com.ropulva.CalendarManagement.event;


import com.ropulva.CalendarManagement.event.requests.CreateEventRequest;
import com.ropulva.CalendarManagement.util.GenericResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ropulva/ap1/v1/event")
@RequiredArgsConstructor
@Tag(name = "Event" , description = "Apis that are responsible for Creating and Modifying Events")
public class EventController {

    private final EventService eventService;

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
    ResponseEntity<?> getAllEvents(){
        return eventService.getAllEvents();
    }
}
