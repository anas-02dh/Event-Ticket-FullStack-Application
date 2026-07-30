package com.oracle.app.eventticketsapp.dtos.auth;

import com.oracle.app.eventticketsapp.enums.UserRole;
import lombok.Data;

/**
 * @author {ANAS DR}
 **/
@Data
public class LoginResponseDTO {
    private String accessToken;
    private String tokenType = "Bearer";
    private String userId;
    private String name;
    private String email;
    private UserRole role;
}
