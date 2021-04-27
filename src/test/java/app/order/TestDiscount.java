package app.order;

import app.BaseTest;
import app.common.Clock;
import app.common.enums.Country;
import app.common.enums.Money;
import app.items.Bread;
import app.items.beers.Beer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.stream.Stream;

public class TestDiscount extends BaseTest {
    private static final BigDecimal BREAD_PRICE = BigDecimal.valueOf(1.63);
    private static final LocalDate SIX_DAYS_OLD = Clock.today().minusDays(6);
    private static final LocalDate FOUR_DAYS_OLD = Clock.today().minusDays(4);
    private static final double GERMAN_BEER_PRICE = 3.5;
    private static final double GERMAN_BEER_DISCOUNT = 1;

    @Test
    public void should_get_charged_once_for_two_bread_older_than_3_days() {
        // given
        Order order = new Order();
        order.addItem(new Bread(Clock.today().minusDays(4)));
        order.addItem(new Bread(Clock.today().minusDays(4)));

        // when
        Money total = order.receipt().total();

        // then
        Assertions.assertEquals(
            BREAD_PRICE,
            total.getAmount()
        );
    }

    @Test
    public void should_get_charged_three_times_for_five_bread_loaves_older_than_3_days() {
        // given
        Order order = new Order();
        order.addItem(new Bread(FOUR_DAYS_OLD));
        order.addItem(new Bread(Clock.today().minusDays(4)));
        order.addItem(new Bread(Clock.today().minusDays(4)));
        order.addItem(new Bread(Clock.today().minusDays(4)));
        order.addItem(new Bread(Clock.today().minusDays(4)));

        // when
        Money total = order.receipt().total();

        // then
        Assertions.assertEquals(
            BREAD_PRICE.multiply(BigDecimal.valueOf(3)),
            total.getAmount()
        );
    }

    @Test
    public void should_get_charged_once_for_3_bread_loaves_produced_before_six_days() {
        // given
        Order order = new Order();
        order.addItem(new Bread(SIX_DAYS_OLD));
        order.addItem(new Bread(SIX_DAYS_OLD));
        order.addItem(new Bread(SIX_DAYS_OLD));

        // when
        Money total = order.receipt().total();

        // then
        Assertions.assertEquals(
            BREAD_PRICE,
            total.getAmount()
        );
    }

    @Test
    public void should_get_charged_twice_for_4_bread_loaves_produced_before_six_days() {
        // given
        Order order = new Order();
        order.addItem(new Bread(SIX_DAYS_OLD));
        order.addItem(new Bread(SIX_DAYS_OLD));
        order.addItem(new Bread(SIX_DAYS_OLD));
        order.addItem(new Bread(SIX_DAYS_OLD));

        // when
        Money total = order.receipt().total();

        // then
        Assertions.assertEquals(
            BREAD_PRICE.multiply(BigDecimal.valueOf(2)),
            total.getAmount()
        );
    }

    @ParameterizedTest(name = "#{index} - Discount works for beers from={0}")
    @MethodSource("countrySixPackPrices")
    public void should_subtract_the_discount_price_from_a_six_pack_of_german_beers(Country country, Double amount) {
        // given
        Order order = new Order();
        addSixPack(order, country);

        // when
        Money total = order.receipt().total();

        // then
        BigDecimal discountedAmount = BigDecimal.valueOf(amount);
        Assertions.assertEquals(
            discountedAmount,
            total.getAmount()
        );
    }

    private static Stream<Arguments> countrySixPackPrices() {
        return Stream.of(
            Arguments.arguments(Country.GERMANY, 20.0),
            Arguments.arguments(Country.NETHERLANDS, 25.0),
            Arguments.arguments(Country.BELGIUM, 27.0)
        );
    }

    @Test
    public void should_subtract_the_discount_as_many_times_as_the_six_packs_bought() {
        // given
        Order order = new Order();
        addSixPack(order, Country.GERMANY);
        addSixPack(order, Country.GERMANY);

        // when
        Money total = order.receipt().total();

        // then
        BigDecimal initialAmount = BigDecimal.valueOf(GERMAN_BEER_PRICE * 12);
        BigDecimal expectedDiscount = BigDecimal.valueOf(GERMAN_BEER_DISCOUNT * 2);

        Assertions.assertEquals(
            initialAmount.subtract(expectedDiscount),
            total.getAmount()
        );
    }

    @Test
    public void should_calculate_the_total_properly_for_not_completed_six_packs() {
        // given
        Order order = new Order();
        addSixPack(order, Country.GERMANY);
        addSixPack(order, Country.GERMANY);
        order.addItem(Beer.from(Country.GERMANY));

        // when
        Money total = order.receipt().total();

        // then
        BigDecimal initialAmount = BigDecimal.valueOf(GERMAN_BEER_PRICE * 13);
        BigDecimal expectedDiscount = BigDecimal.valueOf(GERMAN_BEER_DISCOUNT * 2);

        Assertions.assertEquals(
            initialAmount.subtract(expectedDiscount),
            total.getAmount()
        );
    }

    private void addSixPack(Order order, Country country) {
        for (int beer = 0; beer < 6; ++beer) {
            order.addItem(Beer.from(country));
        }
    }
}
