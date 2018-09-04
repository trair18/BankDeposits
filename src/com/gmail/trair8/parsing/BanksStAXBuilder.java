package com.gmail.trair8.parsing;

import com.gmail.trair8.entity.*;
import com.gmail.trair8.exception.ParsingException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Set;

public class BanksStAXBuilder extends AbstractBanksBuilder {
    static final Logger LOG= LogManager.getLogger();
    private XMLInputFactory inputFactory;

    public BanksStAXBuilder() {
        inputFactory = XMLInputFactory.newInstance();
    }

    public Set<Bank> getBanks() {
        return banks;
    }

    public void buildSetBanks(String fileName) {
        FileInputStream inputStream = null;
        XMLStreamReader reader = null;
        String name;
        try {
            inputStream = new FileInputStream(new File(fileName));
            reader = inputFactory.createXMLStreamReader(inputStream);
            while (reader.hasNext()) {
                int type = reader.next();
                if (type == XMLStreamConstants.START_ELEMENT) {
                    name = reader.getLocalName();
                    if (BanksEnum.valueOf(name.toUpperCase()) == BanksEnum.COMMERCIAL_BANK) {
                        CommercialBank bank = buildCommercialBank(reader, name);
                        banks.add(bank);
                    }
                    else if ( BanksEnum.valueOf(name.toUpperCase()) == BanksEnum.CENTRAL_BANK) {
                        CentralBank bank = buildCentralBank(reader, name);
                        banks.add(bank);
                    }

                }
            }
        } catch (XMLStreamException ex) {
            LOG.error(ex);
        } catch (ParsingException ex) {
            LOG.error(ex);
        } catch (FileNotFoundException ex) {
            LOG.error(ex);
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                LOG.error(e);
            }
        }
    }

    private CentralBank buildCentralBank(XMLStreamReader reader, String bankType) throws XMLStreamException, ParsingException {
        CentralBank bank = new CentralBank();
        bank.setCountry(reader.getAttributeValue(null, BanksEnum.COUNTRY.getValue()));
        return (CentralBank)fill(reader, bank);
    }

    private CommercialBank buildCommercialBank(XMLStreamReader reader, String bankType) throws XMLStreamException, ParsingException {
        CommercialBank bank = new CommercialBank();
        bank.setName(reader.getAttributeValue(null, BanksEnum.NAME.getValue()));
        String branchesNumber = reader.getAttributeValue(null, BanksEnum.BRANCHES_NUMBER.getValue());
        if (branchesNumber != null){
            bank.setBranchesNumber(Integer.parseInt(branchesNumber));
        }
        return (CommercialBank)fill(reader, bank);

    }

    private Bank fill(XMLStreamReader reader, Bank bank) throws XMLStreamException, ParsingException{
        String name;
        while (reader.hasNext()) {
            int type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT:
                    name = reader.getLocalName();
                    switch (BanksEnum.valueOf(name.toUpperCase())) {
                        case DEPOSIT:
                            bank.getDeposits().add(getXMLDeposit(reader));
                            break;
                    }
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    name = reader.getLocalName();
                    if (BanksEnum.valueOf(name.toUpperCase()) == BanksEnum.CENTRAL_BANK ||
                            BanksEnum.valueOf(name.toUpperCase()) == BanksEnum.COMMERCIAL_BANK) {
                        return bank;
                    }
                    break;
            }
        }
        throw new ParsingException();
    }

    private Deposit getXMLDeposit(XMLStreamReader reader) throws XMLStreamException, ParsingException {
        Deposit deposit = new Deposit();;
        int type;
        String name;
        while (reader.hasNext()) {
            type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT:
                    name = reader.getLocalName();
                    switch (BanksEnum.valueOf(name.toUpperCase())) {
                        case COUNTRY:
                            deposit.setCountry(getXMLText(reader));
                            break;
                        case TYPE:
                            deposit.setType(getXMLText(reader));
                            break;
                        case DEPOSITOR:
                            deposit.setDepositor(getXMLText(reader));
                            break;
                        case ACCOUNT_ID:
                            deposit.setAccountID(getXMLText(reader));
                            break;
                        case AMOUNT_ON_DEPOSIT:
                            deposit.setAmountOnDeposit(Integer.parseInt(getXMLText(reader)));
                            break;
                        case PROFITABILITY:
                            deposit.setProfitability(Double.parseDouble(getXMLText(reader)));
                            break;
                        case TIME_CONSTRAINTS:
                            deposit.setTimeConstraints(LocalDateTime.parse(getXMLText(reader)));
                            break;
                    }
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    name = reader.getLocalName();
                    if (BanksEnum.valueOf(name.toUpperCase()) == BanksEnum.DEPOSIT){
                        return deposit;
                    }
                    break;
            }
        }
        throw new ParsingException();
    }

    private String getXMLText(XMLStreamReader reader) throws XMLStreamException{
        String text = null;
        if (reader.hasNext()) {
            reader.next();
            text = reader.getText();
        }
        return text;
    }
}
