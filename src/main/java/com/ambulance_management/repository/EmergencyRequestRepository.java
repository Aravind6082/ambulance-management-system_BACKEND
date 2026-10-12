package com.ambulance_management.repository;

import com.ambulance_management.entity.EmergencyRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmergencyRequestRepository
        extends JpaRepository<EmergencyRequest, Long> {

    List<EmergencyRequest> findByStatus(String status);

    List<EmergencyRequest> findByPatientId(Long patientId);
}
