package com.codecool.fas.controller;

import com.codecool.fas.entity.UserInfo;
import com.codecool.fas.model.UserCredentials;
import com.codecool.fas.repository.UserRepository;
import com.codecool.fas.security.JwtTokenServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final JwtTokenServices jwtTokenServices;
    private final PasswordEncoder passwordEncoder;

    public AuthController(AuthenticationManager authenticationManager, UserRepository userRepository, JwtTokenServices jwtTokenServices) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.jwtTokenServices = jwtTokenServices;
        this.passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

    }

    @PostMapping("/register")
    private ResponseEntity registerUser(@RequestBody UserCredentials userCredentials){
        if (userRepository.existsUserInfoByEmail(userCredentials.getEmail())) {
            return new ResponseEntity<>("Email address has already registered", HttpStatus.NOT_ACCEPTABLE);
        }
        if (userRepository.existsUserInfoByUsername(userCredentials.getUsername())){
            return ResponseEntity.unprocessableEntity().body("This username has already been taken!");
        }

        userRepository.save(UserInfo.builder()
                    .username(userCredentials.getUsername())
                    .password(passwordEncoder.encode(userCredentials.getPassword()))
                    .email(userCredentials.getEmail())
                    .build()
        );

        return ResponseEntity.ok("Success");
    }
}
