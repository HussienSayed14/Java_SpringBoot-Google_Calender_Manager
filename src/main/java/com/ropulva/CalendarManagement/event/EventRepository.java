package com.ropulva.CalendarManagement.event;

import com.ropulva.CalendarManagement.event.dto.EventDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EventRepository extends JpaRepository<EventModel,Long> {


    // Fetching id Only to achieve Index Only Scan
    @Query(value = "SELECT id FROM event WHERE id =:eventId",nativeQuery = true)
    long getEventIdById(long eventId);

    @Query(value = "SELECT id, description, title,start_date,end_date,start_time,end_time FROM event")
    List<EventDto> getAllEvents();
}
