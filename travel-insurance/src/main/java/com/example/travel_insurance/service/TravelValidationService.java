package com.example.travel_insurance.service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import org.springframework.stereotype.Service;

import com.example.travel_insurance.dto.PurchaseRequest;
import com.example.travel_insurance.enums.Area;
import com.example.travel_insurance.enums.Coverage;

@Service
public class TravelValidationService {

    public void validateJourney(PurchaseRequest request) {

        LocalDate today = LocalDate.now();

        if (request.getStartDate().isBefore(today) ||
            request.getStartDate().isAfter(today.plusYears(1))) {

            throw new IllegalArgumentException(
                "Start date must be between today and 1 year from today"
            );
        }

        if (request.getCoverage() == Coverage.SINGLE) {

            long days = ChronoUnit.DAYS.between(
                    request.getStartDate(),
                    request.getEndDate());

            if (days > 180) {
                throw new IllegalArgumentException(
                    "Single trip cannot exceed 180 days"
                );
            }

        } else if (request.getCoverage() == Coverage.ANNUAL) {
            LocalDate expectedEnd =
                request.getStartDate().plusYears(1).minusDays(1);

            request.setEndDate(expectedEnd);

        }

        if (request.getCoverage() == Coverage.ANNUAL
            && request.getArea() == Area.AREA4) {

            throw new IllegalArgumentException(
                "Area 4 is not allowed for annual coverage"
            );
        }
    }

    public void validateAddress(String address1, String address2) {

        if (address2 != null && !address2.isEmpty()) {

                if (address1 == null || address1.isEmpty()) {
                        throw new IllegalArgumentException(
                                "Address Line 1 must exist if Address Line 2 is provided"
                        );
                }
        }
    }
}
