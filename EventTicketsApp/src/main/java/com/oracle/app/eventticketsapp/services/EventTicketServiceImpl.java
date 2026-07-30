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
import com.oracle.app.eventticketsapp.enums.EventStatus;
import com.oracle.app.eventticketsapp.enums.TicketStatus;
import com.oracle.app.eventticketsapp.enums.UserRole;
import com.oracle.app.eventticketsapp.exceptions.EventNotFoundException;
import com.oracle.app.eventticketsapp.mappers.EventTicketsMapper;
import com.oracle.app.eventticketsapp.repositories.CategoryRepository;
import com.oracle.app.eventticketsapp.repositories.EventRepository;
import com.oracle.app.eventticketsapp.repositories.TicketRepository;
import com.oracle.app.eventticketsapp.repositories.UserRepository;
import com.oracle.app.eventticketsapp.security.JwtService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author {ANAS DR}
 **/
@Service
@Transactional
@AllArgsConstructor
public class EventTicketServiceImpl implements EventTicketService{

    private CategoryRepository categoryRepository;
    private EventRepository eventRepository;
    private TicketRepository ticketRepository;
    private UserRepository userRepository;
    private EventTicketsMapper mapper;
    private final JwtService jwtService;
    //private static final SecureRandom secureRandom = new SecureRandom();
    //private static final Base64.Encoder base64Encoder = Base64.getUrlEncoder().withoutPadding();

    //public static String generateToken(int byteLength) {
    //    byte[] randomBytes = new byte[byteLength];
    //    secureRandom.nextBytes(randomBytes);
    //    return base64Encoder.encodeToString(randomBytes);
    //}
    @Override
    public LoginResponseDTO login(LoginRequestDTO loginUserRequest) {

        User logedUser = userRepository.findByEmail(loginUserRequest.getEmail());
        if (logedUser == null) {
            throw new RuntimeException("Invalid email or password");
        }

        if (!logedUser.getPassword().equals(loginUserRequest.getPassword())) {
            throw new RuntimeException("Invalid email or password");
        }
        LoginResponseDTO loginResponseDTO = new LoginResponseDTO();
        //loginResponseDTO.setAccessToken(generateToken(50));
        loginResponseDTO.setAccessToken(jwtService.generateToken(logedUser));
        loginResponseDTO.setTokenType("Bearer");
        loginResponseDTO.setUserId(logedUser.getId());
        loginResponseDTO.setName(logedUser.getName());
        loginResponseDTO.setEmail(logedUser.getEmail());
        loginResponseDTO.setRole(logedUser.getRole());
        return loginResponseDTO;
    }

    @Override
    public UserDTO register(RegisterUserDTO registerUserRequest) {


        User user = userRepository.findByEmail(registerUserRequest.getEmail());
        if (user == null) {
            user = new User();
            user.setName(registerUserRequest.getName());
            user.setEmail(registerUserRequest.getEmail());
            user.setPassword(registerUserRequest.getPassword());
            user.setPhone(registerUserRequest.getPhone());
            user.setRole(UserRole.CUSTOMER);
            user.setCreatedAt(LocalDateTime.now());
            User userSaved = userRepository.save(user);
            return mapper.fromUser(userSaved);
        }
       else {
            throw new RuntimeException("This email already exist");
        }

    }

    @Override
    public List<UserDTO> getUsersByRole(UserRole role) {
        List<User> users= userRepository.findAllByRole(role);
        List<UserDTO> userDTOS = users.stream().map(user -> mapper.fromUser(user)).collect(Collectors.toList());

        return userDTOS;
    }

    @Override
    public List<UserDTO> searchUsers(String keyword) {
        List<User> users = userRepository.findByNameContainsIgnoreCase(keyword);
        List<UserDTO> userDTOS = users.stream().map(user -> mapper.fromUser(user)).collect(Collectors.toList());
        return userDTOS;
    }

    @Override
    public void deleteUser(String id) {

        User user = userRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("User not found")
                );

        ticketRepository.deleteAllByUser(user);

        userRepository.delete(user);
    }


    @Override
    public EventDTO saveEvent(CreateEventDTO eventDTO) {

        Category category =
                categoryRepository.findByName(eventDTO.getCategoryName())
                        .orElseThrow(() ->
                                new RuntimeException("Category not found")
                        );

        User organizer =
                userRepository.findById(eventDTO.getOrganizerId())
                        .orElseThrow(() ->
                                new RuntimeException("Organizer not found")
                        );

        Event event = new Event();

        event.setTitle(eventDTO.getTitle());
        event.setDescription(eventDTO.getDescription());
        event.setDate(eventDTO.getDate());
        event.setTime(eventDTO.getTime());
        event.setLocation(eventDTO.getLocation());
        event.setPrice(eventDTO.getPrice());
        event.setCapacity(eventDTO.getCapacity());

        event.setCategory(category);
        event.setOrganizer(organizer);

        event.setRemainSeats(event.getCapacity());
        event.setStatus(EventStatus.PreSale);

        Event eventSaved = eventRepository.save(event);

        return mapper.fromEvent(eventSaved);
    }

    @Override
    public EventDTO updateEvent(String id, UpdateEventDTO updateEventDTO) {
        Event event = eventRepository.findById(id).orElse(null);
        if (event == null ){
            throw new EventNotFoundException("Event not found");
        }
        Category category =
                categoryRepository.findByName(
                                updateEventDTO.getCategoryName()
                        )
                        .orElseThrow(() ->
                                new RuntimeException("Category not found")
                        );

        event.setTitle(updateEventDTO.getTitle());
        event.setDescription(updateEventDTO.getDescription());
        event.setDate(updateEventDTO.getDate());
        event.setTime(updateEventDTO.getTime());
        event.setLocation(updateEventDTO.getLocation());
        event.setPrice(updateEventDTO.getPrice());
        event.setCapacity(updateEventDTO.getCapacity());
        event.setCategory(category);
        Event eventUpdated = eventRepository.save(event);
        return mapper.fromEvent(eventUpdated);
    }

    @Override
    public void deleteEvent(String id) {
        Event event =
                eventRepository.findById(id).orElse(null);

        if (event == null ){
            throw new EventNotFoundException("Event not found");
        }

        ticketRepository.deleteAllByEvent(event);
        eventRepository.delete(event);
    }

    @Override
    public List<EventDTO> getAllEvents() {
        List<Event> events = eventRepository.findAll();
        List<EventDTO> eventDTOS = events.stream().map(event -> mapper.fromEvent(event)).collect(Collectors.toList());
        return eventDTOS;
    }

    @Override
    public List<EventDTO> searchEvents(String keyword) {
        List<Event> events = eventRepository.findByTitleContainsIgnoreCase(keyword);
        List<EventDTO> eventDTOS = events.stream().map(event -> mapper.fromEvent(event)).collect(Collectors.toList());
        return eventDTOS;
    }

    @Override
    public EventDTO getEventById(String id) {
        Event event = new Event();
        event = eventRepository.findById(id).orElse(null);
        if (event == null ){
            throw new EventNotFoundException("Event not found");
        }

        return mapper.fromEvent(event);
    }

    @Override
    public List<EventDTO> getEventsByCategorize(String categoryName) {
        List<Event> events = eventRepository.findEventByCategory_Name(categoryName);
        List<EventDTO> eventDTOS = events.stream().map(event -> mapper.fromEvent(event)).collect(Collectors.toList());
        return eventDTOS;
    }

    @Override
    public List<TicketDTO> getAllTickets() {
        List<Ticket> tickets = ticketRepository.findAll();
        List<TicketDTO> ticketsDTOS = tickets.stream().map(ticket -> mapper.fromTicket(ticket)).collect(Collectors.toList());
        return ticketsDTOS;
    }



    @Override
    public TicketDTO purchaseTicket(PurchaseTicketDTO dto, String id) {
        Event event = eventRepository.findById(dto.getEventId()).orElse(null);
        if (event == null ){
            throw new EventNotFoundException("Event not found");
        }
        User user =
                userRepository.findById(id)
                        .orElseThrow();
        if(dto.getQuantity() <=0) {
            throw  new RuntimeException("impossible to purchase ticket");
        }
        if(event.getRemainSeats()<dto.getQuantity()){
            throw  new RuntimeException("impossible to purchase ticket");
        }

        Ticket ticket = new Ticket();
        ticket.setUser(user);
        ticket.setEvent(event);
        ticket.setQuantity(dto.getQuantity());
        ticket.setPurchaseDate(LocalDate.now());
        ticket.setStatus(TicketStatus.CONFIRMED);
        ticket.setTotalPrice(event.getPrice()*dto.getQuantity());
        event.setRemainSeats(
                event.getRemainSeats()-dto.getQuantity()
        );
        eventRepository.save(event);
        ticketRepository.save(ticket);
        return mapper.fromTicket(ticket);

    }

    @Override
    public PurchaseHistoryDTO getPurchaseHistory(String userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        List<Ticket> tickets = ticketRepository.findByUser(user);

        PurchaseHistoryDTO purchaseHistory = new PurchaseHistoryDTO();

        purchaseHistory.setUserId(user.getId());
        purchaseHistory.setUserName(user.getName());

        List<TicketDTO> ticketDTOs = tickets.stream()
                .map(mapper::fromTicket)
                .toList();

        purchaseHistory.setTicketDTOS(ticketDTOs);

        if (!tickets.isEmpty()) {

            Ticket lastTicket = tickets.get(tickets.size() - 1);

            purchaseHistory.setEventId(lastTicket.getEvent().getId());
            purchaseHistory.setEventTitle(lastTicket.getEvent().getTitle());
            purchaseHistory.setPurchaseDate(lastTicket.getPurchaseDate());
            purchaseHistory.setTotalPrice(lastTicket.getTotalPrice());
            purchaseHistory.setStatus(lastTicket.getStatus());
        }

        return purchaseHistory;
    }



    @Override
    public List<CategoryDTO> getCategories() {
        List<Category> categories = categoryRepository.findAll();
        List<CategoryDTO> categoriesDTOS = categories.stream().map(category -> mapper.fromCategory(category)).collect(Collectors.toList());
        return categoriesDTOS;
    }

    @Override
    public CategoryDTO saveCategory(CategoryDTO categoryDTO) {
        Category category = mapper.fromCategoryDTO(categoryDTO);
        Category categorySaved = categoryRepository.save(category);
        return mapper.fromCategory(categorySaved);
    }

    @Override
    public void deleteCategory(String id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public CategoryDTO updateCategory(String id, CategoryDTO categoryDTO) {
        Category category = categoryRepository.findById(id).orElse(null);
        if(category ==  null) {
            throw  new RuntimeException("category not found");
        }
        category.setName(categoryDTO.getName());
        category.setDescription(categoryDTO.getDescription());
        Category categorySaved = categoryRepository.save(category);
        return mapper.fromCategory(categorySaved);
    }
}
