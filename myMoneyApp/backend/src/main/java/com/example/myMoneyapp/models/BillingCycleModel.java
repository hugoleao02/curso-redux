package com.example.myMoneyapp.models;

import jakarta.persistence.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "billing_cycle")
public class BillingCycleModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    
    @NotEmpty
    @NotNull
    @Column(nullable = false)
    private String name;
    
    @NotNull
    @Column(nullable = false)
    @Min(value = 1970, message = "O campo 'year' deve ser maior ou igual a 1970.")
    @Max(value = 2100, message = "O campo 'year' deve ser menor ou igual a 2100.")
    private Integer month;
    
    @NotNull
    @Column(nullable = false)
    @Min(value = 1970, message = "O campo 'year' deve ser maior ou igual a 1970.")
    @Max(value = 2100, message = "O campo 'year' deve ser menor ou igual a 2100.")
    private Integer year;
    
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "billing_cycle_id", nullable = false)
    private List<CreditModel> credits = new ArrayList<>();
    
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "billing_cycle_id", nullable = false)
    private List<DebitModel> debits = new ArrayList<>();
    
    
    public UUID getId() {
        return id;
    }
    
    public void setId(UUID id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public List<CreditModel> getCredits() {
        return credits;
    }
    
    public void setCredits(List<CreditModel> credits) {
        this.credits = credits;
    }
    
    public List<DebitModel> getDebits() {
        return debits;
    }
    
    public void setDebits(List<DebitModel> debits) {
        this.debits = debits;
    }
    
    
    public Integer getMonth() {
        return month;
    }
    
    public void setMonth(Integer month) {
        this.month = month;
    }
    
    public Integer getYear() {
        return year;
    }
    
    public void setYear(Integer year) {
        this.year = year;
    }
}
