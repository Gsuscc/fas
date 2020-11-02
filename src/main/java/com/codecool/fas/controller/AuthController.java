package com.codecool.fas.controller;

import com.codecool.fas.entity.UserInfo;
import com.codecool.fas.model.UserCredentials;
import com.codecool.fas.repository.UserRepository;
import com.codecool.fas.security.JwtTokenServices;
import com.codecool.fas.util.EmailValidator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


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
        if (!EmailValidator.validate(userCredentials.getEmail())) {
            return new ResponseEntity<>("Not a valid email address!", HttpStatus.I_AM_A_TEAPOT);
        }
        if (userRepository.existsUserInfoByEmail(userCredentials.getEmail())) {
            return new ResponseEntity<>("Email address has already registered!", HttpStatus.I_AM_A_TEAPOT);
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

    @PostMapping("/login")
    private ResponseEntity loginUser(@RequestBody UserCredentials userCredentials) {
        try {
            String username = userCredentials.getUsername();
            // authenticationManager.authenticate calls loadUserByUsername in CustomUserDetailsService
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, userCredentials.getPassword()));
            List<String> roles = authentication.getAuthorities()
                    .stream()
                    .map(GrantedAuthority::getAuthority)
                    .collect(Collectors.toList());

            String token = jwtTokenServices.createToken(username, roles);

            Map<Object, Object> model = new HashMap<>();
            model.put("username", username);
            model.put("roles", roles);
            model.put("token", token);
            return ResponseEntity.ok(model);
        } catch (AuthenticationException e) {
            return new ResponseEntity<>("Invalid username/password supplied", HttpStatus.I_AM_A_TEAPOT);
        }
    }
}
