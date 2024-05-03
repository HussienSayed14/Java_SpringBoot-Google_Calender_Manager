package com.ropulva.CalendarManagement.event;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<EventModel,Long> {
}
