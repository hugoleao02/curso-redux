package com.example.myMoneyapp.models;

public class SummaryModel {
    private double credits;
    private double debits;
    private double balance;
    public SummaryModel(double credits, double debits, double balance) {
        this.credits = credits;
        this.debits = debits;
        this.balance = balance;
    }

    public double getCredits() {
        return credits;
    }

    public void setCredits(double credits) {
        this.credits = credits;
    }

    public double getDebits() {
        return debits;
    }

    public void setDebits(double debits) {
        this.debits = debits;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
