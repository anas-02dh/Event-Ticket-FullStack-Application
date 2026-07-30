package com.oracle.app.eventticketsapp.services;

import com.oracle.app.eventticketsapp.dtos.dashboard.DashboardDTO;
import com.oracle.app.eventticketsapp.dtos.event.RecentEventDTO;
import com.oracle.app.eventticketsapp.dtos.ticket.RecentTicketDTO;
import com.oracle.app.eventticketsapp.enums.UserRole;
import com.oracle.app.eventticketsapp.repositories.CategoryRepository;
import com.oracle.app.eventticketsapp.repositories.EventRepository;
import com.oracle.app.eventticketsapp.repositories.TicketRepository;
import com.oracle.app.eventticketsapp.repositories.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author {ANAS DR}
 **/
@Service
@RequiredArgsConstructor
@Transactional()
public class DashboardServiceImpl implements DashboardService{
    private final UserRepository userRepository;
    private final EventRepository eventRepository;
    private final CategoryRepository categoryRepository;
    private final TicketRepository ticketRepository;

    @Override
    public DashboardDTO getStatistics() {
        long users = userRepository.countByRole(UserRole.CUSTOMER);
        long events = eventRepository.count();
        long categories = categoryRepository.count();
        long tickets = ticketRepository.count();

        return new DashboardDTO(users, events, categories, tickets);

    }

    @Override
    @Transactional()
    public List<RecentEventDTO> getRecentEvents() {

        return eventRepository
                .findTop5ByOrderByDateDescTimeDesc()
                .stream()
                .map(event -> new RecentEventDTO(

                        event.getId(),

                        event.getTitle(),

                        event.getDate(),

                        event.getTime(),

                        event.getLocation(),

                        event.getPrice(),

                        event.getStatus(),

                        event.getCategory() != null
                                ? event.getCategory().getName()
                                : null

                ))
                .toList();
    }

    @Override
    @Transactional()
    public List<RecentTicketDTO> getRecentTickets() {

        return ticketRepository
                .findTop5ByOrderByPurchaseDateDesc()
                .stream()
                .map(ticket -> new RecentTicketDTO(

                        ticket.getId(),

                        ticket.getUser() != null
                                ? ticket.getUser().getName()
                                : null,

                        ticket.getEvent() != null
                                ? ticket.getEvent().getTitle()
                                : null,

                        ticket.getQuantity(),

                        ticket.getTotalPrice(),

                        ticket.getPurchaseDate(),

                        ticket.getStatus()

                ))
                .toList();

    }
}
