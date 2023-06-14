package com.example.myMoneyapp.controllers;

import com.example.myMoneyapp.models.CreditModel;
import com.example.myMoneyapp.models.DebitModel;
import com.example.myMoneyapp.services.CreditService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/credits")
@CrossOrigin(origins = "*")
public class CreditController {

    final CreditService creditService;
    
    public CreditController(CreditService creditService) {
        this.creditService = creditService;
    }
    
    @PostMapping
    public ResponseEntity<Object> saveCredit(@ModelAttribute @RequestBody @Valid CreditModel creditModel) {
        return ResponseEntity.status(HttpStatus.CREATED).body(creditService.save(creditModel));
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Object> updateCredit(@PathVariable("id") UUID id,
                                              @Valid CreditModel creditModel) {
        Optional<CreditModel> creditModelOptional = creditService.findById(id);
        if (creditModelOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Credit not found.");
        }
        CreditModel creditModel1 = creditModelOptional.get();
        creditModel1.setName(creditModel.getName());
        creditModel1.setValue(creditModel.getValue());
        
        CreditModel updatedCreditModel = creditService.save(creditModel1);
        return ResponseEntity.status(HttpStatus.OK).body(creditService.save(updatedCreditModel));
    }
    
    
    @GetMapping
    public ResponseEntity<List<CreditModel>> getAllCredit() {
        List<CreditModel> credits = creditService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(credits);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneCredit(@PathVariable(value = "id") UUID id) {
        Optional<CreditModel> creditModelOptional = creditService.findById(id);
        return creditModelOptional.
                <ResponseEntity<Object>>map(debitModel -> ResponseEntity.status(HttpStatus.OK)
                .body(debitModel)).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Debit not found."));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteCredit(@PathVariable(value = "id") UUID id) {
        Optional<CreditModel> creditModelOptional = creditService.findById(id);
        if (creditModelOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body("Credit not a found.");
        }
        creditService.delete(creditModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Credit deleted successfully");
    }
   
    
}
