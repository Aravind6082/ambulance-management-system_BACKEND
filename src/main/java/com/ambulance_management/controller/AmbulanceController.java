package com.ambulance_management.controller;

import com.ambulance_management.entity.Ambulance;
import com.ambulance_management.service.AmbulanceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ambulances")
@CrossOrigin(origins = "http://localhost:5173")
public class AmbulanceController {

    private final AmbulanceService ambulanceService;

    public AmbulanceController(AmbulanceService ambulanceService) {
        this.ambulanceService = ambulanceService;
    }

    @PostMapping
    public ResponseEntity<Ambulance> addAmbulance(
            @RequestBody Ambulance ambulance) {

        return ResponseEntity.ok(
                ambulanceService.addAmbulance(ambulance)
        );
    }

    @GetMapping
    public ResponseEntity<List<Ambulance>> getAllAmbulances() {

        return ResponseEntity.ok(
                ambulanceService.getAllAmbulances()
        );
    }

    @GetMapping("/available")
    public ResponseEntity<List<Ambulance>> getAvailableAmbulances() {

        return ResponseEntity.ok(
                ambulanceService.getAvailableAmbulances()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getAmbulance(
            @PathVariable Long id) {

        try {
            return ResponseEntity.ok(
                    ambulanceService.getAmbulance(id)
            );
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest()
                    .body(e.getMessage());
        }
    }

    @PutMapping("/{id}/availability")
    public ResponseEntity<?> updateAvailability(
            @PathVariable Long id,
            @RequestParam boolean available) {

        try {
            return ResponseEntity.ok(
                    ambulanceService.updateAvailability(
                            id, available
                    )
            );
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest()
                    .body(e.getMessage());
        }
    }
}

