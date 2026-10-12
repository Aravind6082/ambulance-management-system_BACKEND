package com.ambulance_management.service;

import com.ambulance_management.entity.Patient;
import com.ambulance_management.entity.User;
import com.ambulance_management.repository.PatientRepository;
import com.ambulance_management.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class PatientService {

    private final PatientRepository patientRepository;
    private final UserRepository userRepository;

    public PatientService(
            PatientRepository patientRepository,
            UserRepository userRepository) {

        this.patientRepository = patientRepository;
        this.userRepository = userRepository;
    }

    public Patient createPatient(
            Long userId,
            String address,
            String emergencyContact) {

        User user = userRepository.findById(userId)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        if (patientRepository.findByUserId(userId).isPresent()) {
            throw new RuntimeException(
                    "Patient profile already exists");
        }

        Patient patient = new Patient();

        patient.setUser(user);
        patient.setAddress(address);
        patient.setEmergencyContact(emergencyContact);

        return patientRepository.save(patient);
    }

    public Patient getPatient(Long id) {

        return patientRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Patient not found"));
    }

    public Patient getPatientByUserId(Long userId) {

        return patientRepository.findByUserId(userId)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Patient profile not found"));
    }
}