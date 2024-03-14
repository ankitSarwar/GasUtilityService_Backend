package com.example.GasUtilityService.service;

import com.example.GasUtilityService.model.RequestStatus;
import com.example.GasUtilityService.model.ServiceRequest;
import com.example.GasUtilityService.model.User;
import com.example.GasUtilityService.repo.ServiceRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ServiceRequestService {
    @Autowired
    private ServiceRequestRepository serviceRequestRepository;

    public ServiceRequest submitServiceRequest(ServiceRequest request, User user) {
        request.setSubmittedAt(LocalDateTime.now());
        request.setStatus("Pending");
        request.setUser(user);
        return serviceRequestRepository.save(request);
    }

    public ServiceRequest trackServiceRequest(Long requestId) {
        return serviceRequestRepository.findById(requestId).orElse(null);
    }

    public ServiceRequest getServiceRequest(Long id) {
        return serviceRequestRepository.findById(id).orElse(null);
    }

    public List<ServiceRequest> getAllServiceRequests() {
        return serviceRequestRepository.findAll();
    }

    public ServiceRequest updateServiceRequestStatus(Long id, RequestStatus newStatus) {
        Optional<ServiceRequest> optionalRequest = serviceRequestRepository.findById(id);

        if (optionalRequest.isPresent()) {
            ServiceRequest serviceRequest = optionalRequest.get();
            serviceRequest.setStatus(String.valueOf(newStatus));
            return serviceRequestRepository.save(serviceRequest);
        } else {
            return null;
        }
    }
}