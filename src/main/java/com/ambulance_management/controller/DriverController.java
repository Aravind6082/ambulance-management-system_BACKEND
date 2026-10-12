package com.ambulance_management.controller;

import com.ambulance_management.entity.Driver;
import com.ambulance_management.service.DriverService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/drivers")
@CrossOrigin(origins = "http://localhost:5173")
public class DriverController {

    private final DriverService driverService;

    public DriverController(DriverService driverService) {
        this.driverService = driverService;
    }

    @PostMapping
    public ResponseEntity<?> createDriver(
            @RequestParam Long userId,
            @RequestParam String licenseNumber,
            @RequestParam String status) {

        try {

            Driver driver = driverService.createDriver(
                    userId,
                    licenseNumber,
                    status
            );

            return ResponseEntity.ok(driver);

        } catch (RuntimeException e) {

            return ResponseEntity
                    .badRequest()
                    .body(e.getMessage());
        }
    }
    @GetMapping
public ResponseEntity<List<Driver>> getAllDrivers() {

    return ResponseEntity.ok(
            driverService.getAllDrivers()
    );
}

    @GetMapping("/{id}")
    public ResponseEntity<?> getDriver(
            @PathVariable Long id) {

        try {

            return ResponseEntity.ok(
                    driverService.getDriver(id)
            );

        } catch (RuntimeException e) {

            return ResponseEntity
                    .badRequest()
                    .body(e.getMessage());
        }
    }
}
