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
    public Optional<Patient> findById(Long id) {
        return patientRepository.findById(id);
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

