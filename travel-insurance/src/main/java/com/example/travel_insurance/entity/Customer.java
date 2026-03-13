package com.example.travel_insurance.entity;
import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Customer {

    @Id
    @GeneratedValue
    private Long id;

    private String fullName;

    private String nric;

    private LocalDate dateOfBirth;

    private String gender;

    private String mobile;

    private String email;

}