package com.example.travel_insurance.service;

import com.example.travel_insurance.dto.PurchaseRequest;
import com.example.travel_insurance.entity.*;
import com.example.travel_insurance.repository.*;
import com.example.travel_insurance.util.NRICUtil;

import org.springframework.stereotype.Service;

import java.time.temporal.ChronoUnit;

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

        customer.setDateOfBirth(
                NRICUtil.getDob(request.getNric()));

        customer.setGender(
                NRICUtil.getGender(request.getNric()));

        customerRepository.save(customer);

        long days = ChronoUnit.DAYS.between(
                request.getStartDate(),
                request.getEndDate());

        double price = pricingService.calculatePrice(
                request.getCoverage(),
                request.getArea(),
                request.getPlan(),
                days);

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
}