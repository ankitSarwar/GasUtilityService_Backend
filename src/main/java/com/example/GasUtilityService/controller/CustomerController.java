package com.example.GasUtilityService.controller;

import com.example.GasUtilityService.dto.AuthRequest;
import com.example.GasUtilityService.exception.RegistrationException;
import com.example.GasUtilityService.model.User;
import com.example.GasUtilityService.service.UserService;
import com.example.GasUtilityService.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.AuthenticationException;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    @Autowired
    private UserService service;
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody User user) {
        try {
            User registeredUser = service.createCustomer(user);

            // Send the verification link in the response
            return ResponseEntity.ok("User registered successfully.");
        } catch (RegistrationException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Registration failed.");
        }
    }
    @PostMapping("/logIn")
    public String logInUser(@RequestBody AuthRequest authRequest) {
        try {
            // Authenticate user
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
            );

            if (authentication.isAuthenticated()) {
                return jwtService.generateToken(authRequest.getUsername());
            } else {

                throw new BadCredentialsException("Invalid username or password");
            }
        } catch (BadCredentialsException e) {
            // Handle authentication failure
            throw new BadCredentialsException("Invalid username or password", e);
        }
    }



}