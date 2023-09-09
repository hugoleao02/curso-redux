package com.myMoneyapp.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.List;
import java.util.UUID;

@Data
@Entity(name = "billing_cycles")
@Table(name = "billing_cycles")
@AllArgsConstructor
@NoArgsConstructor
public class BillingCycle {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    
    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int month;
    
    @Column(nullable = false)
    private int year;
    
    @OneToMany(cascade = CascadeType.ALL)
    private List<Credit> credits;
    
    @OneToMany(cascade = CascadeType.ALL)
    private List<Debit> debits;
}
