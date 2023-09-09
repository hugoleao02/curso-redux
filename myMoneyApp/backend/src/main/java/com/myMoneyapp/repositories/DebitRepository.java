package com.myMoneyapp.repositories;

import com.myMoneyapp.models.BillingCycle;
import com.myMoneyapp.models.Debit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.UUID;
@Repository
public interface DebitRepository extends JpaRepository<Debit, UUID> {
    

}
