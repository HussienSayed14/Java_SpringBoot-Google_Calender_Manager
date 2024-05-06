package com.ropulva.CalendarManagement.event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;


import java.util.List;

public interface EventRepository extends JpaRepository<EventModel,Long> {


    // Fetching id Only to achieve Index Only Scan
    @Query(value = "SELECT id FROM event WHERE id =:eventId",nativeQuery = true)
    long getEventIdById(long eventId);

    @Query(value = "SELECT * FROM event",nativeQuery = true)
    List<EventModel> getAllEvents();


    // Index Scan
    @Query(value = "SELECT * FROM event WHERE status = 'Pending'")
    List<EventModel> getPendingEventsId();

    @Modifying
    @Query(value = "UPDATE event SET status = 'PUBLISHED' WHERE id IN :ids", nativeQuery = true)
    void updateEventStatus(List<Long> ids);
}

