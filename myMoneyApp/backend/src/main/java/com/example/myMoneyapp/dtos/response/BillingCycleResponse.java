package com.example.myMoneyapp.dtos.response;

import com.example.myMoneyapp.models.Credit;
import com.example.myMoneyapp.models.Debit;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class BillingCycleResponse {
    
    private UUID id;
    
    private String name;

    private int month;

    private int year;

    private List<Credit> credits;
   
    private List<Debit> debits;
}
