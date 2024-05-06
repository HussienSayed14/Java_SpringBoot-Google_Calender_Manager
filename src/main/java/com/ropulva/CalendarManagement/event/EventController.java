package com.ropulva.CalendarManagement.event;


import com.ropulva.CalendarManagement.event.requests.CreateEventRequest;
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
    ResponseEntity<AllEventsResponse> getAllEvents(){
        return eventService.getAllEvents();
    }

    private RedirectView googleLogin() {
        System.out.println("Calling Google Login");
        String redirectUri = "http://localhost:8080/google/auth";
        String clientId = CLIENT_ID;
        String scope = "https://www.googleapis.com/auth/calendar";
        String authUrl = "https://accounts.google.com/o/oauth2/v2/auth?response_type=code"
                + "&client_id=" + clientId
                + "&redirect_uri=" + redirectUri
                + "&scope=" + scope
                + "&access_type=offline"
                + "&prompt=consent";

        return new RedirectView(authUrl);
    }

}
