package com.oracle.app.eventticketsapp.dtos.dashboard;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author {ANAS DR}
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DashboardDTO {
    private long users;
    private long events;
    private long categories;
    private long tickets;
}
