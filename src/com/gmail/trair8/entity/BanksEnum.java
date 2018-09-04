package com.gmail.trair8.entity;

public enum BanksEnum {
    BANKS("banks"),
    COMMERCIAL_BANK("commercial_bank"),
    CENTRAL_BANK("central_bank"),
    DEPOSIT("deposit"),
    COUNTRY("country"),
    TYPE("type"),
    DEPOSITOR("depositor"),
    ACCOUNT_ID("account_ID"),
    AMOUNT_ON_DEPOSIT("amount_on_deposit"),
    PROFITABILITY("profitability"),
    TIME_CONSTRAINTS("time_constraints"),
    NAME("name"),
    BRANCHES_NUMBER("branches_number");

    private String value;
    private BanksEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
