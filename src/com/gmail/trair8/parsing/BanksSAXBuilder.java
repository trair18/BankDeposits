package com.gmail.trair8.parsing;

import com.gmail.trair8.entity.Bank;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.IOException;
import java.util.Set;

public class BanksSAXBuilder extends AbstractBanksBuilder {
    private final static Logger LOGGER = LogManager.getLogger();
    private BanksHandler bh;
    private XMLReader reader;

    public BanksSAXBuilder() {
        bh = new BanksHandler();
        try {
            reader = XMLReaderFactory.createXMLReader();
            reader.setContentHandler(bh);
        } catch (SAXException e) {
            LOGGER.error(e);
        }
    }

    public Set<Bank> getBanks() {
        return banks;
    }

    public void buildSetBanks(String fileName) {
        try {
            reader.parse(fileName);
        } catch (SAXException e) {
            LOGGER.error(e);
        } catch (IOException e) {
            LOGGER.error(e);
        }
        banks = bh.getBanks();
    }
}
