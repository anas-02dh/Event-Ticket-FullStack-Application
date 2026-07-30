package com.oracle.app.eventticketsapp.services;

import com.oracle.app.eventticketsapp.dtos.dashboard.DashboardDTO;
import com.oracle.app.eventticketsapp.dtos.event.RecentEventDTO;
import com.oracle.app.eventticketsapp.dtos.ticket.RecentTicketDTO;

import java.util.List;

/**
 * @author {ANAS DR}
 **/
public interface DashboardService {
    DashboardDTO getStatistics();
    List<RecentEventDTO> getRecentEvents();
    List<RecentTicketDTO> getRecentTickets();
}
