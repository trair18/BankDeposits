package com.gmail.trair8.entity;

import java.util.Objects;

public class CommercialBank extends Bank {
    private int branchesNumber;
    private String name;

    public int getBranchesNumber() {
        return branchesNumber;
    }

    public void setBranchesNumber(int branchesNumber) {
        this.branchesNumber = branchesNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        CommercialBank that = (CommercialBank) o;
        return branchesNumber == that.branchesNumber &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {

        return Objects.hash(super.hashCode(), branchesNumber, name);
    }

    @Override
    public String toString() {
        return "CommercialBank{" +
                "branchesNumber=" + branchesNumber +
                ", name='" + name + '\'' +
                ", deposits=" + deposits +
                '}';
    }
}
