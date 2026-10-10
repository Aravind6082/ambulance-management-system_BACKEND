package com.ambulance_management.service;

import com.ambulance_management.entity.Driver;
import com.ambulance_management.entity.User;
import com.ambulance_management.repository.DriverRepository;
import com.ambulance_management.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DriverService {

    private final DriverRepository driverRepository;
    private final UserRepository userRepository;

    public DriverService(DriverRepository driverRepository,
                         UserRepository userRepository) {

        this.driverRepository = driverRepository;
        this.userRepository = userRepository;
    }

    public Driver createDriver(Long userId,
                               String licenseNumber,
                               String status) {

        User user = userRepository.findById(userId)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        if (driverRepository.findByUserId(userId).isPresent()) {
            throw new RuntimeException("Driver profile already exists");
        }

        if (driverRepository.findByLicenseNumber(licenseNumber).isPresent()) {
            throw new RuntimeException("License number already registered");
        }

        Driver driver = new Driver();

        driver.setUser(user);
        driver.setLicenseNumber(licenseNumber);
        driver.setStatus(status);

        return driverRepository.save(driver);
    }

    public Driver getDriver(Long id) {

        return driverRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Driver not found"));
    }

    public List<Driver> getAllDrivers() {

        return driverRepository.findAll();
    }
}