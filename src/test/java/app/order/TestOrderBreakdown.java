package app.order;

import app.BaseTest;
import app.common.Clock;
import app.items.Bread;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestOrderBreakdown extends BaseTest {

    @Test
    public void should_print_the_breakdown_for_each_unique_item() {
        // given
        Order order = new Order();
        order.addItem(new Bread(Clock.today()));
        order.addItem(new Bread(Clock.today().minusDays(2)));

        // when
        String breakdown = order.receipt().printBreakdown();

        // then
        Assertions.assertEquals(
            "BREAD_2021-03-21 x 1 -> 1.63€\n" +
                "BREAD_2021-03-23 x 1 -> 1.63€",
            breakdown
        );
    }

    @Test
    public void should_print_breakdown_for_same_item_multiple_times() {
        // given
        Order order = new Order();
        order.addItem(new Bread(Clock.today()));
        order.addItem(new Bread(Clock.today()));

        // when
        String breakdown = order.receipt().printBreakdown();

        // then
        Assertions.assertEquals(
            "BREAD_2021-03-23 x 2 -> 3.26€",
            breakdown
        );
    }

}
