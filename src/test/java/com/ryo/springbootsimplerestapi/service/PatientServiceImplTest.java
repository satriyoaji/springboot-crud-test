package com.ryo.springbootsimplerestapi.service;

import com.ryo.springbootsimplerestapi.entity.Patient;
import com.ryo.springbootsimplerestapi.repository.PatientRepository;
import com.ryo.springbootsimplerestapi.service.impl.PatientServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

class PatientServiceImplTest {

    @InjectMocks
    PatientServiceImpl patientService;

    @Mock
    PatientRepository patientRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testSave() {
        Patient patient = new Patient();
        when(patientRepository.save(any(Patient.class))).thenReturn(patient);

        Patient savedPatient = patientService.save(patient);

        assertEquals(patient, savedPatient);
    }

    @Test
    void testUpdatePatient() {
        Long id = 1L;
        Patient existingPatient = new Patient();
        existingPatient.setId(id);

        Patient updatedPatient = new Patient();
        updatedPatient.setId(id);
        updatedPatient.setFirstName("UpdatedFirstName");

        when(patientRepository.findById(id)).thenReturn(Optional.of(existingPatient));
        when(patientRepository.save(any(Patient.class))).thenReturn(updatedPatient);

        Patient result = patientService.updatePatient(id, updatedPatient);

        assertEquals("UpdatedFirstName", result.getFirstName());
    }

    @Test
    void testUpdatePatient_NotFound() {
        Long id = 1L;
        when(patientRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> patientService.updatePatient(id, new Patient()));
    }

    @Test
    void testFindById() {
        Long id = 1L;
        Patient patient = new Patient();
        when(patientRepository.findById(id)).thenReturn(Optional.of(patient));

        Optional<Patient> result = patientService.findById(id);

        assertEquals(patient, result.orElse(null));
    }

    @Test
    void testGetAllPatients() {
        List<Patient> patients = new ArrayList<>();
        patients.add(new Patient());
        Page<Patient> page = new PageImpl<>(patients);
        when(patientRepository.findAll(any(Pageable.class))).thenReturn(page);

        Page<Patient> result = patientService.getAllPatients(Pageable.unpaged());

        assertEquals(1, result.getContent().size());
    }

    @Test
    void testSearch() {
        List<Patient> patients = new ArrayList<>();
        patients.add(new Patient());
        Page<Patient> page = new PageImpl<>(patients);
        when(patientRepository.findByPidContainingIgnoreCaseOrFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(anyString(), anyString(), anyString(), any(Pageable.class))).thenReturn(page);

        Page<Patient> result = patientService.search("keyword", Pageable.unpaged());

        assertEquals(1, result.getContent().size());
    }

    @Test
    void testDelete() {
        Long id = 1L;

        patientService.delete(id);

        // Verify that deleteById method of repository is called once with the specified id
        verify(patientRepository, times(1)).deleteById(id);
    }
}
