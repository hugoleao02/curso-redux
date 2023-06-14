package com.example.myMoneyapp.models;

import jakarta.persistence.*;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.NumberFormat;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "debits")
public class DebitModel {
    
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
    private float value;
    @Enumerated(EnumType.STRING)
    @Column
    private Status status;
    
  
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
    
    public float getValue() {
        return value;
    }
    
    public void setValue(float value) {
        this.value = value;
    }
    
    public String getStatus() {
        return status != null ? status.name() : null;
    }
    
    public void setStatus(String status) {
        this.status = convertStatusFromString(status);
    }
    
    private Status convertStatusFromString(String status) {
        if (status == null) {
            return null;
        }
        
        status = status.toUpperCase();
        
        for (Status enumValue : Status.values()) {
            if (enumValue.name().equalsIgnoreCase(status)) {
                return enumValue;
            }
        }
        
        throw new IllegalArgumentException("Invalid status: " + status);
    }
}
