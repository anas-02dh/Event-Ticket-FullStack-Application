package com.oracle.app.eventticketsapp.dtos.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * @author {ANAS DR}
 **/
@Data
public class LoginRequestDTO {

    @Email
    @NotBlank
    private String email;

    @NotBlank
    private String password;
}
