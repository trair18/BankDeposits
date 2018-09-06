package main.com.gmail.trair8.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class Bank {
    List<Deposit> deposits;

    public Bank() {
        deposits = new ArrayList<>();
    }

    public List<Deposit> getDeposits() {
        return deposits;
    }

    public void setDeposits(List<Deposit> deposits) {
        this.deposits = deposits;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bank bank = (Bank) o;
        return Objects.equals(deposits, bank.deposits);
    }

    @Override
    public int hashCode() {

        return Objects.hash(deposits);
    }

    @Override
    public String toString() {
        return "Bank{" +
                "deposits=" + deposits +
                '}';
    }
}
