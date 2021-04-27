package app.common.enums;

import app.common.exceptions.AppException;

import java.math.BigDecimal;

public class Money {
    private BigDecimal amount = BigDecimal.ZERO;
    private Currency currency = Currency.EUR;

    public Money() {
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public Money(BigDecimal amount) {
        this.amount = amount;
    }

    public Money(BigDecimal amount, Currency currency) {
        this.amount = amount;
        this.currency = currency;
    }

    public Money times(int timesToMultiply) {
        BigDecimal newAmount = amount.multiply(BigDecimal.valueOf(timesToMultiply));
        return new Money(
            newAmount
        );
    }

    public void add(Money extraMoney) {
        validateCurrency(extraMoney);
        add(extraMoney.amount);
    }

    public void subtract(Money amountToRemove) {
        validateCurrency(amountToRemove);
        subtract(amountToRemove.amount);
    }

    private void validateCurrency(Money extraMoney) {
        if (!currency.equals(extraMoney.currency)) {
            throw new AppException("summing different currencies");
        }
    }

    private void add(BigDecimal extraAmount) {
        amount = amount.add(extraAmount);
    }

    private void subtract(BigDecimal amountToRemove) {
        amount = amount.subtract(amountToRemove);
    }

    @Override
    public String toString() {
        return getAmount() + getCurrency().getSymbol();
    }
}
