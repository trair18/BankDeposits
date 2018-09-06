package test.com.gmail.trair8.parsing;

import main.com.gmail.trair8.parsing.BanksDOMBuilder;
import main.com.gmail.trair8.parsing.BanksSAXBuilder;
import main.com.gmail.trair8.parsing.BanksStAXBuilder;
import org.testng.Assert;


import main.com.gmail.trair8.parsing.BanksBuilderFactory;
import org.testng.annotations.Test;

public class BanksBuilderFactoryTest{

    private BanksBuilderFactory banksBuilderFactory = new BanksBuilderFactory();

    @Test
    public void testCreateBanksBuilder() throws Exception{
        Assert.assertEquals(new BanksDOMBuilder().getClass(), banksBuilderFactory.createBanksBuilder("dom").getClass());
        Assert.assertEquals(new BanksSAXBuilder().getClass(), banksBuilderFactory.createBanksBuilder("sax").getClass());
        Assert.assertEquals(new BanksStAXBuilder().getClass(), banksBuilderFactory.createBanksBuilder("stax").getClass());
    }
}
