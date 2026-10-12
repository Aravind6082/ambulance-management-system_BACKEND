package com.ambulance_management.controller;

import com.ambulance_management.entity.EmergencyRequest;
import com.ambulance_management.service.EmergencyRequestService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/emergency-requests")
@CrossOrigin(origins = "http://localhost:5173")
public class EmergencyRequestController {

    private final EmergencyRequestService emergencyRequestService;

    public EmergencyRequestController(
            EmergencyRequestService emergencyRequestService) {

        this.emergencyRequestService = emergencyRequestService;
    }

    @PostMapping
    public ResponseEntity<?> createRequest(
            @RequestParam Long patientId,
            @RequestParam String pickupLocation,
            @RequestParam String destination) {

        try {

            EmergencyRequest request =
                    emergencyRequestService.createRequest(
                            patientId,
                            pickupLocation,
                            destination
                    );

            return ResponseEntity.ok(request);

        } catch (RuntimeException e) {

            return ResponseEntity
                    .badRequest()
                    .body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<EmergencyRequest>> getAllRequests() {

        return ResponseEntity.ok(
                emergencyRequestService.getAllRequests()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getRequest(
            @PathVariable Long id) {

        try {

            return ResponseEntity.ok(
                    emergencyRequestService.getRequest(id)
            );

        } catch (RuntimeException e) {

            return ResponseEntity
                    .badRequest()
                    .body(e.getMessage());
        }
    }

    @GetMapping("/patient/{patientId}")
    public ResponseEntity<List<EmergencyRequest>> getPatientRequests(
            @PathVariable Long patientId) {

        return ResponseEntity.ok(
                emergencyRequestService
                        .getRequestsByPatient(patientId)
        );
    }

    @PutMapping("/{requestId}/assign")
    public ResponseEntity<?> assignAmbulance(
            @PathVariable Long requestId,
            @RequestParam Long ambulanceId,
            @RequestParam Long driverId) {

        try {

            return ResponseEntity.ok(
                    emergencyRequestService.assignAmbulance(
                            requestId,
                            ambulanceId,
                            driverId
                    )
            );

        } catch (RuntimeException e) {

            return ResponseEntity
                    .badRequest()
                    .body(e.getMessage());
        }
    }
    @PutMapping("/{requestId}/complete")
public ResponseEntity<?> completeRequest(
        @PathVariable Long requestId) {

    try {

        return ResponseEntity.ok(
                emergencyRequestService.completeRequest(requestId)
        );

    } catch (RuntimeException e) {

        return ResponseEntity
                .badRequest()
                .body(e.getMessage());
    }
}
}
