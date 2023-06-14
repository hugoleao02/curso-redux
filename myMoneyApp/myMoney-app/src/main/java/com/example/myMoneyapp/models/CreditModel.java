package com.example.myMoneyapp.models;

import jakarta.persistence.*;
import org.springframework.format.annotation.NumberFormat;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Entity
@Table(name = "credits")
public class CreditModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private UUID id;
    @NotEmpty
    @NotNull
    @Column
    private String name;
    @NotEmpty
    @NotNull
    @Column
    @NumberFormat(style = NumberFormat.Style.NUMBER)
    private double value;
    
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
    
    public double getValue() {
        return value;
    }
    
    public void setValue(double value) {
        this.value = value;
    }
}
