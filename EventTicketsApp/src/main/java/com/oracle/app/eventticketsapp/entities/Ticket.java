package com.oracle.app.eventticketsapp.entities;


import com.oracle.app.eventticketsapp.enums.TicketStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author {ANAS DR}
 **/
@Entity
@Data
@NoArgsConstructor @AllArgsConstructor
public class Ticket {
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private LocalDate purchaseDate;
    private int quantity;
    private double totalPrice;
    @Enumerated(EnumType.STRING)
    private TicketStatus status;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id")
    private Event event;
}
