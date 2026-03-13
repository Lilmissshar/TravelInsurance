package com.example.travel_insurance.util;

import java.time.LocalDate;

public class NRICUtil {

    public static LocalDate getDob(String nric){

        String yy = nric.substring(0,2);
        String mm = nric.substring(2,4);
        String dd = nric.substring(4,6);

        int year = Integer.parseInt(yy);

        if(year > 30){
            year += 1900;
        } else {
            year += 2000;
        }

        return LocalDate.of(year,
                Integer.parseInt(mm),
                Integer.parseInt(dd));
    }

    public static String getGender(String nric){

        int lastDigit = Character.getNumericValue(nric.charAt(11));

        return lastDigit % 2 == 0 ? "F" : "M";
    }
}