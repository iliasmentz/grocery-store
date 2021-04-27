package app.order;

import app.BaseTest;
import app.common.Clock;
import app.common.enums.Money;
import app.items.Bread;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

public class TestOrderTotal extends BaseTest {

    private final BigDecimal BREAD_PRICE = BigDecimal.valueOf(1.63);

    @Test
    public void total_should_be_zero_when_no_items_in_the_order() {
        // given
        Order order = new Order();

        // when
        Money total = order.receipt().total();

        // then
        Assertions.assertEquals(
            BigDecimal.ZERO,
            total.getAmount()
        );
    }

    @Test
    public void should_get_charged_twice_for_two_fresh_bread_loaves() {
        // given
        Order order = new Order();
        order.addItem(new Bread(Clock.today()));
        order.addItem(new Bread(Clock.today()));

        // when
        Money total = order.receipt().total();

        // then
        Assertions.assertEquals(
            BREAD_PRICE.multiply(BigDecimal.valueOf(2)),
            total.getAmount()
        );
    }
}
