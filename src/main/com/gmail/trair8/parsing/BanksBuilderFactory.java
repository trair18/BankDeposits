package main.com.gmail.trair8.parsing;

public class BanksBuilderFactory {

    private enum TypeParser {
        SAX, STAX, DOM
    }

    public AbstractBanksBuilder createBanksBuilder(String typeParser) {
        TypeParser type = TypeParser.valueOf(typeParser.toUpperCase());
        switch (type) {
            case DOM:
                return new BanksDOMBuilder();
            case STAX:
                return new BanksStAXBuilder();
            case SAX:
                return new BanksSAXBuilder();
            default:
                throw new RuntimeException();
        }
    }

}
