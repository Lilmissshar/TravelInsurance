package com.example.travel_insurance.service;

import com.example.travel_insurance.dto.PurchaseRequest;
import com.example.travel_insurance.entity.*;
import com.example.travel_insurance.repository.*;
import com.example.travel_insurance.util.NRICUtil;

import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class InsuranceService {

    private final CustomerRepository customerRepository;
    private final PolicyRepository policyRepository;
    private final PricingService pricingService;

    public InsuranceService(CustomerRepository customerRepository,
                            PolicyRepository policyRepository,
                            PricingService pricingService) {

        this.customerRepository = customerRepository;
        this.policyRepository = policyRepository;
        this.pricingService = pricingService;
    }

    public InsurancePolicy purchase(PurchaseRequest request){

        Customer customer = new Customer();

        customer.setFullName(request.getFullName());
        customer.setNric(request.getNric());
        customer.setMobile(request.getMobile());
        customer.setEmail(request.getEmail());

        LocalDate dob = NRICUtil.extractDob(request.getNric());
        String gender = NRICUtil.extractGender(request.getNric());

        customer.setDateOfBirth(dob);
        customer.setGender(gender);

        customer.setAddressLine1(request.getAddressLine1());
        customer.setAddressLine2(request.getAddressLine2());
        customer.setPostCode(request.getPostCode());

        customerRepository.save(customer);


        double price = pricingService.calculatePrice(
                request.getCoverage(),
                request.getArea(),
                request.getPlan(),
                request);

        InsurancePolicy policy = new InsurancePolicy();

        policy.setCoverage(request.getCoverage());
        policy.setArea(request.getArea());
        policy.setPlan(request.getPlan());
        policy.setStartDate(request.getStartDate());
        policy.setEndDate(request.getEndDate());
        policy.setPrice(price);
        policy.setCustomer(customer);

        return policyRepository.save(policy);
    }

        public void validateJourney(LocalDate startDate, LocalDate endDate, String plan) {

                LocalDate today = LocalDate.now();

                // Start date must be today → 1 year from today
                if (startDate.isBefore(today) || startDate.isAfter(today.plusYears(1))) {
                        throw new IllegalArgumentException("Start date must be between today and 1 year from today.");
                }

                if ("SINGLE".equalsIgnoreCase(plan)) {

                        if (endDate == null) {
                                throw new IllegalArgumentException("End date is required for single trip.");
                        }

                        if (endDate.isAfter(startDate.plusDays(180))) {
                                throw new IllegalArgumentException("Single trip cannot exceed 180 days.");
                        }

                } else if ("ANNUAL".equalsIgnoreCase(plan)) {

                        LocalDate defaultEndDate = startDate.plusYears(1).minusDays(1);

                        if (endDate == null) {
                                endDate = defaultEndDate;
                        }
                }
        }
}