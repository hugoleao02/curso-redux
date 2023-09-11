package com.example.myMoneyapp.dtos.response;

import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
public class DebitResponse {
    
    private UUID id;
    
    private String name;
    
    private BigDecimal value;
}
