package com.ambulance_management.repository;

import com.ambulance_management.entity.Ambulance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AmbulanceRepository
        extends JpaRepository<Ambulance, Long> {

    List<Ambulance> findByAvailableTrue();
}