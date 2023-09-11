package com.example.myMoneyapp.dtos.request;

import com.example.myMoneyapp.models.Credit;
import com.example.myMoneyapp.models.Debit;
import lombok.Builder;
import lombok.Data;


import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Builder
public class BillingCycleRequest {
    @NotNull(message = "O nome não pode ser nulo")
    private String name;
    
    @NotNull(message = "O mês não pode ser nulo")
    @Min(value = 1, message = "O mês deve ser pelo menos 1")
    @Max(value = 12, message = "O mês não deve exceder 12")
    private Integer month;
    
    @NotNull(message = "O ano não pode ser nulo")
    @Min(value = 1970, message = "O ano deve ser pelo menos 1970")
    @Max(value = 2100, message = "O ano não deve exceder 2100")
    private Integer year;
    
    @OneToMany(cascade = CascadeType.ALL)
    private List<Credit> credits;
    
    @OneToMany(cascade = CascadeType.ALL)
    private List<Debit> debits;
}
