package com.oracle.app.eventticketsapp.dtos.ticket;

import com.oracle.app.eventticketsapp.enums.TicketStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * @author {ANAS DR}
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecentTicketDTO {
    private String id;

    private String buyerName;

    private String eventTitle;

    private int quantity;

    private double totalPrice;

    private LocalDate purchaseDate;

    private TicketStatus status;
}
