package app.order;

import app.BaseTest;
import app.common.Clock;
import app.common.enums.Country;
import app.common.exceptions.ItemExpiredException;
import app.items.Bread;
import app.items.beers.Beer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestOrder extends BaseTest {

    @Test
    public void receipt_should_return_both_breakdown_and_total() {
        // given
        Order order = new Order();
        order.addItem(new Bread(Clock.today()));
        order.addItem(new Bread(Clock.today().minusDays(2)));
        order.addItem(Beer.from(Country.BELGIUM));

        // when
        String receipt = order.receipt().print();

        // then
        Assertions.assertEquals(
            "BEER_BELGIUM x 1 -> 5.0€\n" +
                "BREAD_2021-03-21 x 1 -> 1.63€\n" +
                "BREAD_2021-03-23 x 1 -> 1.63€\n" +
                "\n" +
                "Total: 8.26€",
            receipt
        );
    }

    @Test
    public void should_throw_an_error_for_too_old_bread() {
        // given
        Order order = new Order();
        order.addItem(new Bread(Clock.today()));

        // when/then
        Assertions.assertThrows(
            ItemExpiredException.class,
            () -> order.addItem(new Bread(Clock.today().minusDays(7)))
        );
    }
}
