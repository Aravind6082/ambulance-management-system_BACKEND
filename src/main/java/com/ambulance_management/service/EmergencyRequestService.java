package com.ambulance_management.service;

import com.ambulance_management.entity.Ambulance;
import com.ambulance_management.entity.Driver;
import com.ambulance_management.entity.EmergencyRequest;
import com.ambulance_management.entity.Patient;
import com.ambulance_management.repository.AmbulanceRepository;
import com.ambulance_management.repository.DriverRepository;
import com.ambulance_management.repository.EmergencyRequestRepository;
import com.ambulance_management.repository.PatientRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmergencyRequestService {

    private final EmergencyRequestRepository emergencyRequestRepository;
    private final PatientRepository patientRepository;
    private final AmbulanceRepository ambulanceRepository;
    private final DriverRepository driverRepository;

    public EmergencyRequestService(
            EmergencyRequestRepository emergencyRequestRepository,
            PatientRepository patientRepository,
            AmbulanceRepository ambulanceRepository,
            DriverRepository driverRepository) {

        this.emergencyRequestRepository = emergencyRequestRepository;
        this.patientRepository = patientRepository;
        this.ambulanceRepository = ambulanceRepository;
        this.driverRepository = driverRepository;
    }

    public EmergencyRequest createRequest(
            Long patientId,
            String pickupLocation,
            String destination) {

        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() ->
                        new RuntimeException("Patient not found"));

        EmergencyRequest request = new EmergencyRequest();

        request.setPatient(patient);
        request.setPickupLocation(pickupLocation);
        request.setDestination(destination);
        request.setStatus("REQUESTED");

        return emergencyRequestRepository.save(request);
    }

    public List<EmergencyRequest> getAllRequests() {
        return emergencyRequestRepository.findAll();
    }

    public List<EmergencyRequest> getRequestsByPatient(Long patientId) {
        return emergencyRequestRepository.findByPatientId(patientId);
    }

    public EmergencyRequest getRequest(Long id) {

        return emergencyRequestRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Emergency request not found"));
    }

    public EmergencyRequest assignAmbulance(
            Long requestId,
            Long ambulanceId,
            Long driverId) {

        EmergencyRequest request = getRequest(requestId);

        Ambulance ambulance = ambulanceRepository.findById(ambulanceId)
                .orElseThrow(() ->
                        new RuntimeException("Ambulance not found"));

        Driver driver = driverRepository.findById(driverId)
                .orElseThrow(() ->
                        new RuntimeException("Driver not found"));

        if (!ambulance.isAvailable()) {
            throw new RuntimeException("Ambulance is not available");
        }

        if (!"AVAILABLE".equalsIgnoreCase(driver.getStatus())) {
            throw new RuntimeException("Driver is not available");
        }

        request.setAmbulance(ambulance);
        request.setDriver(driver);
        request.setStatus("ASSIGNED");

        ambulance.setAvailable(false);
        driver.setStatus("BUSY");

        ambulanceRepository.save(ambulance);
        driverRepository.save(driver);

        return emergencyRequestRepository.save(request);
    }
    public EmergencyRequest completeRequest(Long requestId) {

    EmergencyRequest request = getRequest(requestId);

    if (!"ASSIGNED".equalsIgnoreCase(request.getStatus())) {
        throw new RuntimeException(
                "Emergency request is not currently assigned"
        );
    }

    Ambulance ambulance = request.getAmbulance();
    Driver driver = request.getDriver();

    request.setStatus("COMPLETED");

    if (ambulance != null) {
        ambulance.setAvailable(true);
        ambulanceRepository.save(ambulance);
    }

    if (driver != null) {
        driver.setStatus("AVAILABLE");
        driverRepository.save(driver);
    }

    return emergencyRequestRepository.save(request);
}
}
