package com.example.travel_insurance.util;

import java.time.LocalDate;

public class NRICUtil {


    public static String extractGender(String nric) {

        int lastDigit = Character.getNumericValue(nric.charAt(11));

        if (lastDigit % 2 == 1) {
            return "Male";
        } else {
            return "Female";
    }
}

    public static LocalDate extractDob(String nric) {

        if (nric == null || nric.length() != 12) {
            throw new IllegalArgumentException("NRIC must be 12 digits");
        }

        String dobPart = nric.substring(0, 6);

        int year = Integer.parseInt(dobPart.substring(0, 2));
        int month = Integer.parseInt(dobPart.substring(2, 4));
        int day = Integer.parseInt(dobPart.substring(4, 6));

        int currentYear = LocalDate.now().getYear() % 100;
        int fullYear = (year <= currentYear) ? 2000 + year : 1900 + year;

        try {
            return LocalDate.of(fullYear, month, day);
        } catch (RuntimeException e) {
            throw new IllegalArgumentException("NRIC first 6 digits must be a valid YYMMDD date");
        }
    }
}