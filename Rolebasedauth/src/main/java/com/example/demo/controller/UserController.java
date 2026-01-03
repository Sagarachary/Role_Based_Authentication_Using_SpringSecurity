package com.example.demo.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dtos.UserDTO;
import com.example.demo.service.UserService;

@RestController
@RequestMapping("/user/api")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping("/login")
    public Map<String, String> login(@RequestBody UserDTO userdto) {

        String username = userdto.getUsername();
        String password = userdto.getPassword();

        String role = service.getUserRole(username, password);
        String message;

        if (role.equals("user")) {
            message = service.userResponse(username);
        }
        else if (role.equals("admin")) {
            message = service.adminResponse(username);
        }
        else if (role.equals("invalid")) {
            message = "INVALID PASSWORD";
        }
        else {
            message = "USER WITH " + username + " DOES NOT EXIST";
        }

        Map<String, String> response = new HashMap<>();
        response.put("message", message);
        return response;
    }
}

