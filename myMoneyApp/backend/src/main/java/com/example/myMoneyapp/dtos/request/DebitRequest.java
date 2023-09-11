package com.example.myMoneyapp.dtos.request;


import lombok.Data;

import javax.persistence.Column;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.UUID;

@Data
public class DebitRequest {
    
    private UUID id;
    
    @Column(nullable = false, name = "nome")
    @NotNull(message = "O nome não pode ser nulo")
    private String name;
    
    @NotNull(message = "O valor não pode ser nulo")
    @Min(value = 0, message = "O valor deve ser pelo menos 0")
    private BigDecimal value;
}
