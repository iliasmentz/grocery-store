package app.items;

import app.common.Clock;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestBread {
    @Test
    public void should_have_always_the_same_price_for_a_single_unit() {
        // given
        Bread bread1 = new Bread(Clock.today());

        // when
        Bread bread2 = new Bread(Clock.today());

        // then
        Assertions.assertEquals(
            bread1.getPrice().getAmount(),
            bread2.getPrice().getAmount()
        );
    }

    @Test
    public void should_return_the_proper_type() {
        // when
        Bread bread = new Bread(Clock.today());

        // then
        Assertions.assertEquals(
            bread.getType(),
            ItemType.BREAD
        );
    }

}
