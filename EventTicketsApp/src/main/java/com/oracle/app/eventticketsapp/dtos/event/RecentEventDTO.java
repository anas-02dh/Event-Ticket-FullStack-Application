package com.oracle.app.eventticketsapp.dtos.event;

import com.oracle.app.eventticketsapp.enums.EventStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * @author {ANAS DR}
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor

public class RecentEventDTO {
    private String id;

    private String title;

    private LocalDate date;

    private LocalTime time;

    private String location;

    private double price;

    private EventStatus status;

    private String categoryName;

}
