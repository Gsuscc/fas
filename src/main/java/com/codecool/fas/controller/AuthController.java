package com.codecool.fas.controller;

import com.codecool.fas.model.UserCredentials;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/auth")
public class AuthController {

    @PostMapping("/register")
    private String registerUser(@RequestBody UserCredentials userCredentials){
        return "Success";
    }
}
