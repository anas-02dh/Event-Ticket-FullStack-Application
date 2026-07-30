package com.oracle.app.eventticketsapp.repositories;

import com.oracle.app.eventticketsapp.entities.Event;
import com.oracle.app.eventticketsapp.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

/**
 * @author {ANAS DR}
 **/
public interface EventRepository extends JpaRepository<Event,String> {
    List<Event> findByTitleContainsIgnoreCase(String keyword);

    List<Event> findEventByOrganizer(User organizer);

    List<Event> findEventByCategory_Name(String categoryName);

    List<Event> findTop5ByOrderByDateDescTimeDesc();

    void deleteAllByOrganizer(User organizer);
}
