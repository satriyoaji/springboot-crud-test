package com.ryo.springbootsimplerestapi.controller;

import com.ryo.springbootsimplerestapi.entity.Patient;
import com.ryo.springbootsimplerestapi.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

// PatientController.java
@RestController
@RequestMapping("api/patients")
public class PatientController {
    private PatientService patientService;
    @Autowired
    // {base_url}/api/patients
    @GetMapping
    public ResponseEntity<String> getAllUsers(){
        return new ResponseEntity<>("patients", HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Patient> createPatient(@RequestBody Patient patient) {
        Patient savedPatient = patientService.save(patient);
        return ResponseEntity.ok(savedPatient);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Patient> getPatientById(@PathVariable Long id) {
        Optional<Patient> patient = patientService.findById(id);
        return patient.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/search")
    public ResponseEntity<List<Patient>> searchPatients(@RequestParam String keyword, @PageableDefault(size = 10) Pageable pageable) {
        Page<Patient> patients = patientService.search(keyword, pageable);
        return ResponseEntity.ok(patients.getContent());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePatient(@PathVariable Long id) {
        patientService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
