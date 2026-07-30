package com.oracle.app.eventticketsapp.controllers;

import com.oracle.app.eventticketsapp.dtos.event.CreateEventDTO;
import com.oracle.app.eventticketsapp.dtos.event.EventDTO;
import com.oracle.app.eventticketsapp.dtos.event.UpdateEventDTO;
import com.oracle.app.eventticketsapp.services.EventTicketService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author {ANAS DR}
 **/
@RestController
@AllArgsConstructor
@CrossOrigin("*")
public class EventRestController {
    private EventTicketService eventTicketService;

    @GetMapping("/events")
    public List<EventDTO> getAllEvents(){
        return eventTicketService.getAllEvents();
    }

    @GetMapping("/events/{id}")
    public EventDTO getEventById(@PathVariable String id) {
        return eventTicketService.getEventById(id);
    }

    @GetMapping("/events/search")
    public List<EventDTO> searchEvents(@RequestParam(name = "keyword",defaultValue = "")String keyword){
        return eventTicketService.searchEvents(keyword);
    }

    @GetMapping("/events/category")
    public List<EventDTO> getEventsByCategory(@RequestParam(name = "name",defaultValue = "")String categoryName) {
        return eventTicketService.getEventsByCategorize(categoryName);
    }

    @PostMapping("/events")
    public EventDTO saveEvent(@RequestBody CreateEventDTO event) {
        return eventTicketService.saveEvent(event);
    }

    @PutMapping("/events/{id}")
    public EventDTO updateEvent(@PathVariable String id, @RequestBody UpdateEventDTO event) {
        return eventTicketService.updateEvent(id,event);
    }

    @DeleteMapping("/events/{id}")
    public void deleteEvent(@PathVariable String id) {
        eventTicketService.deleteEvent(id);
    }


}
