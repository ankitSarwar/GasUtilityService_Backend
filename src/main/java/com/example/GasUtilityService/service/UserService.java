package com.example.GasUtilityService.service;

import com.example.GasUtilityService.exception.RegistrationException;
import com.example.GasUtilityService.model.ServiceRequest;
import com.example.GasUtilityService.model.User;
import com.example.GasUtilityService.repo.ServiceRequestRepository;
import com.example.GasUtilityService.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    ServiceRequestRepository serviceRequestRepository;


    public User getCustomer(Long id) {
        return userRepository.findById(id).orElse(null);
    }


    public User createCustomer(User user) {
        if (userRepository.findByName(user.getName()).isPresent()) {
            throw new RegistrationException("Username already exists");
        }

        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new RegistrationException("Email address is already registered");
        }


         user.setRoles(user.getRoles());

        // Hash and store the password securely
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return userRepository.save(user);
    }

    public Optional<User> findByUsername(String username) {
       return userRepository.findByName(username);
    }

    public List<ServiceRequest> getAllServiceRequests() {

        return serviceRequestRepository.findAll();
    }
}