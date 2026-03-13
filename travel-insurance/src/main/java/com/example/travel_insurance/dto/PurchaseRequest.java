package com.example.travel_insurance.dto;

import com.example.travel_insurance.enums.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class PurchaseRequest {

    private Coverage coverage;

    private Area area;

    private Plan plan;

    private LocalDate startDate;

    private LocalDate endDate;

    private String fullName;

    private String nric;

    private String mobile;

    private String email;

}