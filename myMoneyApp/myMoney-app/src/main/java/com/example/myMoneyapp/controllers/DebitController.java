package com.example.myMoneyapp.controllers;

import com.example.myMoneyapp.models.DebitModel;
import com.example.myMoneyapp.services.DebitService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/debits")
@CrossOrigin(origins = "*")
public class DebitController {

    final DebitService debitService;
    
    public DebitController(DebitService debitService) {
        this.debitService = debitService;
    }
    
    @PostMapping
    public ResponseEntity<Object> saveDebit(@RequestBody @Valid DebitModel debitModel){
        return ResponseEntity.status(HttpStatus.CREATED).body(debitService.save(debitModel));
    }
}
