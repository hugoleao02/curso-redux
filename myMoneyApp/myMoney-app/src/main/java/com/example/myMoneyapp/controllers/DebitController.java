package com.example.myMoneyapp.controllers;

import com.example.myMoneyapp.models.DebitModel;
import com.example.myMoneyapp.services.DebitService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.parser.Entity;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/debits")
@CrossOrigin(origins = "*")
public class DebitController {
    
    final DebitService debitService;
    
    public DebitController(DebitService debitService) {
        this.debitService = debitService;
    }
    
    @PostMapping
    public ResponseEntity<Object> saveDebit(@ModelAttribute @RequestBody @Valid DebitModel debitModel) {
        return ResponseEntity.status(HttpStatus.CREATED).body(debitService.save(debitModel));
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Object> updateDebit(@PathVariable("id") UUID id,
                                              @Valid DebitModel debitModel) {
        Optional<DebitModel> debitModelOptional = debitService.findById(id);
        if (debitModelOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Debit not found.");
        }
        DebitModel existingDebitModel = debitModelOptional.get();
        existingDebitModel.setName(debitModel.getName());
        existingDebitModel.setValue(debitModel.getValue());
        existingDebitModel.setStatus(debitModel.getStatus());
        
        DebitModel updatedDebitModel = debitService.save(existingDebitModel);
        return ResponseEntity.status(HttpStatus.OK).body(debitService.save(updatedDebitModel));
    }
    
    
    @GetMapping
    public ResponseEntity<List<DebitModel>> getAllDebit() {
        List<DebitModel> debits = debitService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(debits);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneTask(@PathVariable(value = "id") UUID id) {
        Optional<DebitModel> debitModelOptional = debitService.findById(id);
        return debitModelOptional.
                <ResponseEntity<Object>>map(debitModel -> ResponseEntity.status(HttpStatus.OK)
                .body(debitModel)).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Debit not found."));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteDebit(@PathVariable(value = "id") UUID id) {
        Optional<DebitModel> debitModelOptional = debitService.findById(id);
        if (!debitModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body("Debit not a found.");
        }
        debitService.delete(debitModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Debit deleted successfully");
    }
    
}
