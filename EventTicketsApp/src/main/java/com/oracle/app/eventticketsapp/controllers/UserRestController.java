package com.oracle.app.eventticketsapp.controllers;

import com.oracle.app.eventticketsapp.dtos.auth.LoginRequestDTO;
import com.oracle.app.eventticketsapp.dtos.auth.LoginResponseDTO;
import com.oracle.app.eventticketsapp.dtos.auth.RegisterUserDTO;
import com.oracle.app.eventticketsapp.dtos.event.EventDTO;
import com.oracle.app.eventticketsapp.dtos.user.UserDTO;
import com.oracle.app.eventticketsapp.enums.UserRole;
import com.oracle.app.eventticketsapp.services.EventTicketService;
import com.oracle.app.eventticketsapp.services.EventTicketServiceImpl;
import lombok.AllArgsConstructor;
import org.hibernate.annotations.Audited;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author {ANAS DR}
 **/
@RestController
@AllArgsConstructor
@CrossOrigin("*")
public class UserRestController {

    private EventTicketService eventTicketService;

    @PostMapping("/login")
    public LoginResponseDTO login(@RequestBody LoginRequestDTO loginRequestDTO) {
        return eventTicketService.login(loginRequestDTO);
    }

    @PostMapping("/register")
    public UserDTO register(@RequestBody RegisterUserDTO registerUserDTO) {
        return eventTicketService.register(registerUserDTO);
    }

    @GetMapping("/users/{role}")
    public List<UserDTO> getUsersByRole(@PathVariable UserRole role) {
        return eventTicketService.getUsersByRole(role);
    }

    @GetMapping("/users/search")
    public List<UserDTO> searchUsers(@RequestParam(name = "keyword",defaultValue = "")String keyword){
        return eventTicketService.searchUsers(keyword);
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable String id) {

        eventTicketService.deleteUser(id);
    }

}
