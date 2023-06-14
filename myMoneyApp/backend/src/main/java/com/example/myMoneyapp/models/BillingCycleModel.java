package com.example.myMoneyapp.models;

import jakarta.persistence.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
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
    @Column
    private String name;
    @NotEmpty
    @NotNull
    @Column
    private int month;
    @NotEmpty
    @NotNull
    @Column
    private int year;
    
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "billing_cycle_id", nullable = false)
    private List<CreditModel> credits = new ArrayList<>();
    
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
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
    
    public int getMonth() {
        return month;
    }
    
    public void setMonth(int month) {
        this.month = month;
    }
    
    public int getYear() {
        return year;
    }
    
    public void setYear(int year) {
        this.year = year;
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
}
