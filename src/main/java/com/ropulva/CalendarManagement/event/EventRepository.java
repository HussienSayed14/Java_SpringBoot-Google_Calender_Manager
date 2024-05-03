package com.ropulva.CalendarManagement.event;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface EventRepository extends JpaRepository<EventModel,Long> {


    // Fetching id Only to achieve Index Only Scan
    @Query(value = "SELECT id FROM event WHERE id =:eventId",nativeQuery = true)
    long getEventIdById(long eventId);


}
