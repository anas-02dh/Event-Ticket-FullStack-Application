package com.oracle.app.eventticketsapp.services;

import com.oracle.app.eventticketsapp.dtos.auth.LoginRequestDTO;
import com.oracle.app.eventticketsapp.dtos.auth.LoginResponseDTO;
import com.oracle.app.eventticketsapp.dtos.auth.RegisterUserDTO;
import com.oracle.app.eventticketsapp.dtos.category.CategoryDTO;
import com.oracle.app.eventticketsapp.dtos.event.CreateEventDTO;
import com.oracle.app.eventticketsapp.dtos.event.EventDTO;
import com.oracle.app.eventticketsapp.dtos.event.UpdateEventDTO;
import com.oracle.app.eventticketsapp.dtos.ticket.PurchaseHistoryDTO;
import com.oracle.app.eventticketsapp.dtos.ticket.PurchaseTicketDTO;
import com.oracle.app.eventticketsapp.dtos.ticket.TicketDTO;
import com.oracle.app.eventticketsapp.dtos.user.UserDTO;
import com.oracle.app.eventticketsapp.entities.Category;
import com.oracle.app.eventticketsapp.entities.Event;
import com.oracle.app.eventticketsapp.entities.Ticket;
import com.oracle.app.eventticketsapp.entities.User;
import com.oracle.app.eventticketsapp.enums.UserRole;

import java.util.List;

/**
 * @author {ANAS DR}
 **/
public interface EventTicketService {
    // User Service
    LoginResponseDTO login(LoginRequestDTO loginUserRequest);
    UserDTO register(RegisterUserDTO registerUserRequest);
    List<UserDTO> getUsersByRole(UserRole role);
    List<UserDTO> searchUsers(String keyword);
    void deleteUser(String id);
    // Event Service
    EventDTO saveEvent(CreateEventDTO event);
    EventDTO updateEvent(String id, UpdateEventDTO updateEventDTO);
    void deleteEvent(String id);
    List<EventDTO> getAllEvents();
    List<EventDTO> searchEvents(String keyword);
    EventDTO getEventById(String id);
    List<EventDTO> getEventsByCategorize(String categoryName);

    // Ticket Service
    List<TicketDTO> getAllTickets();
    TicketDTO purchaseTicket(PurchaseTicketDTO purchaseTicketDTO, String id);
    //If you're using authentication (JWT/Spring Security):
    //List<PurchaseHistoryDTO> getPurchaseHistory();
    //If authentication isn't implemented yet:
    PurchaseHistoryDTO getPurchaseHistory(String userId);


    //getPurchaseHistory

    // Category Sevice
    CategoryDTO saveCategory(CategoryDTO category);
    List<CategoryDTO> getCategories();
    void deleteCategory(String id);
    CategoryDTO updateCategory(String id, CategoryDTO categoryDTO);


}
