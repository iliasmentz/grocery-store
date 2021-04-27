package app.common;

import app.BaseTest;
import app.common.enums.Currency;
import app.common.enums.Money;
import app.common.exceptions.AppException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.math.BigDecimal;

public class TestMoney extends BaseTest {

    @Test
    public void should_default_to_zero_when_no_value_is_given() {
        // given
        Money money = new Money();

        // when
        BigDecimal amount = money.getAmount();

        // then
        Assertions.assertEquals(
            BigDecimal.ZERO,
            amount
        );
    }

    @Test
    public void should_print_the_given_amount() {
        // given
        Money money = new Money(BigDecimal.valueOf(25.12));

        // when
        String string = money.toString();

        // then
        Assertions.assertEquals(
            "25.12€",
            string
        );
    }

    @Test
    public void should_add_extra_money() {
        // given
        Money money = new Money(BigDecimal.valueOf(25.12));

        // when
        money.add(new Money(BigDecimal.valueOf(12.34)));

        // then
        BigDecimal amount = money.getAmount();
        Assertions.assertEquals(
            BigDecimal.valueOf(37.46),
            amount
        );
    }

    @Test
    public void should_throw_error_for_adding_different_currencies() {
        // given
        Money money = new Money(BigDecimal.valueOf(25.12));

        // when
        Executable differentCurrenyAddition = () -> money.add(new Money(BigDecimal.valueOf(12.34), Currency.USD));

        // then
        Assertions.assertThrows(
            AppException.class,
            differentCurrenyAddition
        );
    }

    @Test
    public void should_multiple_the_amount_correctly() {
        // given
        Money money = new Money(BigDecimal.valueOf(5.12));

        // when
        Money multipliedAmount = money.times(3);

        // then
        Assertions.assertEquals(
            "15.36€",
            multipliedAmount.toString()
        );
    }
}
