package com.example.travel_insurance.dto;

import com.example.travel_insurance.enums.*;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
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

    @NotBlank(message = "Full name is required")
    private String fullName;

    @NotBlank(message = "NRIC is required")
    @Pattern(regexp = "\\d{12}", message = "NRIC must be 12 digits")
    private String nric;

    @Email(message = "Invalid email format (aa@bb.ccc)")
    @NotBlank(message = "Email is required")
    private String email;

    @NotBlank(message = "Mobile number is required")
    @Pattern(
        regexp = "^01\\d{7,9}$",
        message = "Mobile must start with 01 and be 9–11 digits"
    )
    private String mobile;

    @NotBlank(message = "Address Line 1 is required")
    private String addressLine1;

    private String addressLine2;

    @Pattern(regexp = "\\d{5}", message = "Postcode must be 5 digits")
    private String postCode;

}