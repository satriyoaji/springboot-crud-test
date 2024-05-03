package com.ryo.springbootsimplerestapi.controller;
import com.ryo.springbootsimplerestapi.entity.Patient;
import com.ryo.springbootsimplerestapi.repository.PatientRepository;
import com.ryo.springbootsimplerestapi.service.PatientService;
import com.ryo.springbootsimplerestapi.service.impl.PatientServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class PatientControllerTest {

    @InjectMocks
    PatientController patientController;

    @Mock
    PatientService patientService;

    @Mock
    PatientRepository patientRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllPatients() {
        List<Patient> patients = new ArrayList<>();
        patients.add(new Patient());
        Page<Patient> page = new PageImpl<>(patients);
        when(patientService.getAllPatients(any(Pageable.class))).thenReturn(page);

        List<Patient> result = patientController.getAllPatients(Pageable.unpaged()).getBody();

        assertEquals(1, result.size());
    }

    @Test
    void testCreatePatient() {
        Patient patient = new Patient();
        when(patientService.save(any(Patient.class))).thenReturn(patient);

        ResponseEntity<?> responseEntity = patientController.createPatient(patient);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    void testUpdatePatient() {
        Long id = 1L;
        Patient patient = new Patient();
        when(patientService.updatePatient(eq(id), any(Patient.class))).thenReturn(patient);

        ResponseEntity<Patient> responseEntity = patientController.updatePatient(id, patient);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    void testGetPatientById() {
        Long id = 1L;
        Patient patient = new Patient();
        when(patientService.findById(id)).thenReturn(Optional.of(patient));

        ResponseEntity<Patient> responseEntity = patientController.getPatientById(id);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    void testSearchPatients() {
        List<Patient> patients = new ArrayList<>();
        patients.add(new Patient());
        Page<Patient> page = new PageImpl<>(patients);
        when(patientService.search(anyString(), any(Pageable.class))).thenReturn(page);

        List<Patient> result = patientController.searchPatients("keyword", Pageable.unpaged()).getBody();

        assertEquals(1, result.size());
    }

    @Test
    void testDeletePatient() {
        Long id = 1L;

        ResponseEntity<Void> responseEntity = patientController.deletePatient(id);

        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
    }

    // Add tests for exception handling if needed
}

