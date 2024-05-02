package com.ryo.springbootsimplerestapi.entity;

import jakarta.persistence.*;
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
    private String pid;
    @Column(name = "first_name", nullable = false, length = 200)
    private String firstName;
    @Column(name = "last_name", nullable = false, length = 200)
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
