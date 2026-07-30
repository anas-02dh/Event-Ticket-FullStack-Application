package com.oracle.app.eventticketsapp.mappers;

import com.oracle.app.eventticketsapp.dtos.category.CategoryDTO;
import com.oracle.app.eventticketsapp.dtos.event.EventDTO;
import com.oracle.app.eventticketsapp.dtos.ticket.TicketDTO;
import com.oracle.app.eventticketsapp.dtos.user.UserDTO;
import com.oracle.app.eventticketsapp.entities.Category;
import com.oracle.app.eventticketsapp.entities.Event;
import com.oracle.app.eventticketsapp.entities.Ticket;
import com.oracle.app.eventticketsapp.entities.User;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

/**
 * @author {ANAS DR}
 **/

@Component
public class EventTicketsMapper {

    //User

    public UserDTO fromUser(User user) {
        if (user == null) {
            return null;
        }
        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(user,userDTO);
        return userDTO;
    }

    public User fromUserDTO(UserDTO userDTO) {
        User user = new User();
        BeanUtils.copyProperties(userDTO,user);
        return user;
    }

    //Event

    public EventDTO fromEvent(Event event) {
        if (event == null) {
            return null;
        }
        EventDTO eventDTO = new EventDTO();
        BeanUtils.copyProperties(event, eventDTO);
        if (event.getCategory() != null) {
            eventDTO.setCategoryId(event.getCategory().getId());
            eventDTO.setCategoryName(event.getCategory().getName());
        }

        if (event.getOrganizer() != null) {
            eventDTO.setOrganizerId(event.getOrganizer().getId());
            eventDTO.setOrganizerName(event.getOrganizer().getName());
        }
        return eventDTO;
    }

    public Event fromEventDTO(EventDTO eventDTO) {
        Event event = new Event();
        BeanUtils.copyProperties(eventDTO,event);
        return event;
    }

    // Ticket

    public TicketDTO fromTicket(Ticket ticket) {
        if (ticket == null) {
            return null;
        }
        TicketDTO ticketDTO =new TicketDTO();
        BeanUtils.copyProperties(ticket,ticketDTO);

        if (ticket.getUser() != null) {
            ticketDTO.setUserId(ticket.getUser().getId());
            ticketDTO.setUserName(ticket.getUser().getName());
        }

        if (ticket.getEvent() != null) {
            ticketDTO.setEventId(ticket.getEvent().getId());
            ticketDTO.setEventTitle(ticket.getEvent().getTitle());
        }
        return ticketDTO;
    }
    public Ticket fromTicketDTO(TicketDTO ticketDTO) {
        Ticket ticket =new Ticket();
        BeanUtils.copyProperties(ticketDTO,ticket);
        return ticket;
    }

    // Category

    public CategoryDTO fromCategory(Category category){

        if(category == null){
            return null;
        }

        CategoryDTO categoryDTO = new CategoryDTO();

        BeanUtils.copyProperties(category,categoryDTO);

        return categoryDTO;
    }

    public Category fromCategoryDTO(CategoryDTO categoryDTO){

        if(categoryDTO == null){
            return null;
        }

        Category category = new Category();

        BeanUtils.copyProperties(categoryDTO,category);

        return category;
    }

}
