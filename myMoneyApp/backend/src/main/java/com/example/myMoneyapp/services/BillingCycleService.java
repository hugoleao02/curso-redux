package com.example.myMoneyapp.services;

import com.example.myMoneyapp.dtos.request.BillingCycleRequest;
import com.example.myMoneyapp.dtos.response.BillingCycleResponse;
import com.example.myMoneyapp.exception.CustomNotFoundException;
import com.example.myMoneyapp.models.BillingCycle;
import com.example.myMoneyapp.models.Credit;
import com.example.myMoneyapp.models.Debit;
import com.example.myMoneyapp.repositories.BillingCycleRepository;
import com.example.myMoneyapp.repositories.CreditRepository;
import com.example.myMoneyapp.repositories.DebitRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class BillingCycleService {
    
    private final BillingCycleRepository billingCycleRepository;
    private final DebitRepository debitRepository;
    private final CreditRepository creditRepository;
    
    @Autowired
    public BillingCycleService(BillingCycleRepository billingCycleRepository, DebitRepository debitRepository, CreditRepository creditRepository) {
        this.billingCycleRepository = billingCycleRepository;
        this.debitRepository = debitRepository;
        this.creditRepository = creditRepository;
    }
    
    
    public Page<BillingCycleResponse> getAllBillingCycles(Pageable pageable) {
        Page<BillingCycle> billingCyclesPage = billingCycleRepository.findAll(pageable);
        
        List<BillingCycleResponse> responses = billingCyclesPage
                .stream()
                .map(this::mapBillingCycleToResponse)
                .collect(Collectors.toList());
        
        return new PageImpl<>(responses, pageable, billingCyclesPage.getTotalElements());
    }
    public BillingCycleResponse getBillingCycleById(UUID id) {
        BillingCycle billingCycle = getBillingCycle(id);
        return mapBillingCycleToResponse(billingCycle);
    }
    
    @Transactional
    public BillingCycleResponse createBillingCycle(BillingCycleRequest request) {
        BillingCycle billingCycle = new BillingCycle();
        BeanUtils.copyProperties(request, billingCycle);
        
        billingCycle.setCredits(creditRepository.saveAll(request.getCredits()));
        billingCycle.setDebits(debitRepository.saveAll(request.getDebits()));
        
        BillingCycle savedBillingCycle = billingCycleRepository.save(billingCycle);
        
        return mapBillingCycleToResponse(savedBillingCycle);
    }
    
    @Transactional
    public BillingCycleResponse updateBillingCycle(UUID id, BillingCycleRequest request) {
        BillingCycle billingCycle = getBillingCycle(id);
        BeanUtils.copyProperties(request, billingCycle);
        
        billingCycle = billingCycleRepository.save(billingCycle);
        
        return mapBillingCycleToResponse(billingCycle);
    }
    
    public boolean deleteBillingCycle(UUID id) {
        Optional<BillingCycle> billingCycle = billingCycleRepository.findById(id);
        
        if (billingCycle.isPresent()) {
            billingCycleRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
    
    @Transactional
    public BigDecimal calculateSummary(UUID id) {
        BillingCycle billingCycle = getBillingCycle(id);
        BigDecimal totalCredits = calculateTotalCredits(billingCycle);
        BigDecimal totalDebits = calculateTotalDebits(billingCycle);
        
        return totalCredits.subtract(totalDebits);
    }
    
    private BillingCycle getBillingCycle(UUID id) {
        return billingCycleRepository.findById(id)
                .orElseThrow(() -> new CustomNotFoundException("Ciclo de faturamento não encontrado"));
    }
    
    private BillingCycleResponse mapBillingCycleToResponse(BillingCycle billingCycle) {
        BillingCycleResponse response = new BillingCycleResponse();
        BeanUtils.copyProperties(billingCycle, response);
        return response;
    }
    
    @Transactional
    private BigDecimal calculateTotalCredits(BillingCycle billingCycle) {
        if (Objects.isNull(billingCycle.getCredits())) {
            return BigDecimal.ZERO;
        }
        return billingCycle.getCredits()
                .stream()
                .map(Credit::getValue)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
    
    @Transactional
    private BigDecimal calculateTotalDebits(BillingCycle billingCycle) {
        if (Objects.isNull(billingCycle.getDebits())) {
            return BigDecimal.ZERO;
        }
        return billingCycle.getDebits()
                .stream()
                .map(Debit::getValue)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
