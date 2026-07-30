package com.oracle.app.eventticketsapp.controllers;

import com.oracle.app.eventticketsapp.dtos.dashboard.DashboardDTO;
import com.oracle.app.eventticketsapp.dtos.event.RecentEventDTO;
import com.oracle.app.eventticketsapp.dtos.ticket.RecentTicketDTO;
import com.oracle.app.eventticketsapp.services.DashboardService;
import com.oracle.app.eventticketsapp.services.DashboardServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author {ANAS DR}
 **/
@RestController

@RequiredArgsConstructor
public class DashboardRestController {

    private final DashboardService dashboardService;

    @GetMapping("admin/dashboard")
    public DashboardDTO getStatistics() {
        return dashboardService.getStatistics();
    }

    @GetMapping("/admin/dashboard/recent-events")
    public List<RecentEventDTO> getRecentEvents() {

        return dashboardService.getRecentEvents();

    }

    @GetMapping("/admin/dashboard/recent-tickets")
    public List<RecentTicketDTO> getRecentTickets() {

        return dashboardService.getRecentTickets();

    }
}
