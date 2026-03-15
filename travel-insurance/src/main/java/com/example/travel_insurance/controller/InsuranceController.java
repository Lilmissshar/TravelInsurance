package com.example.travel_insurance.controller;

import com.example.travel_insurance.dto.PurchaseRequest;
import com.example.travel_insurance.entity.InsurancePolicy;
import com.example.travel_insurance.service.*;

import jakarta.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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

        model.addAttribute("request", request);

        return "customer";
    }

    @PostMapping("/summary")
        public String summary(@Valid @ModelAttribute("request") PurchaseRequest request, BindingResult result, Model model) {

        try {
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

        } catch (Exception e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "summary";
        }
    }

    @PostMapping("/confirm")
    public String confirm(@ModelAttribute PurchaseRequest request, Model model) {
        // Save the purchase
        InsurancePolicy policy = insuranceService.purchase(request);

        // Pass the saved policy to the success page
        model.addAttribute("policy", policy);
        return "confirm"; // Thymeleaf template: confirm.html
    }
}