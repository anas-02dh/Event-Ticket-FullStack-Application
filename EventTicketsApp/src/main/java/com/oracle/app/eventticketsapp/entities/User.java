package com.oracle.app.eventticketsapp.entities;

import com.oracle.app.eventticketsapp.enums.UserRole;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * @author {ANAS DR}
 **/
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User {
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String name;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false)
    private String password;
    @Enumerated(EnumType.STRING)
    private UserRole role;
    private String phone;
    private LocalDateTime createdAt;
    @OneToMany(mappedBy = "user")
    private List<Ticket> tickets;
    @OneToMany(mappedBy = "organizer")
    private List<Event> events;
}
