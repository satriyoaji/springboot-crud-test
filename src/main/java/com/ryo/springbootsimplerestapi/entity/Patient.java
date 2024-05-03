package com.ryo.springbootsimplerestapi.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "patients")
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "pid", nullable = false, length = 100)
    @NotNull(message = "Patient ID is required")
    private String pid;
    @Column(name = "first_name", nullable = false, length = 200)
    @NotNull(message = "First name is required")
    private String firstName;
    @Column(name = "last_name", nullable = false, length = 200)
    @NotNull(message = "Last name is required")
    private String lastName;
    private LocalDate dateOfBirth;
    @Column(nullable = false, columnDefinition = "varchar(10) not null default 'MALE'")
    @Enumerated(EnumType.STRING)
    private GenderEnum gender;
    private String address;
    private String suburb;
    private String state;
    private String postcode;
    private String phoneNo;

    // getters and setters
}
