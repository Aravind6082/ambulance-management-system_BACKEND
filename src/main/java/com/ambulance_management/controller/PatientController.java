package com.ambulance_management.controller;

import com.ambulance_management.entity.Patient;
import com.ambulance_management.service.PatientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/patients")
@CrossOrigin(origins = "http://localhost:5173")
public class PatientController {

    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @PostMapping
    public ResponseEntity<?> createPatient(
            @RequestParam Long userId,
            @RequestParam String address,
            @RequestParam String emergencyContact) {

        try {

            Patient patient = patientService.createPatient(
                    userId,
                    address,
                    emergencyContact
            );

            return ResponseEntity.ok(patient);

        } catch (RuntimeException e) {

            return ResponseEntity
                    .badRequest()
                    .body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPatient(
            @PathVariable Long id) {

        try {

            return ResponseEntity.ok(
                    patientService.getPatient(id)
            );

        } catch (RuntimeException e) {

            return ResponseEntity
                    .badRequest()
                    .body(e.getMessage());
        }
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getPatientByUserId(
            @PathVariable Long userId) {

        try {

            return ResponseEntity.ok(
                    patientService.getPatientByUserId(userId)
            );

        } catch (RuntimeException e) {

            return ResponseEntity
                    .badRequest()
                    .body(e.getMessage());
        }
    }
}