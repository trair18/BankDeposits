package com.gmail.trair8.runner;

import com.gmail.trair8.parsing.*;

public class Runner {

    public static void main(String[] args) {

        BanksBuilderFactory banksBuilderFactory = new BanksBuilderFactory();
        AbstractBanksBuilder builder = banksBuilderFactory.createBanksBuilder("sax");
        builder.buildSetBanks("data/banks.xml");
        System.out.println(builder.getBanks());
    }
}
