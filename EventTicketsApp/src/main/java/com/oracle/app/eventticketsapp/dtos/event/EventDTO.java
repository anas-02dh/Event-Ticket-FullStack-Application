package com.oracle.app.eventticketsapp.dtos.event;

import com.oracle.app.eventticketsapp.enums.EventStatus;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * @author {ANAS DR}
 **/
@Data
public class EventDTO {
    private String id;
    private String title;
    private String description;
    private LocalDate date ;
    private LocalTime time;
    private String location;
    private double price;
    private int capacity;
    private int remainSeats;
    private EventStatus status;
    private String categoryId;
    private String categoryName;
    private String organizerId;
    private String organizerName;
}
