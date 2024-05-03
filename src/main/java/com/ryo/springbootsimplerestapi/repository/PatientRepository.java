package com.ryo.springbootsimplerestapi.repository;

import com.ryo.springbootsimplerestapi.entity.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;

// PatientRepository.java
@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
    Page<Patient> findAll(Pageable pageable);
    Page<Patient> findByPidContainingIgnoreCaseOrFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(String pid, String firstName, String lastName, Pageable pageable);
//    @Query("SELECT p FROM Patient p WHERE p.pid LIKE %:pid%")
//    List<Patient> searchByPidContaining(@Param("pid") String pid, Pageable pageable);
}
