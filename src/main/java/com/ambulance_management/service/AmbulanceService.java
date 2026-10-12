package com.ambulance_management.service;

import com.ambulance_management.entity.Ambulance;
import com.ambulance_management.repository.AmbulanceRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AmbulanceService {

    private final AmbulanceRepository ambulanceRepository;

    public AmbulanceService(AmbulanceRepository ambulanceRepository) {
        this.ambulanceRepository = ambulanceRepository;
    }

    public Ambulance addAmbulance(Ambulance ambulance) {
        return ambulanceRepository.save(ambulance);
    }

    public List<Ambulance> getAllAmbulances() {
        return ambulanceRepository.findAll();
    }

    public List<Ambulance> getAvailableAmbulances() {
        return ambulanceRepository.findByAvailableTrue();
    }

    public Ambulance getAmbulance(Long id) {
        return ambulanceRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Ambulance not found"));
    }

    public Ambulance updateAvailability(Long id, boolean available) {

        Ambulance ambulance = getAmbulance(id);

        ambulance.setAvailable(available);

        return ambulanceRepository.save(ambulance);
    }
}
