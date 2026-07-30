package com.oracle.app.eventticketsapp.dtos.ticket;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * @author {ANAS DR}
 **/
@Data
public class PurchaseTicketDTO {

    @NotBlank
    private String eventId;

    @Min(1)
    private int quantity;

}
