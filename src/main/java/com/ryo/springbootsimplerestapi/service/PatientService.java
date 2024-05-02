package com.ryo.springbootsimplerestapi.service;

import com.ryo.springbootsimplerestapi.entity.Patient;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

// PatientService.java
public interface PatientService {
    Patient save(Patient patient);
    Optional<Patient> findById(Long id);
    List<Patient> search(String keyword, Pageable pageable);
    void delete(Long id);
}

