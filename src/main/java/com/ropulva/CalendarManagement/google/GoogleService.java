package com.ropulva.CalendarManagement.google;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.model.AclRule;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.EventDateTime;
import com.google.auth.http.HttpCredentialsAdapter;
import com.google.auth.oauth2.GoogleCredentials;
import com.ropulva.CalendarManagement.event.EventModel;
import com.ropulva.CalendarManagement.util.DateTimeFormatterUtil;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.google.api.services.calendar.model.AclRule.Scope;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.util.Collections;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class GoogleService {

    private static final Logger logger = LoggerFactory.getLogger(GoogleService.class);



    public boolean addEventToCalendar(EventModel eventModel) throws IOException {
       try{
           System.out.println("Calling add event");
           Calendar service = buildCalendarService();
           if(service == null){
               return false;
           }


           // Create a new calendar list entry
           Event event = new Event()
                   .setSummary(eventModel.getTitle())
                   .setDescription(eventModel.getDescription());


           DateTime startDateTime = new DateTime(new Date(eventModel.getStartDate().getTime()));
           EventDateTime start = new EventDateTime()
                   .setDateTime(startDateTime)
                   .setTimeZone("Africa/Cairo");
           event.setStart(start);

           DateTime endDateTime = new DateTime(new Date(eventModel.getEndDate().getTime()));
           EventDateTime end = new EventDateTime()
                   .setDateTime(endDateTime)
                   .setTimeZone("Africa/Cairo");
           event.setEnd(end);

           String calendarId = "primary";
           event = service.events().insert(calendarId, event).execute();
           shareCalendar("primary","hussiens399@gmail.com");
           if(event == null){
               return false;
           }
           return true;

       }catch (Exception e){
           logger.error(e.getMessage());
           e.printStackTrace();
           return false;
       }

    }


    private static Calendar buildCalendarService() throws Exception {
        try {

        InputStream serviceAccountStream = GoogleService.class.getResourceAsStream("/auth-file.json");
        GoogleCredentials credentials = GoogleCredentials.fromStream(serviceAccountStream)
                .createScoped(Collections.singletonList("https://www.googleapis.com/auth/calendar"));
        credentials.refreshIfExpired();

        return new Calendar.Builder(
                GoogleNetHttpTransport.newTrustedTransport(),
                JacksonFactory.getDefaultInstance(),
                new HttpCredentialsAdapter(credentials))
                .setApplicationName("Ropulva Events")
                .build();
        }catch (Exception e){
            logger.error(e.getMessage());
            return null;
        }
    }

    public void shareCalendar(String calendarId, String userEmail) {
        try {
            Calendar service = buildCalendarService();

            AclRule rule = new AclRule();
            Scope scope = new Scope();
            scope.setType("user");
            scope.setValue(userEmail);
            rule.setScope(scope);
            rule.setRole("owner");
            service.acl().insert(calendarId, rule).execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }




}
