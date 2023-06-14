package com.example.myMoneyapp.services;

import com.example.myMoneyapp.models.BillingCycleModel;
import com.example.myMoneyapp.models.CreditModel;
import com.example.myMoneyapp.models.DebitModel;
import com.example.myMoneyapp.repositories.BillingCycleRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    
    public Page<BillingCycleModel> findAll(Pageable pageable) {
        return billingCycleRepository.findAll(pageable);
    }
    
    public void delete(BillingCycleModel billingCycleModel) {
        billingCycleRepository.delete(billingCycleModel);
    }
    
    public long count() {
        return billingCycleRepository.count();
    }
    
    public double getTotalCredits() {
        List<BillingCycleModel> billingCycles = billingCycleRepository.findAll();
        double totalCredits = 0.0;
        
        for (BillingCycleModel billingCycle : billingCycles) {
            totalCredits += sumCredits(billingCycle.getCredits());
        }
        
        return totalCredits;
    }
    
    private double sumCredits(List<CreditModel> credits) {
        double sum = 0.0;
        
        for (CreditModel credit : credits) {
            sum += credit.getValue();
        }
        
        return sum;
    }
    
    public double getTotalDebits() {
        List<BillingCycleModel> billingCycles = billingCycleRepository.findAll();
        double totalDebits = 0.0;
        
        for (BillingCycleModel billingCycle : billingCycles) {
            totalDebits += sumDebits(billingCycle.getDebits());
           
        }
        
        return totalDebits;
    }
    
    private double sumDebits(List<DebitModel> debits) {
        double sum = 0.0;
        
        for (DebitModel debit : debits) {
            sum += debit.getValue();
        }
        
        return sum;
    }
    
    
}
    

