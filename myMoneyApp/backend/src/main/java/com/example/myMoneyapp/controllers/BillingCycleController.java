package com.example.myMoneyapp.controllers;

import com.example.myMoneyapp.models.BillingCycleModel;
import com.example.myMoneyapp.models.SummaryModel;
import com.example.myMoneyapp.services.BillingCycleService;
import com.example.myMoneyapp.utils.ErrorParserUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST})
public class BillingCycleController {
    
    final BillingCycleService billingCycleService;
    
    public BillingCycleController(BillingCycleService billingCycleService) {
        this.billingCycleService = billingCycleService;
    }
    
    public ResponseEntity<Object> saveBillingCycle(@RequestBody @Valid BillingCycleModel billingCycleModel,
                                                   BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<String> errors = ErrorParserUtil.parseErrors(bindingResult.getFieldErrors());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Validation error", new Throwable(errors.toString()));
        }
        
        try {
            BillingCycleModel savedBillingCycle = billingCycleService.save(billingCycleModel);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedBillingCycle);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error saving billing cycle", e);
        }
    }
    
    @PutMapping("/billingCycles/{id}")
    public ResponseEntity<Object> updateBillingCycle(
            @PathVariable("id") UUID id,
            @Valid @RequestBody BillingCycleModel billingCycleModel,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            List<String> errors = ErrorParserUtil.parseErrors(bindingResult.getFieldErrors());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Validation error", new Throwable(errors.toString()));
        }
        
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
        return ResponseEntity.status(HttpStatus.OK).body(updatedBillingModel);
    }
    
    @GetMapping("/billingCycles")
    public ResponseEntity<Page<BillingCycleModel>> getAllBillingCycle(Pageable pageable) {
        Page<BillingCycleModel> billingCycles = billingCycleService.findAll(pageable);
        return ResponseEntity.status(HttpStatus.OK).body(billingCycles);
    }
    
    @GetMapping("/billingCycles/count")
    public ResponseEntity<Long> getBillingCycleCount() {
        long count = billingCycleService.count();
        return ResponseEntity.ok(count);
    }
    
    @GetMapping("/billingCycles/summary")
    public ResponseEntity<Object> getBillingCycleSummary() {
        double credits = billingCycleService.getTotalCredits();
        double debits = billingCycleService.getTotalDebits();
        double balance = credits - debits;
        
        SummaryModel summary = new SummaryModel(credits, debits, balance);
        
        return ResponseEntity.status(HttpStatus.OK).body(summary);
    }
    
    @GetMapping("/billingCycles/{id}")
    public ResponseEntity<Object> getOneBillingCycle(@PathVariable(value = "id") UUID id) {
        Optional<BillingCycleModel> billingCycleModelOptional = billingCycleService.findById(id);
        return billingCycleModelOptional.
                <ResponseEntity<Object>>map(debitModel -> ResponseEntity.status(HttpStatus.OK)
                .body(debitModel)).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Debit not found."));
    }
    
    @DeleteMapping("/billingCycles/{id}")
    public ResponseEntity<Object> deleteCredit(@PathVariable(value = "id") UUID id) {
        Optional<BillingCycleModel> billingCycleModelOptional = billingCycleService.findById(id);
        if (billingCycleModelOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body("Billing cycle not found.");
        }
        billingCycleService.delete(billingCycleModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Billing cycle deleted successfully");
    }
    
}
