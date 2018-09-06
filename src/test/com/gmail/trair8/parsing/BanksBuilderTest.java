package test.com.gmail.trair8.parsing;

import main.com.gmail.trair8.entity.Bank;
import main.com.gmail.trair8.entity.CommercialBank;
import main.com.gmail.trair8.entity.Deposit;
import main.com.gmail.trair8.parsing.AbstractBanksBuilder;
import main.com.gmail.trair8.parsing.BanksDOMBuilder;
import main.com.gmail.trair8.parsing.BanksSAXBuilder;
import main.com.gmail.trair8.parsing.BanksStAXBuilder;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.time.LocalDateTime;

public class BanksBuilderTest {

    private static CommercialBank expected;

    private final static String PATH = "data/banksTest.xml";

    @BeforeSuite
    private static void init(){
        expected = new CommercialBank();
        expected.setName("Bank Moscow-Minsk");
        expected.setBranchesNumber(6);
        Deposit deposit = new Deposit();
        deposit.setCountry("Belarus");
        deposit.setType("metal");
        deposit.setDepositor("Ivanov Ivan");
        deposit.setAccountID("q1");
        deposit.setAmountOnDeposit(450);
        deposit.setProfitability(12.5);
        deposit.setTimeConstraints(LocalDateTime.parse("2018-05-30T10:28:44"));
        expected.getDeposits().add(deposit);
    }

    @Test
    private void testBanksStAXBuilder(){
        AbstractBanksBuilder builder = new BanksStAXBuilder();
        builder.buildSetBanks(PATH);
        Bank result = builder.getBanks().iterator().next();
        Assert.assertEquals(expected, result);
    }

    @Test
    private void testBanksSAXBuilder(){
        AbstractBanksBuilder builder = new BanksSAXBuilder();
        builder.buildSetBanks(PATH);
        Bank result = builder.getBanks().iterator().next();
        Assert.assertEquals(expected, result);
    }

    @Test
    private void testBanksDOMBuilder(){
        AbstractBanksBuilder builder = new BanksDOMBuilder();
        builder.buildSetBanks(PATH);
        System.out.println(builder.getBanks());
        Bank result = builder.getBanks().iterator().next();
        Assert.assertEquals(expected, result);
    }

}
