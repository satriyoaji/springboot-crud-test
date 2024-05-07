package com.ryo.springbootsimplerestapi.controller;

import com.ryo.springbootsimplerestapi.entity.Patient;
import com.ryo.springbootsimplerestapi.service.PatientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Pageable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

// PatientController.java
@RestController
//@AllArgsConstructor
@RequestMapping("api/patients")
@Validated
public class PatientController {
    private final PatientService patientService;

    @Autowired
    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @Tag(name = "get", description = "GET methods of Patient APIs")
    @Operation(summary = "Get list of patients",
            description = "Get list of existing patients.")
    @GetMapping
    public ResponseEntity<List<Patient>> getAllPatients(Pageable pageable) {
        Page<Patient> patients =  patientService.getAllPatients(pageable);
        return ResponseEntity.ok(patients.getContent());
    }

    @Tag(name = "post", description = "GET methods of Patient APIs")
    @Operation(summary = "Create an patient",
            description = "Create a new patient.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Patient.class)) }),
    })
    @PostMapping
    public ResponseEntity<?> createPatient(@Valid @RequestBody Patient patient) {
        Patient savedPatient = patientService.save(patient);
        return ResponseEntity.ok(savedPatient);
    }

    @Tag(name = "put", description = "GET methods of Patient APIs")
    @Operation(summary = "Update an patient",
            description = "Update an existing patient.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Patient.class)) }),
            @ApiResponse(responseCode = "404", description = "Patient not found",
                    content = @Content) }
    )
    @PutMapping("/{id}")
    public ResponseEntity<Patient> updatePatient(@PathVariable Long id, @Valid @RequestBody Patient updatedPatient) {
        Patient updated = patientService.updatePatient(id, updatedPatient);
        return ResponseEntity.ok(updated);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get patient data by ID",
            description = "Get patient data by ID.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Patient.class)) }),
            @ApiResponse(responseCode = "404", description = "Patient not found",
                    content = @Content) }
    )
    public ResponseEntity<Patient> getPatientById(@PathVariable Long id) {
        Optional<Patient> patient = patientService.findById(id);
        return patient.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Tag(name = "get", description = "GET methods of Patient APIs")
    @Operation(summary = "Get list data of Patients with search",
            description = "Get list data of Patients with search.")
    @GetMapping("/search")
    public ResponseEntity<List<Patient>> searchPatients(@RequestParam String keyword, @PageableDefault(size = 10) Pageable pageable) {
        Page<Patient> patients = patientService.search(keyword, pageable);
        return ResponseEntity.ok(patients.getContent());
    }

    @Tag(name = "delete", description = "GET methods of Patient APIs")
    @Operation(summary = "Delete patient data by ID",
            description = "Delete patient data by ID.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Patient.class)) }),
            @ApiResponse(responseCode = "404", description = "Patient not found",
                    content = @Content) }
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePatient(@PathVariable Long id) {
        patientService.delete(id);
        return ResponseEntity.noContent().build();
    }

    // Exception handler for validation errors
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage()));
        return ResponseEntity.badRequest().body(errors);
    }
}
