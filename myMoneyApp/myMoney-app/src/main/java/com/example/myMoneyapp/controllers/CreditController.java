package com.example.myMoneyapp.controllers;

import com.example.myMoneyapp.models.CreditModal;
import com.example.myMoneyapp.services.CreditService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/credits")
@CrossOrigin(origins = "*")
public class CreditController {

    final CreditService creditService;
    
    public CreditController(CreditService creditService) {
        this.creditService = creditService;
    }
    
    @PostMapping
    public ResponseEntity<Object> saveCredit(@RequestBody @Valid CreditModal creditModal){
        return ResponseEntity.status(HttpStatus.CREATED).body(creditService.save(creditModal));
    }
}
