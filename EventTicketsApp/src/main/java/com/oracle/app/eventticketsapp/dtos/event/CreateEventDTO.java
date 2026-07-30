package com.oracle.app.eventticketsapp.dtos.event;

import com.oracle.app.eventticketsapp.entities.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * @author {ANAS DR}
 **/
@Data
public class CreateEventDTO {

    @NotBlank
    private String title;
    private String description;
    private LocalDate date ;
    private LocalTime time;

    @NotBlank
    private String location;

    @Positive
    private double price;

    @Positive
    private int capacity;



    private String categoryName;
    private String organizerId;
}
