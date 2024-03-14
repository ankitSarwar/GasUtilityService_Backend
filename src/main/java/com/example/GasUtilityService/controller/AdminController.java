package com.example.GasUtilityService.controller;

import com.example.GasUtilityService.model.RequestStatus;
import com.example.GasUtilityService.model.ServiceRequest;
import com.example.GasUtilityService.model.User;
import com.example.GasUtilityService.model.UserRoles;
import com.example.GasUtilityService.service.JwtService;
import com.example.GasUtilityService.service.ServiceRequestService;
import com.example.GasUtilityService.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private UserService service;

    @Autowired
    private ServiceRequestService serviceRequestService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserService userService;


    @GetMapping
    public ResponseEntity<List<ServiceRequest>> getAllServiceRequests(@RequestParam String token) {
        try {
            String username = jwtService.extractUsername(token);
            Optional<User> currentUserOptional = userService.findByUsername(username);

            if (currentUserOptional.isPresent()) {
                User currentUser = currentUserOptional.get();

                if (currentUser.getRoles().contains(UserRoles.ADMIN)) {
                    List<ServiceRequest> serviceRequests = service.getAllServiceRequests();
                    return ResponseEntity.ok(serviceRequests);
                } else {
                    return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
                }
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }


    @GetMapping("/{id}")
    public ResponseEntity<User> getCustomer(@PathVariable Long id, @RequestParam String token) {
        try {
            String username = jwtService.extractUsername(token);
            Optional<User> currentUserOptional = userService.findByUsername(username);

            if (currentUserOptional.isPresent()) {
                User currentUser = currentUserOptional.get();

                if ((currentUser.getRoles()).equals(UserRoles.ADMIN)) {
                    User user = service.getCustomer(id);
                    if (user != null) {
                        return ResponseEntity.ok(user);
                    } else {
                        return ResponseEntity.notFound().build();
                    }
                } else {
                    return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
                }
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    @PutMapping("/{id}/update-status")
    public ResponseEntity<ServiceRequest> updateServiceRequestStatus(@PathVariable Long id, @RequestBody RequestStatus newStatus, @RequestParam String token) {
        try {
            String username = jwtService.extractUsername(token);
            Optional<User> currentUserOptional = userService.findByUsername(username);

            if (currentUserOptional.isPresent()) {
                User currentUser = currentUserOptional.get();

                if (currentUser.getRoles().contains(UserRoles.ADMIN)) {
                    ServiceRequest updatedRequest = serviceRequestService.updateServiceRequestStatus(id, newStatus);
                    if (updatedRequest != null) {
                        return ResponseEntity.ok(updatedRequest);
                    } else {
                        return ResponseEntity.notFound().build();
                    }
                } else {
                    return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
                }
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


}
