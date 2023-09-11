package com.example.myMoneyapp.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.UUID;

@Data
@Entity(name = "debits")
@Table(name = "debits")
@AllArgsConstructor
@NoArgsConstructor
public class Debit {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private BigDecimal value;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;
}
