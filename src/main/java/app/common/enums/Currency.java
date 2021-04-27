package app.common.enums;

public enum Currency {
    EUR("€"),
    USD("$");

    private String symbol;

    Currency(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }
}
