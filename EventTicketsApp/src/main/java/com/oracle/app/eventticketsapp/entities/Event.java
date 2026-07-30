package com.oracle.app.eventticketsapp.entities;

import com.oracle.app.eventticketsapp.enums.EventStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

/**
 * @author {ANAS DR}
 **/
@Entity
@Data
@NoArgsConstructor @AllArgsConstructor
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String title;
    private String description;
    private LocalDate date ;
    private LocalTime time;
    private String location;
    private double price;
    private int capacity;
    private int remainSeats;
    @Enumerated(EnumType.STRING)
    private EventStatus status;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;
    @OneToMany(mappedBy = "event")
    private List<Ticket> tickets;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "organizer_id")
    private User organizer;
}
