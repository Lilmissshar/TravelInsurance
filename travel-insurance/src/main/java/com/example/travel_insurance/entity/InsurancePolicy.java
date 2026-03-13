package com.example.travel_insurance.entity;

import com.example.travel_insurance.enums.*;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
public class InsurancePolicy {

    @Id
    @GeneratedValue
    private Long id;

    @Enumerated(EnumType.STRING)
    private Coverage coverage;

    @Enumerated(EnumType.STRING)
    private Area area;

    @Enumerated(EnumType.STRING)
    private Plan plan;

    private LocalDate startDate;

    private LocalDate endDate;

    private Double price;

    @ManyToOne
    private Customer customer;
}