package main.com.gmail.trair8.runner;

import main.com.gmail.trair8.parsing.*;

public class Runner {

    private final static String PATH = "data/banks.xml";

    public static void main(String[] args) {

        BanksBuilderFactory banksBuilderFactory = new BanksBuilderFactory();
        AbstractBanksBuilder builder = banksBuilderFactory.createBanksBuilder("dom");
        builder.buildSetBanks(PATH);
        System.out.println(builder.getBanks());
    }
}
