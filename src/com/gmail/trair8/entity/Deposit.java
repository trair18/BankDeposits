package com.gmail.trair8.entity;

import java.time.LocalDateTime;
import java.util.Objects;

public class Deposit {
    private String country;
    private String type;
    private String depositor;
    private String accountID;
    private int amountOnDeposit;
    private double profitability;
    private LocalDateTime timeConstraints = LocalDateTime.parse("2018-06-13T15:30:12");

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDepositor() {
        return depositor;
    }

    public void setDepositor(String depositor) {
        this.depositor = depositor;
    }

    public String getAccountID() {
        return accountID;
    }

    public void setAccountID(String accountID) {
        this.accountID = accountID;
    }

    public int getAmountOnDeposit() {
        return amountOnDeposit;
    }

    public void setAmountOnDeposit(int amountOnDeposit) {
        this.amountOnDeposit = amountOnDeposit;
    }

    public double getProfitability() {
        return profitability;
    }

    public void setProfitability(double profitability) {
        this.profitability = profitability;
    }

    public LocalDateTime getTimeConstraints() {
        return timeConstraints;
    }

    public void setTimeConstraints(LocalDateTime timeConstraints) {
        this.timeConstraints = timeConstraints;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Deposit deposit = (Deposit) o;
        return amountOnDeposit == deposit.amountOnDeposit &&
                Double.compare(deposit.profitability, profitability) == 0 &&
                Objects.equals(country, deposit.country) &&
                Objects.equals(type, deposit.type) &&
                Objects.equals(depositor, deposit.depositor) &&
                Objects.equals(accountID, deposit.accountID) &&
                Objects.equals(timeConstraints, deposit.timeConstraints);
    }

    @Override
    public int hashCode() {

        return Objects.hash(country, type, depositor, accountID, amountOnDeposit, profitability, timeConstraints);
    }

    @Override
    public String toString() {
        return "Deposit{" +
                "country='" + country + '\'' +
                ", type='" + type + '\'' +
                ", depositor='" + depositor + '\'' +
                ", accountID='" + accountID + '\'' +
                ", amountOnDeposit=" + amountOnDeposit +
                ", profitability=" + profitability +
                ", timeConstraints=" + timeConstraints +
                '}';
    }
}
