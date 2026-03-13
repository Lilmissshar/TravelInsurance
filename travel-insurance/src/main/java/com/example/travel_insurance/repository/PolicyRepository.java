package com.example.travel_insurance.repository;

import com.example.travel_insurance.entity.InsurancePolicy;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PolicyRepository extends JpaRepository<InsurancePolicy, Long> {
}