package com.example.myMoneyapp.controllers;

import com.example.myMoneyapp.models.BillingCycleModel;
import com.example.myMoneyapp.models.SummaryModel;
import com.example.myMoneyapp.services.BillingCycleService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/billingcycle")
@CrossOrigin(origins = "*")
public class BillingCycleController {
    
    final BillingCycleService billingCycleService;
    
    public BillingCycleController(BillingCycleService billingCycleService) {
        this.billingCycleService = billingCycleService;
    }
    
    @PostMapping
    public ResponseEntity<Object> saveBillingCycle(@RequestBody @Valid BillingCycleModel billingCycleModel) {
        return ResponseEntity.status(HttpStatus.CREATED).body(billingCycleService.save(billingCycleModel));
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Object> updateBillingCycle(@PathVariable("id") UUID id,
                                               @Valid BillingCycleModel billingCycleModel) {
        Optional<BillingCycleModel> billingCycleModelOptional = billingCycleService.findById(id);
        if (billingCycleModelOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Billing cycle not found.");
        }
        BillingCycleModel billingCycleModel1 = billingCycleModelOptional.get();
        billingCycleModel1.setName(billingCycleModel.getName());
        billingCycleModel1.setMonth(billingCycleModel.getMonth());
        billingCycleModel1.setYear(billingCycleModel.getYear());
        billingCycleModel1.setCredits(billingCycleModel.getCredits());
        billingCycleModel1.setDebits(billingCycleModel.getDebits());
        
        BillingCycleModel updatedBillingModel = billingCycleService.save(billingCycleModel1);
        return ResponseEntity.status(HttpStatus.OK).body(billingCycleService.save(updatedBillingModel));
    }
    
    
    @GetMapping
    public ResponseEntity<List<BillingCycleModel>> getAllBillingCycle() {
        List<BillingCycleModel> billingCycle = billingCycleService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(billingCycle);
    }
    
    @GetMapping("/count")
    public ResponseEntity<Long> getBillingCycleCount() {
        long count = billingCycleService.count();
        return ResponseEntity.ok(count);
    }
    @GetMapping("summary")
    public ResponseEntity<Object> getBillingCycleSummary(){
        double totalCredits = billingCycleService.getTotalCredits();
        double totalDebits = billingCycleService.getTotalDebits();
        double balance = totalCredits - totalDebits;

        SummaryModel summary = new SummaryModel(totalCredits, totalDebits, balance);

        return ResponseEntity.status(HttpStatus.OK).body(summary);
    }
    
    
    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneBillingCycle(@PathVariable(value = "id") UUID id) {
        Optional<BillingCycleModel> billingCycleModelOptional = billingCycleService.findById(id);
        return billingCycleModelOptional.
                <ResponseEntity<Object>>map(debitModel -> ResponseEntity.status(HttpStatus.OK)
                .body(debitModel)).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Debit not found."));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteCredit(@PathVariable(value = "id") UUID id) {
        Optional<BillingCycleModel> billingCycleModelOptional = billingCycleService.findById(id);
        if (billingCycleModelOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body("Billing cycle not a found.");
        }
        billingCycleService.delete(billingCycleModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Billing cycle deleted successfully");
    }
    
    
    
  
    
}
