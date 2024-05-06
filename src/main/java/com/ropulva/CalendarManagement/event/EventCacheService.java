package com.ropulva.CalendarManagement.event;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EventCacheService {

    private final EventRepository eventRepository;
    private static final Logger logger = LoggerFactory.getLogger(EventCacheService.class);

    @Cacheable(value = "events")
    public List<EventModel> getAllEvents(){
        logger.info("Fetching from the database");
        List<EventModel> eventsList = eventRepository.getAllEvents();
        return eventsList;
    }


    @CacheEvict(value = "events", allEntries = true)
    public void clearAllCachedEvents(){

    }


}
