package com.example.myMoneyapp.services;

import com.example.myMoneyapp.models.BillingCycleModel;
import com.example.myMoneyapp.models.CreditModel;
import com.example.myMoneyapp.repositories.BillingCycleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class BillingCycleService {

    final BillingCycleRepository billingCycleRepository;
    
    
    public BillingCycleService(BillingCycleRepository billingCycleRepository) {
        this.billingCycleRepository = billingCycleRepository;
    }
    
    public BillingCycleModel save(BillingCycleModel billingCycleModel) {
        return  billingCycleRepository.save(billingCycleModel);
    }
    
    public Optional<BillingCycleModel> findById(UUID id) {
        return billingCycleRepository.findById(id);
    }
    
    public List<BillingCycleModel> findAll() {
        return billingCycleRepository.findAll();
    }
    
    public void delete(BillingCycleModel billingCycleModel) {
        billingCycleRepository.delete(billingCycleModel);
    }
    
    public long count() {
        return billingCycleRepository.count();
    }
    
   
    
}
