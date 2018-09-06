package main.com.gmail.trair8.parsing;

import main.com.gmail.trair8.entity.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.time.LocalDateTime;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;

public class BanksHandler extends DefaultHandler {
    static final Logger LOG= LogManager.getLogger();
    private Set<Bank> banks;
    private Bank current = null;
    private BanksEnum currentEnum = null;
    private EnumSet<BanksEnum> withText;

    public BanksHandler() {
        banks = new HashSet<Bank>();
        withText = EnumSet.range(BanksEnum.DEPOSIT, BanksEnum.TIME_CONSTRAINTS);
    }

    public Set<Bank> getBanks() {
        return banks;
    }

    public void startElement(String uri, String localName, String qName, Attributes attrs) {
        if(BanksEnum.COMMERCIAL_BANK.getValue().equals(localName))
        {

            current=new CommercialBank();
            ((CommercialBank) current).setName(attrs.getValue(0));
            if(attrs.getLength()==2)
            {
                ((CommercialBank) current).setBranchesNumber(Integer.parseInt(attrs.getValue(1)));
            }
        }
        else if(BanksEnum.CENTRAL_BANK.getValue().equals(localName))
        {
            current=new CentralBank();
            ((CentralBank) current).setCountry(attrs.getValue(0));
        }
        else
        {
            BanksEnum temp=BanksEnum.valueOf(localName.toUpperCase());
            if(withText.contains(temp))
            {
                currentEnum=temp;
            }
        }
    }

    public void endElement(String uri, String localName, String qName) {
        if(BanksEnum.COMMERCIAL_BANK.getValue().equals(localName)||BanksEnum.CENTRAL_BANK.getValue().equals(localName))
        {
            banks.add(current);
        }
    }

    public void characters(char[] ch, int start, int length) {

        String s = new String(ch, start, length).trim();
        if(currentEnum!=null)
        {
            switch (currentEnum){
                case DEPOSIT:
                    current.getDeposits().add(new Deposit());
                    break;
                case COUNTRY:
                    current.getDeposits().get(current.getDeposits().size()-1).setCountry(s);
                    break;
                case TYPE:
                    current.getDeposits().get(current.getDeposits().size()-1).setType(s);
                    break;
                case DEPOSITOR:
                    current.getDeposits().get(current.getDeposits().size()-1).setDepositor(s);
                    break;
                case ACCOUNT_ID:
                    current.getDeposits().get(current.getDeposits().size()-1).setAccountID(s);
                    break;
                case AMOUNT_ON_DEPOSIT:
                    current.getDeposits().get(current.getDeposits().size()-1).setAmountOnDeposit(Integer.parseInt(s));
                    break;
                case PROFITABILITY:
                    current.getDeposits().get(current.getDeposits().size()-1).setProfitability(Double.parseDouble(s));
                    break;
                case TIME_CONSTRAINTS:
                    current.getDeposits().get(current.getDeposits().size()-1).setTimeConstraints(LocalDateTime.parse(s));
                    break;
                default:
                    LOG.fatal("No such element in BanksEnum");
                    throw new EnumConstantNotPresentException(currentEnum.getDeclaringClass(), currentEnum.name());
            }
        }
        currentEnum=null;
    }
}
