package main.com.gmail.trair8.parsing;

import main.com.gmail.trair8.entity.Bank;

import java.util.HashSet;
import java.util.Set;

public abstract class AbstractBanksBuilder {

    protected Set<Bank> banks;

    public AbstractBanksBuilder() {
        banks = new HashSet<Bank>();
    }

    public AbstractBanksBuilder(Set<Bank> banks) {
        this.banks = banks;
    }

    public Set<Bank> getBanks() {
        return banks;
    }

    public abstract void buildSetBanks(String fileName);

}
