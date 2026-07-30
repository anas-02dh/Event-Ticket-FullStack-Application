package com.oracle.app.eventticketsapp.dtos.ticket;

import com.oracle.app.eventticketsapp.enums.TicketStatus;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

/**
 * @author {ANAS DR}
 **/
@Data
public class PurchaseHistoryDTO {
    private String userId;
    private String userName;
    private String eventId;
    private String eventTitle;
    private List<TicketDTO> ticketDTOS;
    private LocalDate purchaseDate;
    private double totalPrice;
    private TicketStatus status;
}
