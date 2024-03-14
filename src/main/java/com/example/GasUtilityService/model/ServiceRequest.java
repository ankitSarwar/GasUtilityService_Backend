package com.example.GasUtilityService.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServiceRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String type;
    private String details;
    private LocalDateTime submittedAt;
    private LocalDateTime resolvedAt;
    private String status;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private User user;

}