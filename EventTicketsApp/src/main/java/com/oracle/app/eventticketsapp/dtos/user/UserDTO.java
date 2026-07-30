package com.oracle.app.eventticketsapp.dtos.user;

import com.oracle.app.eventticketsapp.enums.UserRole;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author {ANAS DR}
 **/
@Data
public class UserDTO {
    private String id;
    private String name;
    private String email;

    private UserRole role;
    private String phone;
    private LocalDateTime createdAt;
}
