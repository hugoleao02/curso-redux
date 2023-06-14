package com.example.myMoneyapp.repositories;

import com.example.myMoneyapp.models.CreditModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CreditRepository  extends JpaRepository<CreditModel, UUID> {
}
