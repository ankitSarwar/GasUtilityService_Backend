package com.example.GasUtilityService.controller;

import com.example.GasUtilityService.model.ServiceRequest;
import com.example.GasUtilityService.model.User;
import com.example.GasUtilityService.model.UserRoles;
import com.example.GasUtilityService.service.JwtService;
import com.example.GasUtilityService.service.ServiceRequestService;
import com.example.GasUtilityService.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/service-requests")
public class ServiceRequestController {
    @Autowired
    private ServiceRequestService service;
    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<ServiceRequest> submitServiceRequest(@RequestBody ServiceRequest request, @RequestParam String token) {
        String username = jwtService.extractUsername(token);
        Optional<User> currentUserOptional = userService.findByUsername(username);

        if (currentUserOptional.isPresent()) {
            User currentUser = currentUserOptional.get();

            if (currentUser.getRoles().contains(UserRoles.USER)) { // Check if the set contains USER role
                ServiceRequest newRequest = service.submitServiceRequest(request, currentUser);
                return ResponseEntity.ok(newRequest);
            } else {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
            }
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServiceRequest> getServiceRequest(@PathVariable Long id, @RequestParam String token) {
        String username = jwtService.extractUsername(token);
        Optional<User> currentUserOptional = userService.findByUsername(username);

        if (currentUserOptional.isPresent()) {
            User currentUser = currentUserOptional.get();

            ServiceRequest request = service.getServiceRequest(id);

            if ((currentUser.getRoles()).equals(UserRoles.USER.toString()) || (currentUser.getRoles()).equals(UserRoles.ADMIN.toString())) {
                return ResponseEntity.ok(request);
            } else {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
            }
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    }

}