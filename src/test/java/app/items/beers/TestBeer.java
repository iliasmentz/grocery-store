package app.items.beers;

import app.BaseTest;
import app.common.enums.Country;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

public class TestBeer extends BaseTest {

    @Test
    public void should_return_a_beer_object_based_on_the_country() {
        // when
        Beer beer = Beer.from(Country.BELGIUM);

        // then
        Assertions.assertEquals(
            Country.BELGIUM,
            beer.getOrigin()
        );

        Assertions.assertEquals(
            BigDecimal.valueOf(5.0),
            beer.getPrice().getAmount()
        );
    }

}
