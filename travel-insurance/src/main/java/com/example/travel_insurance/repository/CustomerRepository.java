package com.example.travel_insurance.repository;

import com.example.travel_insurance.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}