package com.example.travel_insurance.controller;

import com.example.travel_insurance.dto.PurchaseRequest;
import com.example.travel_insurance.service.*;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class InsuranceController {

    private final InsuranceService insuranceService;
    private final PricingService pricingService;

    public InsuranceController(InsuranceService insuranceService,
                               PricingService pricingService) {
        this.insuranceService = insuranceService;
        this.pricingService = pricingService;
    }

    @GetMapping("/")
    public String start(){
        return "step1";
    }

    @PostMapping("/step2")
    public String step2(PurchaseRequest request, Model model){

        model.addAttribute("coverage", request.getCoverage());
        model.addAttribute("area", request.getArea());
        model.addAttribute("startDate", request.getStartDate());
        model.addAttribute("endDate", request.getEndDate());

        return "step2";
    }

    @PostMapping("/customer")
    public String customer(PurchaseRequest request, Model model){
    System.out.println(request.getCoverage());
    System.out.println(request.getArea());
        model.addAttribute("coverage", request.getCoverage());
        model.addAttribute("area", request.getArea());
        model.addAttribute("plan", request.getPlan());
        model.addAttribute("startDate", request.getStartDate());
        model.addAttribute("endDate", request.getEndDate());

        return "customer";
}

    @PostMapping("/summary")
    public String summary(PurchaseRequest request, Model model){

        long days = request.getStartDate()
                .until(request.getEndDate())
                .getDays();

        double price = pricingService.calculatePrice(
                request.getCoverage(),
                request.getArea(),
                request.getPlan(),
                days);

        model.addAttribute("price", price);
        model.addAttribute("request", request);

        return "summary";
    }

    @PostMapping("/confirm")
    public String confirm(PurchaseRequest request){

        insuranceService.purchase(request);

        return "success";
    }
}