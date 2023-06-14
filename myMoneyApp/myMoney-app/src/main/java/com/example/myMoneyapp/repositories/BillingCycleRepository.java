package com.example.myMoneyapp.repositories;

import com.example.myMoneyapp.models.BillingCycleModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface BillingCycleRepository extends JpaRepository<BillingCycleModel, UUID> {
}
