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
    private final TravelValidationService travelValidationService;

    public InsuranceController(InsuranceService insuranceService,
                               PricingService pricingService,
                            TravelValidationService travelValidationService) {
        this.insuranceService = insuranceService;
        this.pricingService = pricingService;
        this.travelValidationService = travelValidationService;
    }

    @GetMapping("/")
    public String start(Model model){
        model.addAttribute("request", new PurchaseRequest());
        return "step1";
    }

    @PostMapping("/step2")
    public String step2(@ModelAttribute("request") PurchaseRequest request,
                        Model model) {
        try {
            travelValidationService.validateJourney(request);
            model.addAttribute("request", request);
            return "step2";
        } catch (IllegalArgumentException e) {
            model.addAttribute("errorMessage", e.getMessage());
            model.addAttribute("request", request);
            return "step1";
        }
    }

    @PostMapping("/customer")
    public String customer(PurchaseRequest request, Model model){
        model.addAttribute("request", request);
        return "customer";
    }

    @PostMapping("/summary")
        public String summary(@Valid @ModelAttribute("request") PurchaseRequest request, BindingResult result, Model model) {
        try {
            double price = pricingService.calculatePrice(
                    request.getCoverage(),
                    request.getArea(),
                    request.getPlan(),
                    request);

            model.addAttribute("price", price);
            model.addAttribute("request", request);

            travelValidationService.validateAddress(request.getAddressLine1(), request.getAddressLine2());

            if (result.hasErrors()) {
                return "customer"; // return the same page
            }
            return "summary";

        } catch (Exception e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "customer";
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