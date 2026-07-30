package com.oracle.app.eventticketsapp.dtos.ticket;

import com.oracle.app.eventticketsapp.enums.TicketStatus;
import lombok.Data;

import java.time.LocalDate;

/**
 * @author {ANAS DR}
 **/
@Data
public class TicketDTO {
    private String id;
    private LocalDate purchaseDate;
    private int quantity;
    private double totalPrice;
    private TicketStatus status;

    private String userId;
    private String userName;

    private String eventId;
    private String eventTitle;
}
