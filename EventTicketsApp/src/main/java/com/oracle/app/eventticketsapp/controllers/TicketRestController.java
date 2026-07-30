package com.oracle.app.eventticketsapp.controllers;

import com.oracle.app.eventticketsapp.dtos.ticket.PurchaseHistoryDTO;
import com.oracle.app.eventticketsapp.dtos.ticket.PurchaseTicketDTO;
import com.oracle.app.eventticketsapp.dtos.ticket.TicketDTO;
import com.oracle.app.eventticketsapp.entities.Ticket;
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
public class TicketRestController {
    private EventTicketService eventTicketService;

    @GetMapping("/tickets")
    public List<TicketDTO> getAllTickets() {
        return eventTicketService.getAllTickets();
    }

    @PostMapping("/tickets/purchase/{userId}")
    public TicketDTO purchaseTicket(@RequestBody PurchaseTicketDTO dto, @PathVariable String userId){
        return eventTicketService.purchaseTicket(dto,userId);
    }

    @GetMapping("/tickets/purchase/history/{userId}")
    public PurchaseHistoryDTO getPurchaseHistory(@PathVariable String userId) {
        return eventTicketService.getPurchaseHistory(userId);
    }

}
