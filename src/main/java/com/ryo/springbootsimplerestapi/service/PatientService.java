package com.ryo.springbootsimplerestapi.service;

import com.ryo.springbootsimplerestapi.entity.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

// PatientService.java
public interface PatientService {
    Patient save(Patient patient);
    Patient updatePatient(Long id, Patient updatedPatient);
    Optional<Patient> findById(Long id);
    Page<Patient> getAllPatients(Pageable pageable);
//    List<Patient> search(String keyword, Pageable pageable);
    Page<Patient> search(String keyword, Pageable pageable);
    void delete(Long id);
}

