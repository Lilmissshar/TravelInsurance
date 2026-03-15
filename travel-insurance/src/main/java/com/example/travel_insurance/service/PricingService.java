package com.example.travel_insurance.service;

import com.example.travel_insurance.enums.*;
import org.springframework.stereotype.Service;

@Service
public class PricingService {

    public double calculatePrice(Coverage coverage,
                                 Area area,
                                 Plan plan,
                                 long days){

        if (coverage == null || area == null || plan == null) {
            throw new IllegalArgumentException("Coverage, Area and Plan must be selected");
        }

    // pricing logic here

        if(coverage == Coverage.SINGLE){

            if(plan == Plan.A){

                switch(area){
                    case AREA1: return days * 10;
                    case AREA2: return days * 15;
                    case AREA3: return days * 20;
                    case AREA4: return days * 5;
                }

            }

            if(plan == Plan.B){

                switch(area){
                    case AREA1: return days * 20;
                    case AREA2: return days * 30;
                    case AREA3: return days * 40;
                    case AREA4: return days * 10;
                }

            }

        }

        if(coverage == Coverage.ANNUAL){

            if(area == Area.AREA4){
                throw new RuntimeException("Area 4 cannot be annual");
            }

            if(plan == Plan.A){

                switch(area){
                    case AREA1: return 150;
                    case AREA2: return 200;
                    case AREA3: return 250;
                }

            }

            if(plan == Plan.B){

                switch(area){
                    case AREA1: return 200;
                    case AREA2: return 250;
                    case AREA3: return 300;
                }

            }

        }

        throw new RuntimeException("Invalid selection");
    }
}