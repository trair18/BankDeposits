package main.com.gmail.trair8.parsing;

import main.com.gmail.trair8.entity.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.time.LocalDateTime;

public class BanksDOMBuilder extends AbstractBanksBuilder {
    private static final Logger LOGGER = LogManager.getLogger();
    private DocumentBuilder docBuilder;

    public BanksDOMBuilder() {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            docBuilder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            LOGGER.error(e);
        }
    }

    public void buildSetBanks(String fileName) {
        Document doc = null;
        try {
            doc = docBuilder.parse(fileName);
            Element root = doc.getDocumentElement();
            NodeList centralBankList = root.getElementsByTagName(BanksEnum.CENTRAL_BANK.getValue());
            for (int i = 0; i < centralBankList.getLength(); i++) {
                Element bankElement = (Element) centralBankList.item(i);
                Bank bank = buildCentralBank(bankElement);
                banks.add(bank);
            }

            NodeList commercialBankList = root.getElementsByTagName(BanksEnum.COMMERCIAL_BANK.getValue());
            for (int i = 0; i < commercialBankList.getLength(); i++) {
                Element gemElement = (Element) commercialBankList.item(i);
                Bank bank = buildCommercialBank(gemElement);
                banks.add(bank);
            }
        } catch (IOException e) {
            LOGGER.error(e);
        } catch (SAXException e) {
            LOGGER.error(e);
        }
    }

    private Bank buildCentralBank(Element bankElement) {
        CentralBank bank = new CentralBank();
        bank.setCountry(bankElement.getAttribute("country"));
        for (int i = 0; i < bankElement.getElementsByTagName("deposit").getLength(); i++) {
            Element depositElement = (Element) bankElement.getElementsByTagName("deposit").item(i);
            bank.getDeposits().add(fill(depositElement));
        }
        return bank;
    }

    private Bank buildCommercialBank(Element bankElement) {
        CommercialBank bank = new CommercialBank();
        bank.setName(bankElement.getAttribute("name"));
        if(bankElement.hasAttribute("branches_number"))
        {
            bank.setBranchesNumber(Integer.parseInt(bankElement.getAttribute("branches_number")));
        }
        for (int i = 0; i < bankElement.getElementsByTagName("deposit").getLength(); i++) {
            Element depositElement = (Element) bankElement.getElementsByTagName("deposit").item(i);
            bank.getDeposits().add(fill(depositElement));
        }
        return bank;
    }


    private Deposit fill(Element depositElement){
        Deposit currentDeposit = new Deposit();
        currentDeposit.setCountry(getElementTextContent(depositElement, "country"));
        currentDeposit.setType(getElementTextContent(depositElement, "type"));
        currentDeposit.setDepositor(getElementTextContent(depositElement, "depositor"));
        currentDeposit.setAccountID(getElementTextContent(depositElement, "account_ID"));
        currentDeposit.setAmountOnDeposit(Integer.parseInt(getElementTextContent(depositElement, "amount_on_deposit")));
        currentDeposit.setProfitability(Double.parseDouble(getElementTextContent(depositElement, "profitability")));
        currentDeposit.setTimeConstraints(LocalDateTime.parse(getElementTextContent(depositElement, "time_constraints")));
        return currentDeposit;
    }



    private static String getElementTextContent(Element element, String elementName) {
        NodeList nList = element.getElementsByTagName(elementName);
        Node node = nList.item(0);
        String text = node.getTextContent();
        return text;
    }
}
