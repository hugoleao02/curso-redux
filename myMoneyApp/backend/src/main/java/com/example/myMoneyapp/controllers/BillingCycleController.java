package com.example.myMoneyapp.controllers;

import com.example.myMoneyapp.dtos.request.BillingCycleRequest;
import com.example.myMoneyapp.dtos.response.BillingCycleResponse;
import com.example.myMoneyapp.exception.CustomNotFoundException;
import com.example.myMoneyapp.services.BillingCycleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.UUID;

@RestController
@RequestMapping("/api/billing-cycles")
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST })
public class BillingCycleController {
    
    private final BillingCycleService billingCycleService;
    
    @Autowired
    public BillingCycleController(BillingCycleService billingCycleService) {
        this.billingCycleService = billingCycleService;
    }
    
    @GetMapping
    public ResponseEntity<Page<BillingCycleResponse>> getAllBillingCycles(Pageable pageable) {
        Page<BillingCycleResponse> billingCyclesPage = billingCycleService.getAllBillingCycles(pageable);
        return ResponseEntity.ok(billingCyclesPage);
    }
    @GetMapping("/{id}")
    public ResponseEntity<BillingCycleResponse> getBillingCycleById(@PathVariable String id) {
        try {
            UUID uuid = UUID.fromString(id);
            BillingCycleResponse billingCycle = billingCycleService.getBillingCycleById(uuid);
            return ResponseEntity.ok(billingCycle);
        } catch (IllegalArgumentException e) {
            throw new CustomNotFoundException("Ciclo de faturamento não encontrado");
        }
    }
    
    @PostMapping
    public ResponseEntity<BillingCycleResponse> createBillingCycle(@RequestBody @Valid BillingCycleRequest request) {
        BillingCycleResponse billingCycleResponse = billingCycleService.createBillingCycle(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(billingCycleResponse);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<BillingCycleResponse> updateBillingCycle(@PathVariable String id, @RequestBody @Valid BillingCycleRequest request) {
        try {
            UUID uuid = UUID.fromString(id);
            BillingCycleResponse updatedBillingCycle = billingCycleService.updateBillingCycle(uuid, request);
            return ResponseEntity.ok(updatedBillingCycle);
        } catch (IllegalArgumentException e) {
            throw new CustomNotFoundException("Ciclo de faturamento não encontrado");
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBillingCycle(@PathVariable UUID id) {
        boolean deleted = billingCycleService.deleteBillingCycle(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        }
        throw new CustomNotFoundException("Ciclo de faturamento não encontrado");
    }
    
    @GetMapping("/summary/{id}")
    public ResponseEntity<BigDecimal> getSummary(@PathVariable String id) {
        try {
            UUID uuid = UUID.fromString(id);
            BigDecimal summary = billingCycleService.calculateSummary(uuid);
            return ResponseEntity.ok(summary);
        } catch (IllegalArgumentException e) {
            throw new CustomNotFoundException("Ciclo de faturamento não encontrado");
        }
    }
}
