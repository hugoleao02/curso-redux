package com.example.myMoneyapp.models;

public class SummaryModel {
    private double totalCredits;
    private double totalDebits;
    private double balance;
    public SummaryModel(double totalCredits, double totalDebits, double balance) {
        this.totalCredits = totalCredits;
        this.totalDebits = totalDebits;
        this.balance = balance;
    }
    
    public double getTotalCredits() {
        return totalCredits;
    }
    
    public void setTotalCredits(double totalCredits) {
        this.totalCredits = totalCredits;
    }
    
    public double getTotalDebits() {
        return totalDebits;
    }
    
    public void setTotalDebits(double totalDebits) {
        this.totalDebits = totalDebits;
    }
    
    public double getBalance() {
        return balance;
    }
    
    public void setBalance(double balance) {
        this.balance = balance;
    }
}
