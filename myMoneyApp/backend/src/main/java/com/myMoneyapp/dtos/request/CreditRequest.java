package com.myMoneyapp.dtos.request;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
public class CreditRequest {
    
    private UUID id;
    
    @NotNull(message = "O nome não pode ser nulo")
    private String name;
    
    @NotNull(message = "O valor não pode ser nulo")
    @Min(value = 0, message = "O valor deve ser pelo menos 0")
    private BigDecimal value;
}
