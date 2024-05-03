package com.ryo.springbootsimplerestapi.service.impl;

import com.ryo.springbootsimplerestapi.entity.Patient;
import com.ryo.springbootsimplerestapi.repository.PatientRepository;
import com.ryo.springbootsimplerestapi.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

// PatientServiceImpl.java
@Service
public class PatientServiceImpl implements PatientService {
    @Autowired
    private PatientRepository patientRepository;

    @Override
    public Patient save(Patient patient) {
        return patientRepository.save(patient);
    }

    @Override
    public Patient updatePatient(Long id, Patient updatedPatient) {
        Optional<Patient> optionalPatient = patientRepository.findById(id);
        if (optionalPatient.isPresent()) {
            Patient existingPatient = optionalPatient.get();
            // Update fields with non-null values from updatedPatient
            if (updatedPatient.getPid() != null) {
                existingPatient.setPid(updatedPatient.getPid());
            }
            if (updatedPatient.getFirstName() != null) {
                existingPatient.setFirstName(updatedPatient.getFirstName());
            }
            if (updatedPatient.getLastName() != null) {
                existingPatient.setLastName(updatedPatient.getLastName());
            }
            if (updatedPatient.getDateOfBirth() != null) {
                existingPatient.setDateOfBirth(updatedPatient.getDateOfBirth());
            }
            if (updatedPatient.getGender() != null) {
                existingPatient.setGender(updatedPatient.getGender());
            }
            if (updatedPatient.getAddress() != null) {
                existingPatient.setAddress(updatedPatient.getAddress());
            }
            if (updatedPatient.getSuburb() != null) {
                existingPatient.setSuburb(updatedPatient.getSuburb());
            }
            if (updatedPatient.getState() != null) {
                existingPatient.setState(updatedPatient.getState());
            }
            if (updatedPatient.getPostcode() != null) {
                existingPatient.setPostcode(updatedPatient.getPostcode());
            }
            if (updatedPatient.getPhoneNo() != null) {
                existingPatient.setPhoneNo(updatedPatient.getPhoneNo());
            }

            // Save the updated patient
            return patientRepository.save(existingPatient);
        } else {
            throw new IllegalArgumentException("Patient not found with ID: " + id);
        }
    }

    @Override
    public Optional<Patient> findById(Long id) {
        return patientRepository.findById(id);
    }

    @Override
    public Page<Patient> getAllPatients(Pageable pageable) {
        return patientRepository.findAll(pageable);
    }

    @Override
    public Page<Patient> search(String keyword, Pageable pageable) {
        return patientRepository.findByPidContainingIgnoreCaseOrFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(keyword, keyword, keyword, pageable);
    }

    @Override
    public void delete(Long id) {
        patientRepository.deleteById(id);
    }
}

