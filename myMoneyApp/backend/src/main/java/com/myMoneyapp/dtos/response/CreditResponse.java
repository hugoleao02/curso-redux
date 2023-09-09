package com.myMoneyapp.dtos.response;

import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
public class CreditResponse {
    private UUID id;
    
    private String name;
    
    private BigDecimal value;
}
