package com.oracle.app.eventticketsapp.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author {ANAS DR}
 **/
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Category {
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String name;
    private String description;
    @OneToMany(mappedBy = "category")
    private List<Event> events;
}
