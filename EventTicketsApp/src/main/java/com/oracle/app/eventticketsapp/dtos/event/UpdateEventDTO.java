package com.oracle.app.eventticketsapp.dtos.event;

import com.oracle.app.eventticketsapp.enums.EventStatus;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * @author {ANAS DR}
 **/
@Data
public class UpdateEventDTO {
    private String title;
    private String description;
    private LocalDate date ;
    private LocalTime time;
    private String location;
    private Double price;
    private Integer capacity;

    private EventStatus status;
    private String categoryName;
}
